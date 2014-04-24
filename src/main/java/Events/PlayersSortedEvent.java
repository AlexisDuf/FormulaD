/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Players.PlayersManager;

/**
 *
 * @author matt
 */
public class PlayersSortedEvent {
    private PlayersManager playersManager;

    public PlayersSortedEvent(PlayersManager playersManager) {
        this.playersManager = playersManager;
    }

    public PlayersManager getPlayersManager() {
        return playersManager;
    }
    
    
}
