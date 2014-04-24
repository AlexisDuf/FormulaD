/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.RecordingGame;

import Events.CurrentScoreChangedEvent;
import Tools.XMLTools;
import com.google.common.eventbus.EventBus;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matt
 */
public class ScoresManager{
    
    private ArrayList<Score> scores;
    private int currentScoreIndex;
    private EventBus eventBus;
    public static String xmlPath="./game/scores.xml";

    public ScoresManager() {
        this.scores = new ArrayList<>();
        loadScores();
        if(this.scores.size() > 0){
            this.currentScoreIndex = 0;
        }else{
            this.currentScoreIndex = -1;
        }
        
    }

    public ScoresManager(EventBus eventBus) {
        this();
        this.eventBus = eventBus;
    } 
    
    public final void loadScores(){
        try {
            this.scores = (ArrayList<Score>) XMLTools.decodeToFile(xmlPath);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void saveScores(){
        try {
            XMLTools.encodeToFile(this.scores, xmlPath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScoresManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void addSore(Score newScore){
        this.scores.add(newScore);
        saveScores();
    }
    
    public void removeScore(Score toRemove){
        this.scores.remove(toRemove);
    }
    
    public void nextScore(){
        if(currentScoreIndex+1 >= this.scores.size()){
            currentScoreIndex = 0;
        }else{
            currentScoreIndex ++;
        }
        this.eventBus.post(new CurrentScoreChangedEvent(this.scores.get(currentScoreIndex)));
    }
    
    public void previousScore(){
        if(currentScoreIndex-1 < 0 ){
            currentScoreIndex = this.scores.size()-1;
        }else{
            currentScoreIndex --;
        }
        this.eventBus.post(new CurrentScoreChangedEvent(this.scores.get(currentScoreIndex)));
    }
    
    public int getCurrentScoreIndex(){
        return this.currentScoreIndex;
    }
    
    public Score getCurrentScore(){
        return this.scores.get(this.currentScoreIndex);
    }
}
