/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.MVC;

import Events.CreateViewEvent;
import Model.Game;
import View.AbstractFormulaDConfigurationGameView;
import View.AbstractFormulaDView;
import com.google.common.eventbus.EventBus;
import java.io.Serializable;

/**
 *
 * @author matt
 */
public class EventBusHandler implements Serializable{
    private EventBus eventBus;

    public EventBusHandler() {
        this.eventBus = new EventBus();

    }

    public EventBusHandler(String eventBusName) {
        this.eventBus = new EventBus(eventBusName);
    }

    public void addFormulaDView(AbstractFormulaDView view, Game sender) {
        this.eventBus.register(view);
        view.createView(new CreateViewEvent(sender));
    }

    public void removeView(AbstractFormulaDConfigurationGameView view) {
        this.eventBus.unregister(view);
    }

    public EventBus getEventBus() {
        return this.eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    
  
}
