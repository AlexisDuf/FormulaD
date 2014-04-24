/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import Model.MVC.AbstractModel;

/**
 *
 * @author matt
 */
public class PlayerNameAlreadyExistFormulaDException extends FormulaDeException {

    public PlayerNameAlreadyExistFormulaDException() {
    }

    public PlayerNameAlreadyExistFormulaDException(String message, AbstractModel sender) {
        super(message, sender);
    }
    
}
