/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Game;
import java.awt.event.ActionEvent;

/**
 *
 * @author matt
 */
public class ChangeHumanModeController extends AbstractFormulaDController{
    boolean isHuman;

    public ChangeHumanModeController(Game model, boolean isHuman) {
        super(model);
        this.isHuman = isHuman;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.isHuman){
            this.model.getPlayersManager().setHumanModePlayer(this.model.getPlayersManager().getCurrentPlayer());
        }else{
            this.model.getPlayersManager().setComputerModePlayer(this.model.getPlayersManager().getCurrentPlayer());
        }
        
    }
    
}
