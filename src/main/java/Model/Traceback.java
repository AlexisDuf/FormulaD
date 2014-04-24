/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author matt
 */
public class Traceback {
    private ArrayList<SnapShot> snapShots;
    
    
    /**
     * Default constructor
     */
    public Traceback() {
        snapShots = new ArrayList<SnapShot>();
    }    
    
    /**
     * 
     * @return Return a SnapShots array
     */
    
    public ArrayList<SnapShot> getSnapShots() {
        return snapShots;
    }
    
    
    /**
     * Set a new SnapShots array
     * @param snapShots 
     */
    public void setSnapShots(ArrayList<SnapShot> snapShots) {
        this.snapShots = snapShots;
    }
    
    /**
     * Return a specific snapshot
     * @param i index of array
     * @return 
     */
    public SnapShot getSnapShot(int i){
        return snapShots.get(i);        
    }
    
    /**
     * Set a new snapshot in array at the end
     * @param snap
     */
    public void setNewSnapShot(SnapShot snap){
        snapShots.add(snap);
    }
    
    public SnapShot getLastSnapShot(){
        if(this.snapShots.size() > 0){
            return snapShots.get(snapShots.size()-1);
        }else{
            return null;
        }
        
    }
    
    public SnapShot getPenultimateSnapShot(){
        if(this.snapShots.size() > 1){
            return snapShots.get(snapShots.size()-2);
        }else{
            return null;
        }
        
    }
    
    
}
