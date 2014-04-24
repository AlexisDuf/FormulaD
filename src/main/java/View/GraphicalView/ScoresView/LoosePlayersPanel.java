/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.ScoresView;

import Events.CreateViewEvent;
import Events.CurrentScoreChangedEvent;
import Model.RecordingGame.Score;
import Model.RecordingGame.ScorePlayer;
import java.awt.GridLayout;
import java.util.Collection;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class LoosePlayersPanel extends JPanel{

    public LoosePlayersPanel() {
        this.setOpaque(false);
    }
    
    

    public void createView(CreateViewEvent ev) {
        Score currentScore = ev.getSender().getScoresManager().getCurrentScore();
        if(currentScore != null){
            this.removeAll();
            this.setLayout(new GridLayout(currentScore.getLoosePlayers().size(), 1));
            Collection<ScorePlayer> loosePlayers = currentScore.getLoosePlayers();
            int i = 1;
            for (ScorePlayer currentScorePlayer : loosePlayers) {
                this.add(new PlayerItem(currentScorePlayer.getName(), currentScorePlayer.getCar(), i));
                i++;
            }
        }
    }
    
    public void refresh(CurrentScoreChangedEvent ev){
        Score currentScore = ev.getScore();
        if(currentScore != null){
            this.removeAll();
            this.setLayout(new GridLayout(currentScore.getLoosePlayers().size(), 1));
            Collection<ScorePlayer> loosePlayers = currentScore.getLoosePlayers();
            int i = 1;
            for (ScorePlayer currentScorePlayer : loosePlayers) {
                this.add(new PlayerItem(currentScorePlayer.getName(), currentScorePlayer.getCar(), i));
                i++;
            }
        }
    }
    
}
