/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Players;

import Events.CurrentPlayerLevelChangedEvent;
import Events.YourTurnEvent;
import Model.AILevels;
import Model.Cars.Car;
import Model.Game;
import Model.Players.AI.AIInterface;
import com.google.common.eventbus.EventBus;

/**
 *
 * @author matt
 */
public class ComputerPlayer extends Player {
    
    public static AILevels defaultLevel= AILevels.Beginner;
    
    private AILevels level;
    private AIInterface engine;

    public ComputerPlayer() {
    }

    public ComputerPlayer(String _name, Car _car, int _classementPosition, AILevels level, EventBus eventBus, Game game) {
        super(_name, _car, _classementPosition, eventBus, game);
        this.level = level;
        engine = level.getIA(game, this);
    }
    
    
    
    /*
     * Setters 
     */
    
    
    public static void setDefaultLevel(AILevels defaultLevel) {
        ComputerPlayer.defaultLevel = defaultLevel;
    }

    
    
    public void setLevel(AILevels level) {
        this.level = level;
        this.game.getEventBusHandler().getEventBus().post(new CurrentPlayerLevelChangedEvent(this));
    }
    
    /*
     * Getters
     */
    public AILevels getLevel() {
        return this.level;
    }

    public static AILevels getDefaultLevel() {
        return defaultLevel;
    }
    
    
    
    /*
     * Utils
     */
    
    @Override
    public ComputerPlayer clone(){
        ComputerPlayer clone = new ComputerPlayer(playerName, car, classementPosition, level, this.game.getEventBusHandler().getEventBus(), game);
        clone.setTraceback(this.traceback);
        clone.setClassementPosition(this.classementPosition);
        return clone;
    }

    @Override
    public void startOperations(YourTurnEvent ev) {
        super.startOperations(ev);
        if(getToken().equals(ev.getToken())){
            engine.startOperations();
        }
        
    }
    
    
    
}
