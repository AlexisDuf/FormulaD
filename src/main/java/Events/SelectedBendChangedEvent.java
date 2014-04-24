/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Map.Bend;

/**
 *
 * @author matt
 */
public class SelectedBendChangedEvent {
    private Bend selectedBend;

    public SelectedBendChangedEvent(Bend selectedBend) {
        this.selectedBend = selectedBend;
    }

    public Bend getSelectedBend() {
        return selectedBend;
    }
    
    
}
