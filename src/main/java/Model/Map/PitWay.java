/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Map;

import java.io.Serializable;

/**
 *
 * @author wasson
 */
public class PitWay extends Way implements Serializable{
    private PitCell[] pitWay;
    private int length,firstPit;

    public PitWay() {
    }
    
    
    public PitWay(int id, int length,int first,Cell leftEndCell, Cell frontEndCell) {
        super(id);
        pitWay = new PitCell[length];
        //add cell
        for (int i = 0; i < length; i++) {
            pitWay[i] = new PitCell("stand:"+i,this);
        }
        for (int i = 0; i < length-1; i++) {
            pitWay[i].setFront(pitWay[i+1]);
        }
        pitWay[pitWay.length-1].setFront(frontEndCell);
        pitWay[pitWay.length-1].setLeft(leftEndCell);
        this.firstPit = first;
    }

    @Override
    public Cell getCell(String string, Integer index) {
        return pitWay[index];
    }

    public Cell[] getPitWay() {
        return pitWay;
    }

    public int getLength() {
        return length;
    }

    public int getFirst() {
        return firstPit;
    }

    public void setPitWay(PitCell[] pitWay) {
        this.pitWay = pitWay;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setFirst(int first) {
        this.firstPit = first;
    }
    
    
    
    
    
}
