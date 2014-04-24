/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exceptions;

import Model.MVC.AbstractModel;

/**
 *
 * @author alexisdufour
 */
public class EliminationRuleException extends FormulaDeException{

    public EliminationRuleException() {
    }

    public EliminationRuleException(String message, AbstractModel sender) {
        super(message, sender);
    }
    
    
    
}
