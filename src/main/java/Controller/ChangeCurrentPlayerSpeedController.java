/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cars.CarSpeed;
import Model.Game;
import java.awt.event.ActionEvent;

/**
 *
 * @author matt
 */
public class ChangeCurrentPlayerSpeedController extends AbstractFormulaDController{
    
    private CarSpeed carSpeed;

    public ChangeCurrentPlayerSpeedController(Game model, CarSpeed speed) {
        super(model);
        this.carSpeed = speed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.getPlayersManager().getCurrentPlayer().getCar().setSpeed(this.carSpeed);
        this.model.getPlayersManager().setCurrentPlayerSpeedIsChoosed(true);
    }
    
}
