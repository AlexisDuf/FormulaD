/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Circuit;
import Model.Game;
import java.awt.event.ActionEvent;

/**
 *
 * @author matt
 */
public class ChangeCircuitController extends AbstractFormulaDController{
    
    private Circuit circuit;

    public ChangeCircuitController(Game model, Circuit _circuit ) {
        super(model);
        this.circuit = _circuit;
    }  

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.setCircuit(circuit);
    }
    
}
