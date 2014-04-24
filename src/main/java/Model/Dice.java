/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;

/**
 *
 * @author matt
 */
public enum Dice{
    
    BlackDice(1,20,Color.BLACK,"Black"),
    YellowDice(1,2,new Color(202,195,0),"Yellow"),
    OrangeDice(2,4,new Color(236,61,17),"Orange"),
    RedDice(4,8,new Color(222,0,0), "Red"),
    GreenDice(7,12,new Color(21,103,0),"Green"),
    PurpleDice(11,20,new Color(83,0,92), "Purple"),
    BlueDice(21,30,new Color(0,0,192), "Blue");
    
    /*
     * ATTRIBUTS
     */
    
    private int rangeMin;
    private int rangeMax;
    private Color color;
    private String diceColorName;
    
    /*
     * CONSTRUCTORS
     */

    private Dice() {
        this.rangeMin = 1;
        this.rangeMax = 6;
    }

    
    private Dice(int rangeMin, int rangeMax, Color color, String diceColorName) {
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        this.color = color;
        this.diceColorName = diceColorName;
    }
    
    /*
     * SETTERS
     */
    
    public void setRangeMin(int rangeMin) {
        this.rangeMin = rangeMin;
    }
    
    public void setRangeMax(int rangeMax) {
        this.rangeMax = rangeMax;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    /*
     * GETTERS
     */
    
    public int getRangeMin() {
        return rangeMin;
    }

    public int getRangeMax() {
        return rangeMax;
    }

    public Color getColor() {
        return color;
    }
    
    public String getDiceColorName(){
        return this.diceColorName;
    }
    
    /*
     * Simulation du lancer de dé
     */
   
    public int getNumber(){
        return (int)(Math.random()*(this.rangeMax-this.rangeMin))+this.rangeMin;
    }
  
}
