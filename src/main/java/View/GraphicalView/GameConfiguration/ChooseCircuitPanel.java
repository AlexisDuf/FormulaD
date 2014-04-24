/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.GameConfiguration;

import Controller.ChangeCircuitController;
import Events.CircuitAddedEvent;
import Events.CreateViewEvent;
import Model.Circuit;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author matt
 */
public class ChooseCircuitPanel extends JPanel {

    private ButtonGroup buttonGroup;

    public ChooseCircuitPanel() {
        this.buttonGroup = new ButtonGroup();
    }

    public void initPanel(CreateViewEvent ev) {
        setLayout(new GridLayout(ev.getSender().getCircuitManager().getNumberOfCircuits(), 1));
        Iterator<Circuit> it = ev.getSender().getCircuitManager().iterator();
        Circuit currentCircuit;
        JRadioButton currentRadioButton;

        while (it.hasNext()) {
            currentCircuit = it.next();
            currentRadioButton = new JRadioButton(currentCircuit.getName());
            //currentRadioButton.setBackground(new Color(69, 69, 69));
            currentRadioButton.setOpaque(false);
            currentRadioButton.setForeground(new Color(255, 255, 255));
            if (currentCircuit == ev.getSender().getCircuit()) {
                currentRadioButton.setSelected(true);
            }
            add(currentRadioButton);
            buttonGroup.add(currentRadioButton);
            currentRadioButton.addActionListener(new ChangeCircuitController(ev.getSender(), currentCircuit));
        }
    }

    public void refreshCircuitAdded(CircuitAddedEvent ev) {
        this.setLayout(new GridLayout(ev.getCircuit().getGame().getCircuitManager().getNumberOfCircuits(), 1));
        JRadioButton newRadioButton = new JRadioButton(ev.getCircuit().getName());
        newRadioButton.setBackground(new Color(69,69,69));
        newRadioButton.setForeground(new Color(255,255,255));
        newRadioButton.addActionListener(new ChangeCircuitController(ev.getCircuit().getGame(), ev.getCircuit()));
        add(newRadioButton);
        buttonGroup.add(newRadioButton);
        repaint();
        revalidate();
    }
}
