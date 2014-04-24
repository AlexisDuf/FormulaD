/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Game;
import Model.Map.Cell;

/**
 *
 * @author matt
 */
public class ScrapOnCellEvent {
    private Cell scrapOnCell;
    private Game game;

    public ScrapOnCellEvent(Cell scrapOnCell, Game game) {
        this.scrapOnCell = scrapOnCell;
        this.game = game;
    }

    public Cell getScrapOnCell() {
        return scrapOnCell;
    }

    public Game getGame() {
        return game;
    }
    
    
}
