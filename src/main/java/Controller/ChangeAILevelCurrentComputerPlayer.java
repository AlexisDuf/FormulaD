/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AILevels;
import Model.Game;
import Model.Players.ComputerPlayer;
import Model.Players.Player;
import java.awt.event.ActionEvent;

/**
 *
 * @author matt
 */
public class ChangeAILevelCurrentComputerPlayer extends AbstractFormulaDController{
    
    private AILevels level;

    public ChangeAILevelCurrentComputerPlayer(Game model, AILevels _level) {
        super(model);
        this.level = _level;        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Player player = this.model.getPlayersManager().getCurrentPlayer();
        ComputerPlayer computPlayer = (ComputerPlayer)player;
        computPlayer.setLevel(level);
    }

    public AILevels getLevel() {
        return level;
    }
    
    
    
}
