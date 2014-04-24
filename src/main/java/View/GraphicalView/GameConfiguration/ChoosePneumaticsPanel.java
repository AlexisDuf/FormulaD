/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.GameConfiguration;

import Controller.ChangePneumaticsController;
import Events.CreateViewEvent;
import Model.Cars.Pneumatics;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author matt
 */
public class ChoosePneumaticsPanel extends JPanel{
    
    private ButtonGroup buttonGroup;

    public ChoosePneumaticsPanel() {
        this.buttonGroup = new ButtonGroup();
    }
    
    public void initPanel(CreateViewEvent ev){
        Pneumatics[] pneumatics = Pneumatics.values();
        setLayout(new GridLayout(pneumatics.length,1));
        JRadioButton currentRadioButton;
        for (int i = 0; i < pneumatics.length; i++) {
            currentRadioButton = new JRadioButton(pneumatics[i].getName());
            currentRadioButton.addActionListener(new ChangePneumaticsController(ev.getSender(), pneumatics[i]));
            currentRadioButton.setForeground(new Color(255,255,255));
            currentRadioButton.setBackground(new Color(116,167,178));
            if(ev.getSender().getPlayersManager().getCurrentPlayer().getCar().getPneumatics() == pneumatics[i]){
                currentRadioButton.setSelected(true);
            }
            add(currentRadioButton);
            this.buttonGroup.add(currentRadioButton);
        }
    }
    
}
