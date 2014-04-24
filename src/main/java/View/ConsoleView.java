/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ChangeAILevelCurrentComputerPlayer;
import Events.ApplicationStateChangedEvent;
import Events.CarPositionChangedEvent;
import Events.CarStatesChangedEvent;
import Events.ChangedClassementEvent;
import Events.CircuitAddedEvent;
import Events.CircuitChangedEvent;
import Events.CreateViewEvent;
import Events.CurrentPlayerCarChangedEvent;
import Events.CurrentPlayerCarSpeedChanged;
import Events.CurrentPlayerChangedEvent;
import Events.CurrentPlayerDiceValueChangedEvent;
import Events.CurrentPlayerModeChangedEvent;
import Events.DivinModeChangedEvent;
import Events.GameOverEvent;
import Events.NumberOfPlayersChanged;
import Events.PlayerFinishedALapEvent;
import Events.PlayerIsEliminateEvent;
import Events.PlayerLoosePointsEvent;
import Events.PlayerNameChangedEvent;
import Events.PlayerWonEvent;
import Events.PlayersSortedEvent;
import Events.RuleInformationEvent;
import Events.ScrapOnCellEvent;
import Events.SelectedBendChangedEvent;
import Events.SelectedPlayerChangedEvent;
import Events.ThrowCurrentPlayerDiceEvent;
import Events.WeatherChangedEvent;
import Model.Game;
import Model.Players.Player;
import com.google.common.eventbus.Subscribe;
import java.util.Collection;

/**
 *
 * @author matt
 */
public class ConsoleView implements AbstractFormulaDView, AbstractGameView, AbstractFormulaDConfigurationGameView {

    private boolean isConnect = true;

    @Override
    public void createView(CreateViewEvent ev) {
    }

    @Override
    @Subscribe
    public void refreshDice(ThrowCurrentPlayerDiceEvent ev) {
        System.out.println("Dice Value : " + ev.getCurrentDiceValue());
    }

    @Override
    @Subscribe
    public void refreshClassement(ChangedClassementEvent ev) {
    }

    @Override
    @Subscribe
    public void refreshCarPosition(CarPositionChangedEvent ev) {
        if (ev.getOldPosition() == null) {
            System.out.println(ev.getPlayer().getPlayerName()
                    + " start on the cell :"
                    + ev.getNewPosition().getComonId());
        } else {
            System.out.println(ev.getPlayer().getPlayerName()
                    + " moved from "
                    + ev.getOldPosition().getComonId()
                    + " to "
                    + ev.getNewPosition().getComonId());
        }

    }

    @Override
    @Subscribe
    public void refreshCurrentPlayer(CurrentPlayerChangedEvent ev) {
        System.out.println("\n\nCurrent Player : " + ev.getCurrentPlayer().getPlayerName());
    }

    @Override
    @Subscribe
    public void refreshSelectedPlayer(SelectedPlayerChangedEvent ev) {
    }

    @Override
    public void refreshCurrentPlayerDiceValue(CurrentPlayerDiceValueChangedEvent ev) {
    }

    @Override
    public void refreshCurrentPlayerCarSpeed(CurrentPlayerCarSpeedChanged ev) {
    }

    @Override
    public void refreshSelectedBend(SelectedBendChangedEvent ev) {
    }

    @Override
    public void refreshApplicationState(ApplicationStateChangedEvent ev) {
    }

    @Override
    public void refreshWeather(WeatherChangedEvent ev) {
    }

    @Override
    public void refreshNumberOfPlayers(NumberOfPlayersChanged ev) {
    }

    @Override
    public void refreshCircuitAdded(CircuitAddedEvent ev) {
    }

    @Override
    public void refreshPlayerName(PlayerNameChangedEvent ev) {
    }

    @Override
    public void refreshCurrentCar(CurrentPlayerCarChangedEvent ev) {
    }

    @Override
    public void refreshCurrentPlayerMode(CurrentPlayerModeChangedEvent ev) {
    }

    @Override
    public void refreshCurrentComputerPlayerLevel(ChangeAILevelCurrentComputerPlayer ev) {
    }

    @Override
    public boolean isConnect() {
        return isConnect;
    }

    @Override
    public void connectView() {
        isConnect = true;
    }

    @Override
    public void disconnectView() {
        isConnect = false;
    }

    @Override
    public void refreshCarStates(CarStatesChangedEvent ev) {
    }

    @Override
    public void refreshSortedPlayer(PlayersSortedEvent ev) {
    }

    @Override
    public void refreshPlayerIsEliminate(PlayerIsEliminateEvent ev) {
        System.out.println("\n\n"+ev.toString());
    }

    @Override
    public void refreshRuleInformation(RuleInformationEvent ev) {
        System.out.println("\n\n"+ev.getMessage());
    }

    @Override
    public void refreshPlayerLoosePoint(PlayerLoosePointsEvent ev) {
        System.out.println("\n\n"+ev.getMessage());
        System.out.println();
    }

    @Override
    public void refreshCircuit(CircuitChangedEvent ev) {
        System.out.println("\n"+ev.getCircuit().getName()+" is the selected circuit !");
    }

    @Override
    public void refreshPlayerFinishedLap(PlayerFinishedALapEvent ev) {
        System.out.println("\n"+ev.getPlayer().getPlayerName()+" has finished the lap number : "+(ev.getLap()-1));
    }

    @Override
    public void refreshPlayerWon(PlayerWonEvent ev) {
        System.out.println("\n"+ev.getPlayer().getPlayerName()+" has finished his course ! Felicitations");
    }

    @Override
    public void refreshDivinMode(DivinModeChangedEvent ev) {
        boolean divinMode = ev.getGame().getDivinMode();
        if(divinMode){
            System.out.println("Divin Mode activate");
        }else{
            System.out.println("Divin Mode desactivate");
        }
    }

    @Override
    public void refreshGameOver(GameOverEvent ev) {
        System.out.println("\n\nGame Over \n\n Results :\n");
        Game game = ev.getGame();
        Collection<Player> winPlayers = game.getPlayersManager().getWinPlayers();
        Collection<Player> loosePlayers = game.getPlayersManager().getLoosePlayers();
        int index = 1;
        System.out.println("\n\tWin Players : ");
        for (Player player : winPlayers) {
            System.out.println("\n\t"+index+". "+player.getPlayerName()+" terminate the race in "+player.getNumberOfThrow()+" throws");
            index++;
        }
        index = 1;
        System.out.println("\n\tLoose Players : ");
        for (Player player : loosePlayers) {
            System.out.println("\n\t"+index+". "+player.getPlayerName()+" loose the race "+player.getNumberOfThrow()+" throws");
            index++;
        }
    }

    @Override
    @Subscribe
    public void refreshCell(ScrapOnCellEvent ev) {
        System.out.println("Scrap on Cell : "+ev.getScrapOnCell().getComonId());
    }
}
