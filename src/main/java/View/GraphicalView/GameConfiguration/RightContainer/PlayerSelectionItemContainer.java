/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.GameConfiguration.RightContainer;


import Controller.ChangeAILevelCurrentComputerPlayer;
import Events.ApplicationStateChangedEvent;
import Events.CircuitAddedEvent;
import Events.CircuitChangedEvent;
import Events.CreateViewEvent;
import Events.CurrentPlayerCarChangedEvent;
import Events.CurrentPlayerChangedEvent;
import Events.CurrentPlayerModeChangedEvent;
import Events.NumberOfPlayersChanged;
import Events.PlayerNameChangedEvent;
import Events.WeatherChangedEvent;
import Model.Players.ComputerPlayer;
import Model.Players.HumanPlayer;
import Model.Players.Player;
import View.AbstractFormulaDConfigurationGameView;
import View.AbstractFormulaDView;
import com.google.common.eventbus.Subscribe;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class PlayerSelectionItemContainer extends JPanel implements AbstractFormulaDConfigurationGameView, AbstractFormulaDView{
    
    boolean isConnect = true;
    Collection<PlayerSelectionItem> playersButton;

    public PlayerSelectionItemContainer() {
        this.playersButton = new LinkedList<>();
    }

    @Override
    public boolean isConnect() {
        return this.isConnect;
    }

    @Override
    public void connectView() {
        this.isConnect = true;
    }

    @Override
    public void disconnectView() {
        this.isConnect = false;
    }

    @Override
    public void createView(CreateViewEvent ev) {
        PlayerSelectionItem currentButton;
        this.setLayout(new GridLayout(ev.getSender().getNumberOfPlayers(), 1,0,20));
        Collection<ComputerPlayer> computerPlayers = ev.getSender().getPlayersManager().getComputerPlayers();
        Collection<HumanPlayer> humanPlayers = ev.getSender().getPlayersManager().getHumanPlayers();
        
        for(ComputerPlayer currentComputerPlayer : computerPlayers){
            currentButton = new PlayerSelectionItem(ev.getSender().getPlayersManager(), currentComputerPlayer);
            currentButton.setBackground(new Color(69,69,69));
            currentButton.setForeground(Color.WHITE);
            this.add(currentButton);
            this.playersButton.add(currentButton);
        }
        
        for(HumanPlayer currentHumanPlayer : humanPlayers){
            currentButton = new PlayerSelectionItem(ev.getSender().getPlayersManager(), currentHumanPlayer);
            this.add(currentButton);
            this.playersButton.add(currentButton);
        }
    }

    @Override
    @Subscribe
    public void refreshApplicationState(ApplicationStateChangedEvent ev) {
        
    }

    @Override
    @Subscribe
    public void refreshWeather(WeatherChangedEvent ev) {
        
    }

    @Override
    @Subscribe
    public void refreshCurrentPlayer(CurrentPlayerChangedEvent ev) {
        
    }

    @Override
    @Subscribe
    public void refreshNumberOfPlayers(NumberOfPlayersChanged ev) {
        this.playersButton.clear();
        PlayerSelectionItem currentButton;
        removeAll();
        this.setLayout(new GridLayout(ev.getPlayersManager().getNnumberOfPlayers(), 1,0,20));
        Collection<ComputerPlayer> computerPlayers = ev.getPlayersManager().getComputerPlayers();
        Collection<HumanPlayer> humanPlayers = ev.getPlayersManager().getHumanPlayers();        
        
        for(ComputerPlayer currentComputerPlayer : computerPlayers){
            currentButton = new PlayerSelectionItem(ev.getPlayersManager(), currentComputerPlayer);
            currentButton.setBackground(new Color(69,69,69));
            currentButton.setForeground(Color.WHITE);
            this.add(currentButton);
            this.playersButton.add(currentButton);
        }
        
        for(HumanPlayer currentHumanPlayer : humanPlayers){
            currentButton = new PlayerSelectionItem(ev.getPlayersManager(), currentHumanPlayer);
            this.add(currentButton);
            this.playersButton.add(currentButton);
        }
        this.revalidate();
        this.repaint();
    }

    @Override
    @Subscribe
    public void refreshCircuitAdded(CircuitAddedEvent ev) {
        
    }

    @Override
    @Subscribe
    public void refreshPlayerName(PlayerNameChangedEvent ev) {
        PlayerSelectionItem button = getButton(ev.getPlayer());
        if(button != null){
           button.setText(ev.getPlayer().getPlayerName());
        }
    }
    
    private PlayerSelectionItem getButton(Player player){
        PlayerSelectionItem currentButton=null;
        boolean found = false;
        Iterator<PlayerSelectionItem> it = this.playersButton.iterator();
        while(!found && it.hasNext()){
            currentButton = it.next();
            if(currentButton.getPlayer()==player){
                found = true;
            }
        }
        return currentButton;
    }

    @Override
    public void refreshCurrentCar(CurrentPlayerCarChangedEvent ev) {
        
    }

    @Override
    @Subscribe
    public void refreshCurrentPlayerMode(CurrentPlayerModeChangedEvent ev) {
        refreshNumberOfPlayers(new NumberOfPlayersChanged(ev.getPlayersManager()));
    }

    @Override
    public void refreshCurrentComputerPlayerLevel(ChangeAILevelCurrentComputerPlayer ev) {}

    @Override
    public void refreshCircuit(CircuitChangedEvent ev) {
    }
}
