/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Players.AI;

import Events.ThrowCurrentPlayerDiceEvent;
import Model.Cars.CarSpeed;
import Model.Game;
import Model.Map.Cell;
import Model.Players.AI.Events.ChosenSpeedEvent;
import Model.Players.AI.Events.EndOfOperationsEvent;
import Model.Players.Player;
import Model.Rules.SpeedRule;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author wasson
 */
public class GogolAI implements AIInterface {

    private Game game;
    private Player me;
    private Timer timer = new Timer();
    private EventBus internalSync = new EventBus();

    public GogolAI(Game game, Player me) {
        this.game = game;
        this.internalSync.register(this);
        this.game.getEventBusHandler().getEventBus().register(this);
        this.me = me;
    }

    @Override
    public void startOperations() {
        if(true/*game.isGameIsOn()*/){
            CarSpeed[] speeds = SpeedRule.getSpeedCanBeApllicated(me);
            Random random = new Random();
            final CarSpeed choosenSpeed = speeds[speeds.length - 1 - random.nextInt(speeds.length)];

            TimerTask chooseSpeed = new TimerTask() {

                @Override
                public void run() {
                    me.getCar().setSpeed(choosenSpeed);
                    game.getPlayersManager().setCurrentPlayerSpeedIsChoosed(true);
                    internalSync.post(new ChosenSpeedEvent());
                }
            };
            timer.schedule(chooseSpeed, 1000 );
        }
    }

    @Subscribe
    public void startDiceOperations(ChosenSpeedEvent ev) {
        me.throwDice();
    }

    @Subscribe
    public void diceOperations(ThrowCurrentPlayerDiceEvent ev) {
       
        if (me.getToken().equals(this.game.getPlayersManager().getToken())) {
            final HashMap<Cell, LinkedList<Cell>> destinations = ev.getDestinations();
            final Random random = new Random();
            TimerTask chooseDest = new TimerTask() {

                @Override
                public void run() {
                    final int max = destinations.size();
                    final int index = random.nextInt(max);
                    LinkedList<Cell> list = destinations.get(destinations.keySet().toArray()[index]);
                    Cell e = list.getLast();
                    me.getCar().setPosition(e, list);
                    internalSync.post(new EndOfOperationsEvent());
                }
            };
            timer.schedule(chooseDest, 1000);
        }
        
    }
    
    @Override
    @Subscribe
    public void endOfOperations(EndOfOperationsEvent e) {
        me.endOfOperations();
    }

}
