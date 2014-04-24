/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Game;
import Model.Players.Player;
import java.awt.event.ActionEvent;

/**
 *
 * @author matt
 */
public class ChangeSelectedPlayerController extends AbstractFormulaDController {
    
    private Player player;

    public ChangeSelectedPlayerController(Game model, Player player) {
        super(model);
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.getPlayersManager().setSelectedPlayer(player);
    }
    
}
