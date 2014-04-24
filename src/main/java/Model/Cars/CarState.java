/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cars;

import java.io.Serializable;

/**
 *
 * @author matt
 */
public class CarState implements Serializable{
    
    /*
     * ATTRIBUTS
     */
    
    private String name;
    private int currentPdv;
    private int maxPdv;
    
    /*
     * CONSTRUCTORS
     */
    public CarState() {
        
    }

    public CarState(String name, int currentPdv, int maxPdv) {
        this.name = name;
        this.currentPdv = currentPdv;
        this.maxPdv = maxPdv;
    }
        
    
    /*
     * GETTERS
     */
    public int getCurrentPdv() {
        return currentPdv;
    }
    
    public String getName(){
        return this.name;
    }
    
    /*
     * SETTERS
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentPdv(int currentPdv) {
        this.currentPdv = currentPdv;
    }
    
    
    
    /*
     * UTILS FUNCTIONS
     */
    
    public void addPoints(int numberOfPoints){
        int result = currentPdv + numberOfPoints;
        if(result <= maxPdv){
            this.currentPdv += numberOfPoints;
        }else{
            // Exceptions
        }
        
    }
    
    public void removePoints(int numberOfPoints){
        int result = currentPdv - numberOfPoints;
        if(result >= 0 ){
            this.currentPdv -= numberOfPoints;
        }else{
            // Exception
        }
        
    }
            
}
