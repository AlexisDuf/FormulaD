/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Dice;
import Model.Map.Cell;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author matt
 */
public class ThrowCurrentPlayerDiceEvent {
    private int currentDiceValue;
    private HashMap<Cell, LinkedList<Cell>> destinations;
    private Dice dice;

    public ThrowCurrentPlayerDiceEvent(int currentDiceValue, HashMap<Cell, LinkedList<Cell>> destionations, Dice dice) {
        this.currentDiceValue = currentDiceValue;
        this.destinations = destionations;
        this.dice = dice;
    }
    
    public int getCurrentDiceValue(){
        return this.currentDiceValue;
    }
    
    public HashMap<Cell,LinkedList<Cell>> getDestinations(){
        return this.destinations;
    }
    
    public Dice getDice(){
        return this.dice;
    }
}
