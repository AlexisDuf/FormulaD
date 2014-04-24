/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ApplicationState;
import Model.Game;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

/**
 *
 * @author matt
 */
public class ChangeApplicationStateController extends AbstractFormulaDController {
    
    private ApplicationState state;

    public ChangeApplicationStateController(ApplicationState state, Game model) {
        super(model);
        this.state = state;
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setApplicationState(state);        
    }
    
}
