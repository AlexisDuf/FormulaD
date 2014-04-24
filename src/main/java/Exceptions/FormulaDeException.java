/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import Model.Game;
import Model.MVC.AbstractModel;



/**
 *
 * @author matt
 */
public class FormulaDeException extends RuntimeException {
    private AbstractModel sender;

    public FormulaDeException() {
    }

    public FormulaDeException(String message, AbstractModel sender) {
        super(message);
        this.sender = sender;
    }
       
}
