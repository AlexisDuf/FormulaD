/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.RecordingGame.Score;

/**
 *
 * @author matt
 */
public class CurrentScoreChangedEvent {
    private Score score;

    public CurrentScoreChangedEvent() {
    } 

    public CurrentScoreChangedEvent(Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }
    
    
    
    
}
