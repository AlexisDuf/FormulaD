/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Players;

import Events.YourTurnEvent;
import Model.Cars.Car;
import Model.Game;
import Model.Map.Map;
import Model.Traceback;
import com.google.common.eventbus.EventBus;
import java.io.Serializable;

/**
 *
 * @author matt
 */
public class HumanPlayer extends Player implements Serializable{


    public HumanPlayer() {
    }

    public HumanPlayer(String _name, Car _car, int _classementPosition, EventBus eventBus, Game game) {
        super(_name, _car, _classementPosition, eventBus, game);
    }

    
    /*
     * Utils
     */
    
    @Override
    public HumanPlayer clone(){
        HumanPlayer clone = new HumanPlayer(playerName, car, classementPosition, this.game.getEventBusHandler().getEventBus(), game);
        clone.setTraceback(this.traceback);
        return clone;
    }

    @Override
    public void startOperations(YourTurnEvent ev) {
        super.startOperations(ev);
    }
    
    
    
}
