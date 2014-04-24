/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.GameConfiguration;

import Controller.ChangeWeatherController;
import Events.CreateViewEvent;
import Model.Configuration.Weather;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author matt
 */
public class ChooseWeatherPanel extends JPanel{
    private ButtonGroup buttonGroup;

    public ChooseWeatherPanel() {
        this.buttonGroup = new ButtonGroup();
    }
    
    public void initPanel(CreateViewEvent ev){
        JRadioButton currentRadioButton;
        setLayout(new GridLayout(Weather.values().length, 1));
        Weather[] weathers = Weather.values();
        for (int i = 0; i <  weathers.length; i++) {
            currentRadioButton = new JRadioButton(weathers[i].getName());
            currentRadioButton.addActionListener(new ChangeWeatherController(ev.getSender(), weathers[i]));
            currentRadioButton.setForeground(new Color(255,255,255));
            currentRadioButton.setBackground(new Color(69,69,69));
            this.buttonGroup.add(currentRadioButton);
            this.add(currentRadioButton);
            if(ev.getSender().getCurrentWeather() == weathers[i]){
                currentRadioButton.setSelected(true);
            }
        }
    }
    
    
}
