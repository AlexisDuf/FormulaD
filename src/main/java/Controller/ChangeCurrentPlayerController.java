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
public class ChangeCurrentPlayerController extends AbstractFormulaDController {
    
    Player player;

    public ChangeCurrentPlayerController(Game model, Player _player) {
        super(model);
        this.player = _player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.getPlayersManager().setCurrentPlayer(player);
    }
    
}
