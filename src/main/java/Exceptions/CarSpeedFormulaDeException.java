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
public class CarSpeedFormulaDeException extends FormulaDeException {

    public CarSpeedFormulaDeException() {
    }

    public CarSpeedFormulaDeException(String message, AbstractModel sender) {
        super(message, sender);
    }
    
}
