/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Events.ApplicationStateChangedEvent;
import Events.CircuitChangedEvent;
import Events.CurrentPlayerHavePlayedEvent;
import Events.DivinModeChangedEvent;
import Events.GameOverEvent;
import Events.WeatherChangedEvent;
import Model.Cars.CarStates;
import Model.Players.PlayersManager;
import Model.Configuration.Weather;
import Model.MVC.AbstractModel;
import Model.MVC.EventBusHandler;
import Model.Map.Cell;
import Model.Map.Map;
import Model.Players.Player;
import Model.RecordingGame.Score;
import Model.RecordingGame.ScoresManager;
import Model.Rules.AbstractRulesManager;
import Model.Rules.LinkedListRulesManager;
import Model.Rules.MovementRule;
import Model.Rules.SpeedRule;
import Tools.XMLTools;
import com.google.common.eventbus.Subscribe;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matt
 */
public class Game extends AbstractModel implements Serializable {

    /**
     * *************************************************
     ************ ATTRIBUTS *****************
    ***************************************************
     */
    private Weather currentWeather;
    private Map currentMap;
    private ApplicationState applicationState;
    private boolean GameIsOn = false;
    private PlayersManager playersManager;
    private Circuit circuit;
    private transient CircuitManager circuitManager;
    private transient AbstractRulesManager rulesManager;
    private transient ScoresManager scoresManager;
    private boolean divinMode = false;
    
    public static int minNumerOfPlayer = 2;
    public static int maxNumerOfPlayer = 10;
    
    public static int numberMaxLaps = 3;
    public static String xmlFolder="./game/";

    /**
     * ********************************
     * ********* CONSTRUCTORS *********
     *********************************
     */
    public Game() {
        this.applicationState = ApplicationState.MainMenu; // Par défaut on affiche le menu principal
        this.circuitManager = new CircuitManager(this.eventBusHandler, this);
        this.playersManager = new PlayersManager(this.eventBusHandler.getEventBus(), this);
        this.rulesManager = new LinkedListRulesManager(this);
        this.currentWeather = Weather.Sun; // Par défaut 
        this.setCircuit(this.circuitManager.getDefaultCircuit());
        this.scoresManager = new ScoresManager(this.eventBusHandler.getEventBus());
        this.eventBusHandler.getEventBus().register(this);
    }

    /**
     * *********************************
     ************ GETTEURS ************
     **********************************
     */
    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public AbstractRulesManager getRules() {
        return rulesManager;
    }

    public ApplicationState getApplicationState() {
        return this.applicationState;
    }

    public CircuitManager getCircuitManager() {
        return this.circuitManager;
    }

    public int getNumberOfPlayers() {
        return this.playersManager.getNnumberOfPlayers();
    }

    public PlayersManager getPlayersManager() {
        return this.playersManager;
    }

