/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.BoardView.RightContainer.CenterContainer;

import Controller.ChangeSelectedPlayerController;
import Events.CarPositionChangedEvent;
import Events.CarStatesChangedEvent;
import Events.ChangedClassementEvent;
import Events.CreateViewEvent;
import Events.CurrentPlayerCarSpeedChanged;
import Events.CurrentPlayerChangedEvent;
import Events.SelectedPlayerChangedEvent;
import Events.ThrowCurrentPlayerDiceEvent;
import Events.CurrentPlayerDiceValueChangedEvent;
import Events.DivinModeChangedEvent;
import Events.GameOverEvent;
import Events.PlayerFinishedALapEvent;
import Events.PlayerIsEliminateEvent;
import Events.PlayerLoosePointsEvent;
import Events.PlayerWonEvent;
import Events.PlayersSortedEvent;
import Events.RuleInformationEvent;
import Events.ScrapOnCellEvent;
import Events.SelectedBendChangedEvent;
import Model.Players.Player;
import View.AbstractFormulaDView;
import View.AbstractGameView;
import com.google.common.eventbus.Subscribe;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class PlayerGameIconContainer extends JPanel implements AbstractGameView, AbstractFormulaDView {

    private HashMap<String, PlayerGameIcon> playersIcon = new HashMap<>();
    private PlayerGameIcon currentPlayer;

    public PlayerGameIconContainer() {
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(114, 167, 178));
        this.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
    }

    @Override
    public void createView(CreateViewEvent ev) {
        Collection<Player> players = ev.getSender().getPlayersManager().getPlayers();
        for (Player player : players) {
            PlayerGameIcon currentPlayerGameIcon = new PlayerGameIcon(player);
            currentPlayerGameIcon.addActionListener(new ChangeSelectedPlayerController(ev.getSender(), player));
            this.add(currentPlayerGameIcon);
            if (player == ev.getSender().getPlayersManager().getCurrentPlayer()) {
                this.currentPlayer = currentPlayerGameIcon;
            }
            this.playersIcon.put(player.getPlayerName(), currentPlayerGameIcon);
        }
        this.currentPlayer.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));;
    }

    @Override
    public void refreshDice(ThrowCurrentPlayerDiceEvent ev) {
    }

    @Override
    public void refreshClassement(ChangedClassementEvent ev) {
    }

    @Override
    public void refreshCarPosition(CarPositionChangedEvent ev) {
    }

    @Override
    public void refreshCarStates(CarStatesChangedEvent ev) {
    }

    @Override
    public void refreshCurrentPlayer(CurrentPlayerChangedEvent ev) {
        this.currentPlayer.setBorder(BorderFactory.createEmptyBorder());
        this.currentPlayer = this.playersIcon.get(ev.getCurrentPlayer().getPlayerName());
        this.currentPlayer.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));
    }

    @Override
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
    @Subscribe
    public void refreshSortedPlayer(PlayersSortedEvent ev) {
        for (String mapKey : playersIcon.keySet()) {
            remove(playersIcon.get(mapKey));
        }
        Collection<Player> players = ev.getPlayersManager().getPlayers();
        for (Player player : players) {
            if(!player.isHasWin() && !player.isEliminate()){
                add(playersIcon.get(player.getPlayerName()));
            } 
        }
        revalidate();
        repaint();
    }

    @Override
    public void refreshPlayerIsEliminate(PlayerIsEliminateEvent ev) {
        refreshSortedPlayer(new PlayersSortedEvent(ev.getPlayer().getGame().getPlayersManager()));
    }

    @Override
    public void refreshRuleInformation(RuleInformationEvent ev) {
    }

    @Override
    public void refreshPlayerLoosePoint(PlayerLoosePointsEvent ev) {
    }

    @Override
    public void refreshPlayerFinishedLap(PlayerFinishedALapEvent ev) {
    }

    @Override
    public void refreshPlayerWon(PlayerWonEvent ev) {
        PlayerGameIcon playerIcon = this.playersIcon.remove(ev.getPlayer().getPlayerName());
        this.remove(playerIcon);
        repaint();
        revalidate();
    }

    @Override
    public void refreshDivinMode(DivinModeChangedEvent ev) {
    }

    @Override
    public void refreshGameOver(GameOverEvent ev) {
    }

    @Override
    public void refreshCell(ScrapOnCellEvent ev) {
    }
}
