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
public class PlayerIsEliminateEvent {
    private Player player;
    private String reason;

    public PlayerIsEliminateEvent(Player player, String reason) {
        this.player = player;
        this.reason = reason;
    }
    
    @Override
    public String toString(){
        return  this.reason ;
    }

    public Player getPlayer() {
        return player;
    }

    public String getReason() {
        return reason;
    }
    
    
}