    public boolean isGameIsOn() {
        return GameIsOn;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public static int getMinNumerOfPlayer() {
        return minNumerOfPlayer;
    }

    public static int getMaxNumerOfPlayer() {
        return maxNumerOfPlayer;
    }

    public boolean getDivinMode() {
        return this.divinMode;
    }

    /**
     * *********************************
     ************ SETTEURS ************
     **********************************
     */
    public void setCurrentWeather(Weather currentWeather) {
        this.currentWeather = currentWeather;
        this.eventBusHandler.getEventBus().post(new WeatherChangedEvent(currentWeather));
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

    public void setRules(AbstractRulesManager rules) {
        this.rulesManager = rules;
    }

    public void setApplicationState(ApplicationState _applicationState) {
        if(this.applicationState == ApplicationState.Game && _applicationState == ApplicationState.MainMenu){
            reinit();
        }
        this.applicationState = _applicationState;
        if (this.applicationState == ApplicationState.Game && !GameIsOn) {
            initGame();
        }
        this.eventBusHandler.getEventBus().post(new ApplicationStateChangedEvent(this));
    }

    public void setCircuit(Circuit _circuit) {
        this.circuit = _circuit;
        this.setCurrentMap(new Map(this.circuit.getFolderPath()));
        this.eventBusHandler.getEventBus().post(new CircuitChangedEvent(circuit));
    }

    public void setWeather(Weather _weather) {
        this.currentWeather = _weather;
    }

    public void setGameIsOn(boolean GameIsOn) {
        this.GameIsOn = GameIsOn;
    }

    public void setPlayersManager(PlayersManager playersManager) {
        this.playersManager = playersManager;
    }

    public void setCircuitManager(CircuitManager circuitManager) {
        this.circuitManager = circuitManager;
    }

    public static void setMinNumerOfPlayer(int minNumerOfPlayer) {
        Game.minNumerOfPlayer = minNumerOfPlayer;
    }

    public static void setMaxNumerOfPlayer(int maxNumerOfPlayer) {
        Game.maxNumerOfPlayer = maxNumerOfPlayer;
    }

    public void setDivinMode(boolean divinMode) {
        this.divinMode = divinMode;
        this.eventBusHandler.getEventBus().post(new DivinModeChangedEvent(this));
    }

    /*
     * LaunchGame
     */
    private void initGame() {
        Collection<Player> players = this.playersManager.getUnsortPlayers();
        Collections.shuffle((List) players);
        Iterator<Cell> startGridIterator = currentMap.getStartGrid().iterator();
        Cell currentPosition = startGridIterator.next();
        for (Player player : players) {
            this.eventBusHandler.getEventBus().register(player);
            LinkedList<Cell> path = new LinkedList<>();
            path.add(currentPosition);
            player.getCar().setPosition(currentPosition, path);
            currentPosition = startGridIterator.next();
        }
        this.playersManager.setPlayers(players);
        initRules();
        this.eventBusHandler.getEventBus().register(this.rulesManager);
        this.GameIsOn = true;
    }

    private void initRules() {
        this.rulesManager.addRule(new MovementRule(this.eventBusHandler.getEventBus()));
        this.rulesManager.addRule(new SpeedRule(this.eventBusHandler.getEventBus()));
    }

    public AbstractRulesManager getRulesManager() {
        return rulesManager;
    }
    
    public void end() {
        //to call at the end 
       this.GameIsOn = false;
       this.scoresManager.addSore(new Score(this.getPlayersManager().getWinPlayers(), 
                                            this.getPlayersManager().getLoosePlayers(),
                                            new Date(),
                                            this.getCircuit().getName()));
        this.eventBusHandler.getEventBus().post(new GameOverEvent(this));
    }
    
    private void reinit(){
        this.playersManager = new PlayersManager(this.getEventBusHandler().getEventBus(), this);
        this.currentWeather = Weather.Sun;
        this.setCircuit(this.circuitManager.getDefaultCircuit());
    }
    

    public ScoresManager getScoresManager() {
        return scoresManager;
    }

    public boolean isDivinMode() {
        return divinMode;
    }

    public static int getNumberMaxLaps() {
        return numberMaxLaps;
    }

    public static String getXmlFolder() {
        return xmlFolder;
    }

    public void setRulesManager(AbstractRulesManager rulesManager) {
        this.rulesManager = rulesManager;
    }

    public void setScoresManager(ScoresManager scoresManager) {
        this.scoresManager = scoresManager;
    }

    public static void setNumberMaxLaps(int numberMaxLaps) {
        Game.numberMaxLaps = numberMaxLaps;
    }

    public static void setXmlFolder(String xmlFolder) {
        Game.xmlFolder = xmlFolder;
    }
    
    
    

    @Subscribe
    public void currentPlayerHavedPlayed(CurrentPlayerHavePlayedEvent ev) {
        /*
         * Lorsque le joueur courant a joué
         */
        Player nextPlayer;
        do{
            nextPlayer = getPlayersManager().getNextCurrentPlayer();
        }while(nextPlayer.isEliminate() || nextPlayer.isHasWin());
        
        this.getPlayersManager().setCurrentPlayer(nextPlayer);

    }

    public void saveGame() {
        try {
            /*
             * Game
             */
            XMLTools.applyTransient(Game.class, "scoresManager");
            XMLTools.applyTransient(Game.class, "rulesManager");
            XMLTools.applyTransient(Game.class, "circuitManager");
            /*
             * Player
             */
            XMLTools.applyTransient(Player.class, "game");
            /*
             * CarStates
             */
            XMLTools.applyTransient(CarStates.class, "eventBus");
            /*
             * PlayersManager and Player
             */
            XMLTools.applyTransient(PlayersManager.class, "game");
            XMLTools.applyTransient(PlayersManager.class, "eventBus");
            XMLTools.applyTransient(Player.class, "game");
            XMLTools.applyTransient(Player.class, "eventBus");


            XMLTools.encodeToFile(this.GameIsOn, xmlFolder+"gameIsOn.xml");
            XMLTools.encodeToFile(this.applicationState, xmlFolder+"applicationState.xml");
            XMLTools.encodeToFile(this.circuit, xmlFolder+"circuit.xml");
            XMLTools.encodeToFile(this.currentMap, xmlFolder+"map.xml");
            XMLTools.encodeToFile(this.currentWeather, xmlFolder+"weather.xml");
            XMLTools.encodeToFile(this.divinMode, xmlFolder+"divinMode.xml");
            XMLTools.encodeToFile(this.eventBusHandler, xmlFolder+"eventBus.xml");
            XMLTools.encodeToFile(this.playersManager,xmlFolder+"playersManager.xml");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CircuitManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Game loadGame() {
        Game toReturn = new Game();
        try {
            toReturn.GameIsOn = (boolean)XMLTools.decodeToFile(xmlFolder+"gameIsOn.xml");
            toReturn.applicationState = (ApplicationState)XMLTools.decodeToFile(xmlFolder+"applicationState.xml");
            toReturn.circuit = (Circuit)XMLTools.decodeToFile(xmlFolder+"circuit.xml");
            toReturn.currentMap = (Map)XMLTools.decodeToFile(xmlFolder+"map.xml");
            toReturn.currentWeather = (Weather)XMLTools.decodeToFile(xmlFolder+"weather.xml");
            toReturn.currentWeather = (Weather)XMLTools.decodeToFile(xmlFolder+"weather.xml");
            toReturn.divinMode = (boolean)XMLTools.decodeToFile(xmlFolder+"divinMode.xml");
            toReturn.eventBusHandler = (EventBusHandler)XMLTools.decodeToFile(xmlFolder+"eventBus.xml");
            toReturn.playersManager = (PlayersManager)XMLTools.decodeToFile(xmlFolder+"playersManager.xml");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toReturn;
    }

    public static boolean gameIsSaved() {
        return true;
    }

    
}
