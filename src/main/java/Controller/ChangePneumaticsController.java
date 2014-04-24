/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cars.Pneumatics;
import Model.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author matt
 */
public class ChangePneumaticsController implements ActionListener{
    private Game game;
    private Pneumatics pneumatics;

    public ChangePneumaticsController(Game game, Pneumatics pneumatics) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.game.getPlayersManager().getCurrentPlayer().getCar().setPneumatics(this.pneumatics);
    }
    
    
}
