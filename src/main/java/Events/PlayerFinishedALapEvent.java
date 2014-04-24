/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Events;

import Model.Players.Player;

/**
 *
 * @author wasson
 */
public class PlayerFinishedALapEvent {
     private Player player;
     private int lap;

    public PlayerFinishedALapEvent(Player player, int lap) {
        this.player = player;
        this.lap = lap;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }
     
     
}
