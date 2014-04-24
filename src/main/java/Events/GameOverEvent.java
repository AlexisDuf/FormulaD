/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Events;

import Model.Game;

/**
 *
 * @author wasson
 */
public class GameOverEvent {
    private Game game;

    public GameOverEvent(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
    
    
}
