/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Rules;

import Events.CarPositionChangedEvent;
import Events.CurrentPlayerCarSpeedChanged;
import Events.ThrowCurrentPlayerDiceEvent;
import Events.YourTurnEvent;
import Model.Game;
import com.google.common.eventbus.Subscribe;

/**
 *
 * @author matt
 */
public abstract class AbstractRulesManager {
    
    private boolean connect = true;
    protected transient Game game;

    public AbstractRulesManager(Game game) {
        this.game = game;
    }    
    
    @Subscribe
    public abstract void applyRulesBeforeSpeedSelection(YourTurnEvent ev);
    
    @Subscribe
    public abstract void applyRulesAfterSpeedSelection(CurrentPlayerCarSpeedChanged ev);
    
    @Subscribe
    public abstract void applyRulesAfterDiceThrow(ThrowCurrentPlayerDiceEvent ev);
    
    @Subscribe
    public abstract void applyRulesAfterDecision(CarPositionChangedEvent ev);
    
    
    /*
     * Rules Manager
     */
    
    public abstract void addRule(AbstractRule rule);
    public abstract void removeRule(AbstractRule rule);
    
    /*
     * RulesConnect
     */
    
    public void setConnect(boolean connect) {
        this.connect = connect;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    

    public boolean isConnect() {
        return connect;
    }

    public Game getGame() {
        return game;
    }
    
    
}
