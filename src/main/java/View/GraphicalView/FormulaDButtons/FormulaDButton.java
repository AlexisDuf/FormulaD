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
public abstract class FormulaDButton extends JButton{
    
    
     private static class RoundedBorder implements Border {

        private int radius;
        RoundedBorder(int radius) {
            this.radius = radius;  
        }
        
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        @Override
        public boolean isBorderOpaque() {
            return true;
        }


        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }

    public FormulaDButton() {
        this("FormulaDButton");
    }

    public FormulaDButton(String text) {
        super(text);
        this.setMaximumSize(this.getMaximumSize());
        
    }
     
    
}
