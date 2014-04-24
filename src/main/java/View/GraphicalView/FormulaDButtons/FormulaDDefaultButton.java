/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.FormulaDButtons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author matt
 */
public class FormulaDDefaultButton extends FormulaDButton {
    public FormulaDDefaultButton() {
        this ("Default Button");
    }

    public FormulaDDefaultButton(String string) {
        super(string);
        this.setBackground(new Color(50,118,177));
        this.setForeground(Color.WHITE);
        this.setMaximumSize(this.getMaximumSize());
    }
    
}

