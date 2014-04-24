/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cars;

import Model.Dice;

/**
 *
 * @author matt
 */
public enum CarSpeed {
    
    FirstSpeed(Dice.YellowDice),
    SecondSpeed(Dice.OrangeDice),
    ThirdSpeed(Dice.RedDice),
    FourthSpeed(Dice.GreenDice),
    FifthSpeed(Dice.PurpleDice),
    SixthSpeed(Dice.BlueDice);
    
    /*
     * ATTRIBUTS
     */
    
    private Dice dice;
     
    /*
     * CONSTRUCTORS
     */
    
    private CarSpeed(){
        
    }
    
    private CarSpeed(Dice dice){
        this.dice = dice;
    } 
    
    /*
     * GETTEURS
     */

    public Dice getDice() {
        return dice;
    }

    /*
     * SETTEURS
     */
    public void setDice(Dice dice) {
        this.dice = dice;
    }
}
