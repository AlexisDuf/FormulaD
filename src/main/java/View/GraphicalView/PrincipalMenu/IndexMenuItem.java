/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.PrincipalMenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author matt
 */
public class IndexMenuItem extends JPanel{
    
    public static Color indexMenuItemColor = new Color(255,255,255);
    
    /*
     * Constructor
     */
    
    public IndexMenuItem(String _picturePath, ActionListener actionListener) throws IOException {
        /*
         * Simulation du padding en html
         */
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(BorderFactory.createCompoundBorder(paddingBorder, paddingBorder));
        
        setLayout(new GridLayout(2, 1));
        BufferedImage buttonIcon = ImageIO.read(new File(_picturePath));
        JButton button = new JButton(new ImageIcon(buttonIcon));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        this.setBackground(indexMenuItemColor);
        button.addActionListener(actionListener);
        
        add(button);
     
    }
    
    
}
