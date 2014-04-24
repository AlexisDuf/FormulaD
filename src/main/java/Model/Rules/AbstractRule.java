/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Rules;

import Model.Players.Player;
import com.google.common.eventbus.EventBus;

/**
 *
 * @author matt
 */
public abstract class AbstractRule {
    
    protected EventBus eventBus;

    public AbstractRule() {
    } 

    public AbstractRule(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    
    
    public abstract void applyRuleBeforeSpeedSelection(Player p);
    public abstract void applyRuleAfterSpeedSelection(Player p);
    public abstract void applyRuleAfterDiceThrow(Player p);
    public abstract void applyRuleAfterDecision(Player p);
    

}
