/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.GameConfiguration.RightContainer;

import Model.Players.Player;
import Model.Players.PlayersManager;
import View.GraphicalView.FormulaDButtons.FormulaDButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author matt
 */
public class PlayerSelectionItem extends FormulaDButton implements ActionListener{
    private JLabel playerIcon;
    private Player player;
    private PlayersManager playersManager;

    public PlayerSelectionItem() {
        
    }
    
    public PlayerSelectionItem(PlayersManager playersManager, Player player){
        super(player.getPlayerName());
        this.setPreferredSize(new Dimension(300,65));
        this.playerIcon = new JLabel(new ImageIcon("./img/GraphicalViewIcon/user-icon.png"));
        this.playerIcon.setPreferredSize(new Dimension(44,42));
        this.add(this.playerIcon);
        this.setBackground(new Color(255,255,255));
        this.setForeground(new Color(69,69,69));
        this.player = player;
        this.playersManager = playersManager;
        this.setMaximumSize(null);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.playersManager.setCurrentPlayer(player);
    }
    
    public Player getPlayer(){
        return this.player;
    }
}
