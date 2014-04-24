/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.ScoresView;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class PlayerItem extends JPanel{
    private String playerName;
    private String carName;
    private int position;

    public PlayerItem() {
    }

    public PlayerItem(String playerName, String carName, int position) {
        this.playerName = playerName;
        this.carName = carName;
        this.position = position;
        JLabel jlabel = new JLabel(this.position+".\t"+this.playerName+" with car :"+this.carName);
        super.setOpaque(false);
        jlabel.setForeground(Color.WHITE);
        this.add(jlabel);
    }
    
}
