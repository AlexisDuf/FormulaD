/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Dice;
import Model.Game;
import java.awt.event.ActionEvent;

/**
 *
 * @author matt
 */
public class ThrowCurrentPlayerDiceController extends AbstractFormulaDController{
    

    public ThrowCurrentPlayerDiceController(Game model) {
        super(model);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        model.getPlayersManager().getCurrentPlayer().throwDice();
    }
    
}
