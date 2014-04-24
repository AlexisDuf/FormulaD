/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.RecordingGame;

import Model.Players.Player;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author matt
 */
public class Score implements Serializable{

    private Collection<ScorePlayer> winSortedPlayers;
    private Collection<ScorePlayer> loosePlayers;
    private Date date;
    private String circuitName;

    public Score() {
    }

    public Score(Collection<Player> winPlayers, Collection<Player> loosePlayers, Date date, String CircuiName) {
        this.winSortedPlayers = new LinkedList<>();
        this.loosePlayers = new LinkedList<>();
        for (Player player : loosePlayers) {
            this.loosePlayers.add(new ScorePlayer(player.getPlayerName(),
                                                  player.getNumberOfThrow(),
                                                  player.getCar().getName()));
        }
        for (Player player : winPlayers) {
            this.winSortedPlayers.add(new ScorePlayer(player.getPlayerName(),
                                                  player.getNumberOfThrow(),
                                                  player.getCar().getName()));
        }
        this.date = date;
        this.circuitName = CircuiName;
    }
    
    /*
     * Getters
     */

    public Collection<ScorePlayer> getWinSortedPlayers() {
        return winSortedPlayers;
    }

    public Collection<ScorePlayer> getLoosePlayers() {
        return loosePlayers;
    }

    public Date getDate() {
        return date;
    }

    public String getCircuitName() {
        return circuitName;
    }
    
    
    /*
     * Setters
     */
    

    public void setWinSortedPlayers(Collection<ScorePlayer> winSortedPlayers) {
        this.winSortedPlayers = winSortedPlayers;
    }

    public void setLoosePlayers(Collection<ScorePlayer> loosePlayers) {
        this.loosePlayers = loosePlayers;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }
     
}
