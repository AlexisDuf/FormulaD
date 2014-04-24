/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Players.ComputerPlayer;

/**
 *
 * @author matt
 */
public class CurrentPlayerLevelChangedEvent {
    private ComputerPlayer player;

    public CurrentPlayerLevelChangedEvent(ComputerPlayer player) {
        this.player = player;
    }

    public ComputerPlayer getPlayer() {
        return player;
    }
    
    
}
