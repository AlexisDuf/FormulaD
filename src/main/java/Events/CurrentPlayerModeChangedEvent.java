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
public class CurrentPlayerModeChangedEvent {
    private Player player;
    private PlayersManager playersManager;

    public CurrentPlayerModeChangedEvent(PlayersManager playersManager, Player currentPlayer) {
        this.playersManager = playersManager;
        this.player = currentPlayer;
    }

    public Player getPlayer() {
        return player;
    }
    
    public PlayersManager getPlayersManager(){
        return this.playersManager;
    }
    
    
}
