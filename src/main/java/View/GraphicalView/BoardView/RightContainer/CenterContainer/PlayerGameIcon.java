/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.BoardView.RightContainer.CenterContainer;

import Model.Players.Player;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author matt
 */
public class PlayerGameIcon extends JButton {

    public PlayerGameIcon(Player player) {
        super(new ImageIcon(player.getCar().getPictureIconPath()));
        this.setBackground(Color.WHITE);
        this.setText(player.getPlayerName());
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setPreferredSize(new Dimension(130, 100));
        this.setMaximumSize(new Dimension(130, 100));
    }
    
}
