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
public class CurrentPlayerChangedEvent {
    private Player currentPlayer;

    public CurrentPlayerChangedEvent(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
}
