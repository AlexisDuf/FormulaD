/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Cars.CarSpeed;
import Model.Map.Cell;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author matt
 */
public class SnapShot {
    private int valueDice; 
    private List<Cell> route ; 
    private CarSpeed speed ;
    private int currentLap;

    public SnapShot() {
    }    

    public SnapShot(int valueDice, List<Cell> route, CarSpeed speed, int currentLap) {
        this.valueDice = valueDice;
        this.route = route;
        this.speed = speed;
        this.currentLap = currentLap;
    }    

    public int getCurrentLap() {
        return currentLap;
    }

    public void setCurrentLap(int currentLap) {
        this.currentLap = currentLap;
    }
    
    public CarSpeed getSpeed() {
        return speed;
    }

    public void setSpeed(CarSpeed speed) {
        this.speed = speed;
    }   
    
    
    public int getValueDice() {
        return valueDice;
    }

    public void setValueDice(int valueDice) {
        this.valueDice = valueDice;
    }

    public List<Cell> getRoute() {
        return new LinkedList<>(route);
    }

    public void setRoute(List<Cell> route) {
        this.route = route;
    }
    
    public Cell getLastCell(){
        if(!(route ==null)){
             return route.get(route.size()-1);
        }
        return null;
       
    }
    
    public Cell getFirstCell(){
        return route.get(1);
    }
}
