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
public class CurrentPlayerCarSpeedChanged {
    private PlayersManager playerManager;
    private Player currentPlayer;

    public CurrentPlayerCarSpeedChanged(PlayersManager playerManager, Player currentPlayer) {
        this.playerManager = playerManager;
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public PlayersManager getPlayerManager() {
        return playerManager;
    }
    
    
    
    
}
