/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Game;
import java.awt.event.ActionListener;

/**
 *
 * @author matt
 */
public abstract class AbstractFormulaDController implements ActionListener {
    protected final Game model;

    public AbstractFormulaDController(Game model) {
        this.model = model;
    }
}
