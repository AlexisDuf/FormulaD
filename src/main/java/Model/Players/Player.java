/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Players;

import Events.CurrentPlayerCarChangedEvent;
import Events.PlayerNameChangedEvent;
import Events.ThrowCurrentPlayerDiceEvent;
import Events.CurrentPlayerHavePlayedEvent;
import Events.YourTurnEvent;
import Model.Cars.Car;
import Model.Cars.CarSpeed;
import Model.Game;
import Model.SnapShot;
import Model.Traceback;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.io.Serializable;

/**
 *
 * @author matt
 */
public abstract class Player implements Serializable {

    public static String defaultPlayerName = "undefined";
    private static Integer numberForName = new Integer(0);

    public static String getNextDefaultPlayerName() {
        numberForName++;
        return defaultPlayerName + numberForName.toString();
    }

    /*
     * Attributs
     */
    protected String playerName;
    protected Car car;
    protected int classementPosition;
    protected Traceback traceback;
    protected int numberOfThrow = 0;
    protected int currentDiceValue = 0;
    protected transient Game game;
    protected String token = "";
    protected int lapNumber = 1;
    protected transient EventBus eventBus;
    
    protected boolean eliminate = false;
    protected boolean hasWin = false;

    /*
     * Constructors
     */
    public Player() {
    }

    public Player(String _name, Car _car, int _classementPosition, EventBus eventBus, Game game) {
        this.eventBus = eventBus;
        this.playerName = _name;
        this.car = _car;
        _car.setPlayer(this);
        this.car.setAvailable(false);
        this.classementPosition = _classementPosition;
        this.traceback = new Traceback();
        this.traceback.setNewSnapShot(new SnapShot(0, null, CarSpeed.FirstSpeed, lapNumber));
        this.game = game;
    }

    /*
     * Getters
     */
    public String getPlayerName() {
        return this.playerName;
    }

    public Car getCar() {
        return car;
    }

    public int getClassementPosition() {
        return classementPosition;
    }

    public Traceback getTraceback() {
        return traceback;
    }

    public String getToken() {
        return this.token;
    }

    public int getLapNumber() {
        return this.lapNumber;
    }

    public boolean isEliminate() {
        return eliminate;
    }

    public int getNumberOfThrow() {
        return this.numberOfThrow;
    }

    public int getCurrentDiceValue() {
        return this.currentDiceValue;
    }

    public Game getGame() {
        return this.game;
    }

    public EventBus getEventBus() {
        return this.eventBus;
    }

    public boolean isHasWin() {
        return hasWin;
    }
    

    public static String getDefaultPlayerName() {
        return defaultPlayerName;
    }

    public static Integer getNumberForName() {
        return numberForName;
    }

    public void setHasWin(boolean hasWin) {
        this.hasWin = hasWin;
    }
    
    

    /*
     * Setters
     */
    public void setName(String name) {
        this.playerName = name;
        this.game.getEventBusHandler().getEventBus().post(new PlayerNameChangedEvent(this));
    }

    public final void setCar(Car _car) {
        if (_car.isAvailable()) {
            this.car.setAvailable(true);
            this.car.resetDefaultValues();
            this.car = _car;
            this.car.setPlayer(this);
            _car.setAvailable(false);
        } else {
            //Exceptions
        }

        this.game.getEventBusHandler().getEventBus().post(new CurrentPlayerCarChangedEvent(this));
    }

    public void setClassementPosition(int classementPosition) {
        this.classementPosition = classementPosition;
    }

    public void setTraceback(Traceback traceback) {
        this.traceback = traceback;
    }

    /**
     * If the player is eliminated
     *
     * @param eliminate
     */
    public void setEliminate(boolean eliminate, String cause) {
        this.eliminate = eliminate;
        if (eliminate) {
            this.game.getPlayersManager().playerEliminated(this, cause);
        }

    }

    public void setCurrentDiceValue(int currentDiceValue) {
        this.currentDiceValue = currentDiceValue;
        this.traceback.getLastSnapShot().setValueDice(currentDiceValue);
        this.game.getEventBusHandler().getEventBus().post(new ThrowCurrentPlayerDiceEvent(currentDiceValue,
                this.game.getCurrentMap().getDestinations(getCar().getPosition(),
                currentDiceValue, this.game),
                this.getCar().getSpeed().getDice()));
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLapNumber(int lapNumber) {
        this.lapNumber = lapNumber;
        if(lapNumber > Game.numberMaxLaps){
            this.game.getPlayersManager().playerWin(this);
        }
    }

    public static void setDefaultPlayerName(String defaultPlayerName) {
        Player.defaultPlayerName = defaultPlayerName;
    }

    public static void setNumberForName(Integer numberForName) {
        Player.numberForName = numberForName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setNumberOfThrow(int numberOfThrow) {
        this.numberOfThrow = numberOfThrow;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    /*
     * Fonction commune à tout les joueurs
     */
    public void throwDice() {
        SnapShot lastSnapShot;
        int newDiceValue = this.getCar().getSpeed().getDice().getNumber();
        this.setCurrentDiceValue(newDiceValue);
        this.numberOfThrow++;
    }

    @Subscribe
    public void startOperations(YourTurnEvent ev) {
        if (this.numberOfThrow > 0) {
            this.car.setCanIncrementSpeed(true);
        }
    }

    public void endOfOperations() {
        this.game.getEventBusHandler().getEventBus().post(new CurrentPlayerHavePlayedEvent());
    }
}
