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
import java.util.LinkedList;

/**
 *
 * @author matt
 */
public class LinkedListRulesManager extends AbstractRulesManager {

    private LinkedList<AbstractRule> rules;

    public LinkedListRulesManager(Game game) {
        super(game);
        rules = new LinkedList<>();
    }

    @Override
    @Subscribe
    public void applyRulesAfterSpeedSelection(CurrentPlayerCarSpeedChanged ev) {
        if (this.game.isGameIsOn()) {
            for (AbstractRule currentRule : rules) {
                currentRule.applyRuleAfterSpeedSelection(game
                        .getPlayersManager()
                        .getCurrentPlayer());
            }
        }

    }

    @Override
    @Subscribe
    public void applyRulesAfterDiceThrow(ThrowCurrentPlayerDiceEvent ev) {
        if (this.game.isGameIsOn()) {
            for (AbstractRule currentRule : rules) {
                currentRule.applyRuleAfterDiceThrow(game
                        .getPlayersManager()
                        .getCurrentPlayer());
            }
        }
    }

    @Override
    @Subscribe
    public void applyRulesAfterDecision(CarPositionChangedEvent ev) {
        if (this.game.isGameIsOn()) {
            for (AbstractRule currentRule : rules) {
                currentRule.applyRuleAfterDecision(game
                        .getPlayersManager()
                        .getCurrentPlayer());
            }
        }
    }

    @Override
    @Subscribe
    public void applyRulesBeforeSpeedSelection(YourTurnEvent ev) {
        if (this.game.isGameIsOn()) {
            for (AbstractRule currentRule : rules) {
                currentRule.applyRuleBeforeSpeedSelection(game
                        .getPlayersManager()
                        .getCurrentPlayer());
            }
        }
    }

    @Override
    public void addRule(AbstractRule rule) {
        this.rules.add(rule);
    }

    @Override
    public void removeRule(AbstractRule rule) {
        this.rules.remove(rule);
    }
}
