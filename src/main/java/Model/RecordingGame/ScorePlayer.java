/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.RecordingGame;

import java.io.Serializable;

/**
 *
 * @author matt
 */
public class ScorePlayer implements Serializable {
    private String name;
    private int numberOfThrow;
    private String car;

    public ScorePlayer() {
    }
    
    

    public ScorePlayer(String name, int numberOfThrow, String car) {
        this.name = name;
        this.numberOfThrow = numberOfThrow;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfThrow() {
        return numberOfThrow;
    }

    public String getCar() {
        return car;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfThrow(int numberOfThrow) {
        this.numberOfThrow = numberOfThrow;
    }

    public void setCar(String car) {
        this.car = car;
    }
       
}
