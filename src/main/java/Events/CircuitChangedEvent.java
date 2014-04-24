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
public class CircuitChangedEvent {
    private Circuit circuit;

    public CircuitChangedEvent(Circuit circuit) {
        this.circuit = circuit;
    }

    public Circuit getCircuit() {
        return circuit;
    }
    
    
}
