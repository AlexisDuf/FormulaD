/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Game;

/**
 *
 * @author matt
 */
public class CreateViewEvent {
    
    private Game sender;

    public CreateViewEvent(Game sender) {
        this.sender = sender;
    }
    
    public Game getSender(){
        return this.sender;
    }
    
}
