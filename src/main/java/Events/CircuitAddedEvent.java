/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Circuit;

/**
 *
 * @author matt
 */
public class CircuitAddedEvent {
    Circuit circuit;

    public CircuitAddedEvent(Circuit circuit){
        this.circuit = circuit;
    }
    
    public Circuit getCircuit(){
        return this.circuit;
    }
    
}
