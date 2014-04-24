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
public class Bend extends RaceWay implements Serializable{
    private String name;
    private boolean left;
    private int minStop;
    private int longest;
    private int minest;

    public Bend() {
    }
   
    public Bend(int id, String name, boolean left, int minStop, int midway, int leftway, int rightway ) {
        super(id,midway,leftway,rightway);
        this.name = name;
        this.left = left;
        this.minStop = minStop;
        
        this.longest = Math.max(Math.max(midway, leftway), rightway);
        this.minest = Math.min(Math.min(midway, leftway), rightway);
        
        Cell[] midWay = this.getMidWay();
        Cell[] leftWay = this.getLeftWay();
        Cell[] rightWay = this.getRightWay();
        

        
        for(int i = 0; i<midway;i++){
            midWay[i] =new BendCell("virage:"+this.getId()+":m:"+i,this);
        }
        for(int i = 0; i<leftway;i++){
            leftWay[i] =new BendCell("virage:"+this.getId()+":g:"+i,this);
        }
        for(int i = 0; i<rightway;i++){
            rightWay[i] =new BendCell("virage:"+this.getId()+":d:"+i,this);
        }       
    }

    public String getName() {
        return name;
    }


    public int getMinStop() {
        return minStop;
    }

    public int getMinest() {
        return minest;
    }

    

    public int getLongest() {
        return longest;
    }    
    
    public boolean isLeft(){
        return this.left;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setMinStop(int minStop) {
        this.minStop = minStop;
    }

    public void setLongest(int longest) {
        this.longest = longest;
    }

    public void setMinest(int minest) {
        this.minest = minest;
    }   
    
}
