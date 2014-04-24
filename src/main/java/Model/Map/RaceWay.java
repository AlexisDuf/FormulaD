/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;

import java.io.Serializable;

/**
 *
 * @author matt
 */
public class RaceWay extends Way implements Serializable{
    
    private Cell[] midWay;
    private Cell[] rightWay;
    private Cell[] leftWay;

    public Cell[] getMidWay() {
        return midWay;
    }

    public Cell[] getRightWay() {
        return rightWay;
    }

    public Cell[] getLeftWay() {
        return leftWay;
    }

    public RaceWay() {
    }
    
    public RaceWay(int id,int midWay,int leftWay,int rightWay) {
        super(id);
        this.midWay = new Cell[midWay];
        this.leftWay = new Cell[leftWay];
        this.rightWay = new Cell[rightWay];
    }

    @Override
    public Cell getCell(String string, Integer index) {
        if(string.equals("g")){
            return this.leftWay[index];
        }else if(string.equals("m")){
            return this.midWay[index];
        }else if(string.equals("d")){
            return this.rightWay[index];
        }
        return null;
    }

    public void setMidWay(Cell[] midWay) {
        this.midWay = midWay;
    }

    public void setRightWay(Cell[] rightWay) {
        this.rightWay = rightWay;
    }

    public void setLeftWay(Cell[] leftWay) {
        this.leftWay = leftWay;
    }
    
    

    
    
    
}
