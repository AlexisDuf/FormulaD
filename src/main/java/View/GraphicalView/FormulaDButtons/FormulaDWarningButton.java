/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.FormulaDButtons;

import java.awt.Color;
import javax.swing.JButton;

public class FormulaDWarningButton extends FormulaDButton {

    public FormulaDWarningButton() {
        this ("WarningButton");
    }

    public FormulaDWarningButton(String string) {
        super(string);
        this.setBackground(new Color(210,50,45));
        this.setForeground(Color.WHITE);
        this.setMaximumSize(this.getMaximumSize());
    }
    
}