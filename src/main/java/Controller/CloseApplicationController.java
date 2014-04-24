/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 *
 * @author matt
 */
public class CloseApplicationController implements WindowListener, ActionListener {

    private Game game;

    public CloseApplicationController(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        game.getCircuitManager().saveCircuits();
        /*if(game.isGameIsOn()){
            game.saveGame();
        }*/
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.getCircuitManager().saveCircuits();
        /*if(game.isGameIsOn()){
            game.saveGame();
        }*/
        System.exit(0);
    }
}
