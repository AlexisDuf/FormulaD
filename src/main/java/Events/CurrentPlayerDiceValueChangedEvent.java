/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

/**
 *
 * @author matt
 */
public class CurrentPlayerDiceValueChangedEvent {
    private int diceValue;

    public CurrentPlayerDiceValueChangedEvent(int diceValue) {
        this.diceValue = diceValue;
    }
    
    public int getDiceValue(){
        return this.diceValue;
    }
}
