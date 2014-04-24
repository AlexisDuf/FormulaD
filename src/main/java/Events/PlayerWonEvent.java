/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Events;

import Model.Players.Player;

/**
 *
 * @author wasson
 */
public class PlayerWonEvent {
    private Player player;

    public PlayerWonEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    
    
}
