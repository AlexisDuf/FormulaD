/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.RecordingGame.ScoresManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author matt
 */
public class NextScoreController implements ActionListener{
    private ScoresManager scoresManager;

    public NextScoreController(ScoresManager scoresManager) {
        this.scoresManager = scoresManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.scoresManager.nextScore();
    }
}
