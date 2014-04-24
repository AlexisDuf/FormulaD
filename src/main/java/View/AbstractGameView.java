/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Events.CarPositionChangedEvent;
import Events.CarStatesChangedEvent;
import Events.ChangedClassementEvent;
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
import com.google.common.eventbus.Subscribe;

/**
 *
 * @author matt
 */
public interface AbstractGameView extends AbstractFormulaDView {
    
    @Subscribe
    public void refreshDice(ThrowCurrentPlayerDiceEvent ev);
    
    @Subscribe
    public void refreshClassement(ChangedClassementEvent ev);
    
    @Subscribe
    public void refreshCarPosition(CarPositionChangedEvent ev);
    
    @Subscribe
    public void refreshCurrentPlayer(CurrentPlayerChangedEvent ev);
    
    @Subscribe
    public void refreshSelectedPlayer(SelectedPlayerChangedEvent ev);
    
    @Subscribe
    public void refreshCurrentPlayerDiceValue(CurrentPlayerDiceValueChangedEvent ev);
    
    @Subscribe
    public void refreshCurrentPlayerCarSpeed(CurrentPlayerCarSpeedChanged ev);
    
    @Subscribe
    public void refreshSelectedBend(SelectedBendChangedEvent ev);
    
    @Subscribe
    public void refreshCarStates(CarStatesChangedEvent ev);
    
    @Subscribe
    public void refreshSortedPlayer(PlayersSortedEvent ev);
    
    @Subscribe
    public void refreshPlayerIsEliminate(PlayerIsEliminateEvent ev);
    
    @Subscribe
    public void refreshRuleInformation(RuleInformationEvent ev);
    
    @Subscribe
    public void refreshPlayerLoosePoint(PlayerLoosePointsEvent ev);
    
    @Subscribe
    public void refreshPlayerFinishedLap(PlayerFinishedALapEvent ev);
    
    @Subscribe
    public void refreshPlayerWon(PlayerWonEvent ev);
    
    @Subscribe
    public void refreshDivinMode(DivinModeChangedEvent ev);
    
    @Subscribe
    public void refreshGameOver(GameOverEvent ev);
    
    @Subscribe
    public void refreshCell(ScrapOnCellEvent ev);
    
}
