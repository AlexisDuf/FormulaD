/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Players;

import Model.SnapShot;
import Model.Traceback;
import java.util.Comparator;

/**
 *
 * @author alexisdufour
 */
public class CompareValueDice implements Comparator<Player> {

    /**
     * Compare the dice value between the players
     * @param p1 represents a player
     * @param p2 represents a player
     * @return 
     */
    @Override
    public int compare(Player p1, Player p2) {
        Traceback currentTracebackP1 = p1.getTraceback(); 
        SnapShot currentSnapP1 = currentTracebackP1.getLastSnapShot(); 
        
        Traceback currentTracebackP2 = p2.getTraceback(); 
        SnapShot currentSnapP2 = currentTracebackP2.getLastSnapShot(); 
        
        if(currentSnapP1.getValueDice() < currentSnapP2.getValueDice()){
            return -1;
        }else if(currentSnapP1.getValueDice() > currentSnapP2.getValueDice()){
            return 1; 
        }else{
            return 0;
        }
    }
    
}
