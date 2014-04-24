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
public class StraightLine extends RaceWay implements Serializable{
    private Bend from;
    private Bend to;

    public StraightLine() {
    }
    
        
    public StraightLine(int id, int midway, int leftway, int rightway,Bend from, Bend to ) {
        super(id,midway,leftway,rightway);
        
        Cell[] midWay = this.getMidWay();
        Cell[] leftWay = this.getLeftWay();
        Cell[] rightWay = this.getRightWay();
        
        this.from = from;
        this.to = to ;
        
        for(int i = 0; i<midway;i++){
            midWay[i] =new StraightLineCell("case:"+this.getId()+":m:"+i,this);
        }
        for(int i = 0; i<leftway;i++){
            leftWay[i] =new StraightLineCell("case:"+this.getId()+":g:"+i,this);
        }
        for(int i = 0; i<rightway;i++){
            rightWay[i] =new StraightLineCell("case:"+this.getId()+":d:"+i,this);
        }
        this.linkCells();
        
    }

   
    
    private void linkCells(){
        
        Cell[] midWay = this.getMidWay();
        Cell[] leftWay = this.getLeftWay();
        Cell[] rightWay = this.getRightWay();
        //link the inner cells 
        int leftWayLength=leftWay.length,midWayLength=midWay.length,rightWayLength=rightWay.length;
        int offsetMid=0;
        if(from.isLeft()){
            
            for (int i = 0; i < leftWay.length-1; i++) {
                leftWay[i].setFront(leftWay[i+1]);
                if(i<midWayLength){
                    leftWay[i].setRight(midWay[i]);
                }
                
            }
            for (int i = 0; i < midWay.length-1; i++) {
                midWay[i].setFront(midWay[i+1]);
                if(i<rightWayLength){
                    midWay[i].setRight(rightWay[i]);
                }
                if(i<leftWayLength){
                    midWay[i].setLeft(leftWay[i+offsetMid]);
                }
                
            }
            for (int i = 0; i < rightWay.length-1; i++) {
                rightWay[i].setFront(rightWay[i+1]);
                if(i<leftWayLength){
                    rightWay[i].setLeft(midWay[i+offsetMid]);
                }
                
            }
        }else{
            offsetMid = 1;

            for (int i = 0; i < leftWay.length-1; i++) {
                leftWay[i].setFront(leftWay[i+1]);
                if(i+offsetMid<midWayLength){
                    leftWay[i].setRight(midWay[i+offsetMid]);
                }
                
            }
            for (int i = 0; i < midWay.length-1; i++) {
                midWay[i].setFront(midWay[i+1]);
                if(i+offsetMid<rightWayLength){
                    midWay[i].setRight(rightWay[i+offsetMid]);
                }
                if(i<leftWayLength){
                    midWay[i].setLeft(leftWay[i]);
                }
            }
            for (int i = 0; i < rightWay.length-1; i++) {
                rightWay[i].setFront(rightWay[i+1]);
                if(i<midWayLength){
                    rightWay[i].setLeft(midWay[i]);
                }
            }
        }
        /**if(to.isLeft()){
            rightWay[rightWayLength-1].setLeft(midWay[midWayLength-1]);
            midWay[midWayLength-1].setLeft(leftWay[leftWayLength-1]);
        }else{
            midWay[midWayLength-1].setRight(rightWay[rightWayLength-1]);
           leftWay[leftWayLength-1].setRight(midWay[midWayLength-1]);
        }**/
               
    }

    public Bend getFrom() {
        return from;
    }

    public Bend getTo() {
        return to;
    }

    public void setFrom(Bend from) {
        this.from = from;
    }

    public void setTo(Bend to) {
        this.to = to;
    }
    
    
    
}
