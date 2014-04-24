/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.MVC;

import java.io.Serializable;

/**
 *
 * @author matt
 */
public class AbstractModel implements Serializable{
    
    /***********************************
    ************  ATTRIBUTS ************
    ***********************************/
    
    protected EventBusHandler eventBusHandler;
    
    
    /*
     * Constructors
     */
    public AbstractModel() {
        this.eventBusHandler = new EventBusHandler();
    }
    
    /*
     * Getters
     */
    
    public EventBusHandler getEventBusHandler(){
        return this.eventBusHandler;
    }
    
    /***********************************
    ************* SETTEURS ************
    ***********************************/

    public void setEventBusHandler(EventBusHandler eventBusHandler) {
        this.eventBusHandler = eventBusHandler;
    }
    
    
    
}
