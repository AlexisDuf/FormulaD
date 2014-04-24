/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Map.Cell;
import Model.Players.Player;

/**
 *
 * @author matt
 */
public class CarPositionChangedEvent {
    private Player player;
    private Cell oldPosition;

    public CarPositionChangedEvent(Player player, Cell oldPosition) {
        this.player = player;
        this.oldPosition = oldPosition;
    }
    
    public Cell getNewPosition(){
        return this.player.getCar().getPosition();
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public Cell getOldPosition(){
        return this.oldPosition;
    }
}
