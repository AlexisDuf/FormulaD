/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.PrincipalMenu;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class IndexDescription extends JPanel {

    public IndexDescription() {
        setOpaque(false);
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel("FORMULA DE");
        
        
        JLabel description = new JLabel("Coding by Paul WASSON, Alexis DUFOUR and Matthieu DELMAIRE");
    }
    
}
