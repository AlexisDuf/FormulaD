/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Players.Player;
import Model.Players.PlayersManager;

/**
 *
 * @author matt
 */
public class SelectedPlayerChangedEvent {
    
    private PlayersManager sender;
    private Player oldSelectedPlayer;

    public SelectedPlayerChangedEvent(PlayersManager sender, Player selectedPlayer) {
        this.sender = sender;
        this.oldSelectedPlayer = selectedPlayer;        
    }
    
    public PlayersManager getSender(){
        return this.sender;
    }
    
    public Player getOldSelectedPlayer(){
        return this.oldSelectedPlayer;
    }
    
}
