/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ChangeAILevelCurrentComputerPlayer;
import Events.ApplicationStateChangedEvent;
import Events.CircuitAddedEvent;
import Events.CircuitChangedEvent;
import Events.CurrentPlayerCarChangedEvent;
import Events.CurrentPlayerChangedEvent;
import Events.CurrentPlayerModeChangedEvent;
import Events.NumberOfPlayersChanged;
import Events.PlayerNameChangedEvent;
import Events.WeatherChangedEvent;
import com.google.common.eventbus.Subscribe;

/**
 *
 * @author matt
 */
public interface AbstractFormulaDConfigurationGameView extends AbstractFormulaDView {
    
    @Subscribe
    public void refreshApplicationState(ApplicationStateChangedEvent ev);
    
    @Subscribe
    public void refreshWeather(WeatherChangedEvent ev);
    
    @Subscribe
    public void refreshCurrentPlayer(CurrentPlayerChangedEvent ev);
    
    @Subscribe
    public void refreshNumberOfPlayers(NumberOfPlayersChanged ev);
    
    @Subscribe
    public void refreshCircuitAdded(CircuitAddedEvent ev);
    
    @Subscribe
    public void refreshPlayerName(PlayerNameChangedEvent ev);
    
    @Subscribe
    public void refreshCurrentCar(CurrentPlayerCarChangedEvent ev);
    
    @Subscribe
    public void refreshCurrentPlayerMode(CurrentPlayerModeChangedEvent ev);
    
    @Subscribe
    public void refreshCurrentComputerPlayerLevel(ChangeAILevelCurrentComputerPlayer ev);
    
    @Subscribe
    public void refreshCircuit(CircuitChangedEvent ev);
    
    
    /*
     * Utils Functions
     */ 
    
    public boolean isConnect();
    public void connectView();
    public void disconnectView();
    
}
