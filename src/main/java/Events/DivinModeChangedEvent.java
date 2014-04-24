/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Game;

/**
 *
 * @author matt
 */
public class DivinModeChangedEvent {
    private Game game;

    public DivinModeChangedEvent(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
    
    
}
