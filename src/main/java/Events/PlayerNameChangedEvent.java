/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Players.Player;

/**
 *
 * @author matt
 */
public class PlayerNameChangedEvent {
    private Player player;

    public PlayerNameChangedEvent(Player player) {
        this.player = player;
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
}
