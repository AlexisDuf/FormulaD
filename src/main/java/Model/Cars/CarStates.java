/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cars;

import Events.CarStatesChangedEvent;
import Model.Players.Player;
import com.google.common.eventbus.EventBus;
import java.util.HashMap;

/**
 *
 * @author matt
 */
public class CarStates {
    /*
     * Attributs
     */
    
    private HashMap<String, CarState> states; // Tableau associatif
    
    /*
     * Startd PDV 
     * (On régle ici les points de vie de base des différentes caratéristiques)
     */
    
    public static int startPdvPneumatics = 6;
    public static int startPdvBrakes = 3;
    public static int startPdvLimpWithSpeed = 3;
    public static int startPdvBody = 3;
    public static int startPdvEngine = 3;
    public static int startPdvRoadHolding = 2;
    
    public static int maxPdvPneumatics = 14;
    public static int maxPdvBrakes = 7;
    public static int maxPdvLimpWithSpeed = 7;
    public static int maxPdvBody = 7;
    public static int maxPdvEngine = 7;
    public static int maxPdvRoadHolding = 7;
    
    private EventBus eventBus;
    private Player player;
    
    /*
     * Constructors
     */
 
        /*
         * Création d'un nouveau tableau de contrôle avec les points de vie de base
         */

    public CarStates() {
        this.states = new HashMap<>();
        this.states.put("pneumactics", new CarState("Pneumatics",startPdvPneumatics, maxPdvPneumatics));
        this.states.put("brakes", new CarState("Brakes",startPdvBrakes, maxPdvBrakes));
        this.states.put("limpWithSpeed", new CarState("Limp with speed",startPdvLimpWithSpeed, maxPdvLimpWithSpeed));
        this.states.put("body", new CarState("Body",startPdvBody, maxPdvBody));
        this.states.put("engine", new CarState("Engine",startPdvEngine, maxPdvEngine));
        this.states.put("roadHolding", new CarState("Road Holding",startPdvRoadHolding, maxPdvRoadHolding));
    }

    public HashMap<String, CarState> getStates() {
        return states;
    }
    
    
    /*
     * GETTERS
     */
    public static int getStartPdvPneumatics() {
        return startPdvPneumatics;
    }

    public static int getStartPdvBrakes() {
        return startPdvBrakes;
    }

    public static int getStartPdvLimpWithSpeed() {
        return startPdvLimpWithSpeed;
    }

    public static int getStartPdvBody() {
        return startPdvBody;
    }

    public static int getStartPdvEngine() {
        return startPdvEngine;
    }

    public static int getStartPdvRoadHolding() {
        return startPdvRoadHolding;
    }

    public static int getMaxPdvPneumatics() {
        return maxPdvPneumatics;
    }

    public static int getMaxPdvBrakes() {
        return maxPdvBrakes;
    }

    public static int getMaxPdvLimpWithSpeed() {
        return maxPdvLimpWithSpeed;
    }

    public static int getMaxPdvBody() {
        return maxPdvBody;
    }

    public static int getMaxPdvEngine() {
        return maxPdvEngine;
    }

    public static int getMaxPdvRoadHolding() {
        return maxPdvRoadHolding;
    }
    
    /*
     * SETTERS
     */
    public void setStates(HashMap<String, CarState> states) {
        this.states = states;
    }

    public static void setStartPdvPneumatics(int startPdvPneumatics) {
        CarStates.startPdvPneumatics = startPdvPneumatics;
    }

    public static void setStartPdvBrakes(int startPdvBrakes) {
        CarStates.startPdvBrakes = startPdvBrakes;
    }

    public static void setStartPdvLimpWithSpeed(int startPdvLimpWithSpeed) {
        CarStates.startPdvLimpWithSpeed = startPdvLimpWithSpeed;
    }

    public static void setStartPdvBody(int startPdvBody) {
        CarStates.startPdvBody = startPdvBody;
    }

    public static void setStartPdvEngine(int startPdvEngine) {
        CarStates.startPdvEngine = startPdvEngine;
    }

    public static void setStartPdvRoadHolding(int startPdvRoadHolding) {
        CarStates.startPdvRoadHolding = startPdvRoadHolding;
    }

    public static void setMaxPdvPneumatics(int maxPdvPneumatics) {
        CarStates.maxPdvPneumatics = maxPdvPneumatics;
    }

    public static void setMaxPdvBrakes(int maxPdvBrakes) {
        CarStates.maxPdvBrakes = maxPdvBrakes;
    }

    public static void setMaxPdvLimpWithSpeed(int maxPdvLimpWithSpeed) {
        CarStates.maxPdvLimpWithSpeed = maxPdvLimpWithSpeed;
    }

    public static void setMaxPdvBody(int maxPdvBody) {
        CarStates.maxPdvBody = maxPdvBody;
    }

