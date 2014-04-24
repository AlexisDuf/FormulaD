/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.BoardView.RightContainer.CenterContainer;

import Model.Map.Cell;
import org.w3c.dom.Element;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

/**
 *
 * @author matt
 */
public class DestinationCellEventManager {
    private Cell destination;
    private Element elt;
    
    /*
     * Listenners
     */
    private EventListener lisClick;
    private EventListener lisHover;
    private EventListener lisOut;

    public DestinationCellEventManager(Cell destination, Element elt, EventListener lisClick, EventListener lisHover, EventListener lisOut) {
        this.destination = destination;
        this.elt = elt;
        
        this.lisClick = lisClick;
        this.lisHover = lisHover;
        this.lisOut = lisOut;
    }
    
    public void removeListenners(){
        EventTarget t = (EventTarget) this.elt;
        t.removeEventListener("click", lisClick, false);
        t.removeEventListener("mouseover", lisHover, false);
        t.removeEventListener("mouseout", lisOut, false);
    }
    
    
}