    public static void setMaxPdvEngine(int maxPdvEngine) {
        CarStates.maxPdvEngine = maxPdvEngine;
    }

    public static void setMaxPdvRoadHolding(int maxPdvRoadHolding) {
        CarStates.maxPdvRoadHolding = maxPdvRoadHolding;
    }
    
    
    
    
    /*
     * ADD POINTS
     * 
     * Permet de cacher le modèle interne, si on veut changer la modélisation
     * des states (tableau plutot que HashMap par exemple) il suffira juste de
     * changer ces fonctions là
     */
   
    public void addPneumaticsPoints(int numberOfPoints){
        this.states.get("pneumactics").addPoints(numberOfPoints);
        if(this.player !=null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
  
    public void addBrakesPoints(int numberOfPoints){
        this.states.get("brakes").addPoints(numberOfPoints);
        if(this.player !=null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
    
    public void addLimpWithSpeedPoints(int numberOfPoints){
        this.states.get("limpWithSpeed").addPoints(numberOfPoints);
        if(this.player !=null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
    
    public void addBodyPoints(int numberOfPoints){
        this.states.get("body").addPoints(numberOfPoints);
        if(this.player !=null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
    
    public void addEnginePoints(int numberOfPoints){
        this.states.get("engine").addPoints(numberOfPoints);
        if(this.player !=null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
    
    public void addRoadHoldingPoints(int numberOfPoints){
        this.states.get("roadHolding").addPoints(numberOfPoints);
        if(this.player !=null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
    
    /*
     * REMOVES POINTS
     */
    
    public void removePneumaticsPoints(int numberOfPoints){
        this.states.get("pneumactics").removePoints(numberOfPoints);
        if(this.player !=null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
  
    public void removeBrakesPoints(int numberOfPoints){
        this.states.get("brakes").removePoints(numberOfPoints);
        if(this.player !=null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
    
    public void removeLimpWithSpeedPoints(int numberOfPoints){
        this.states.get("limpWithSpeed").removePoints(numberOfPoints);
        if(this.player !=null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
    
    public void removeBodyPoints(int numberOfPoints){
        this.states.get("body").removePoints(numberOfPoints);
        if(this.player !=null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
    
    public void removeEnginePoints(int numberOfPoints){
        this.states.get("engine").removePoints(numberOfPoints);
        if(this.player !=null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
    
    public void removeRoadHoldingPoints(int numberOfPoints){
        this.states.get("roadHolding").removePoints(numberOfPoints);
        if(this.player != null && this.eventBus != null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
    
    /*
    * GET CarState
    */
    
    public CarState getPneumaticState(){
        return states.get("pneumactics");        
    }
    
    public CarState getBrakesState(){
        return states.get("brakes");        
    }
    
    public CarState getLimpWithSpeedState(){
        return states.get("limpWithSpeed");        
    }
    
    public CarState getBodyState(){
        return states.get("body");        
    }
    
    public CarState getEngineState(){
        return states.get("engine");        
    }
    
    public CarState getRoadHoldingState(){
        return states.get("roadHolding");        
    }
    
    
    public void setPlayer(Player player){
        this.player = player;
        this.eventBus = player.getEventBus();
    }

    public void resetPdvStates() {
        getBodyState().setCurrentPdv(startPdvBody);
        getBrakesState().setCurrentPdv(startPdvBrakes);
        getEngineState().setCurrentPdv(startPdvEngine);
        getLimpWithSpeedState().setCurrentPdv(startPdvLimpWithSpeed);
        getPneumaticState().setCurrentPdv(startPdvPneumatics);
        getRoadHoldingState().setCurrentPdv(startPdvRoadHolding);
        if(this.player !=null){
            this.eventBus.post(new CarStatesChangedEvent(player));
        }
    }
    
    public String toString(){
        String result = "";
        result+= "\n\nPlayer : "+this.player.getPlayerName();
        result+="\nCar : "+this.player.getCar().getName();
        result+="\nCar caracteristics : ";
            result+="\n\tBody : "+this.getBodyState().getCurrentPdv()+"/"+CarStates.maxPdvBody;
            result+="\n\tBrakes : "+this.getBrakesState().getCurrentPdv()+"/"+CarStates.maxPdvBrakes;
            result+="\n\tEngine : "+this.getEngineState().getCurrentPdv()+"/"+CarStates.maxPdvEngine;
            result+="\n\tLimp with speed : "+this.getLimpWithSpeedState().getCurrentPdv()+"/"+CarStates.maxPdvLimpWithSpeed;
            result+="\n\tPneumatics : "+this.getPneumaticState().getCurrentPdv()+"/"+CarStates.maxPdvPneumatics;
            result+="\n\tRoad holding : "+this.getRoadHoldingState().getCurrentPdv()+"/"+CarStates.maxPdvRoadHolding;
       return result;
    }
    
}
