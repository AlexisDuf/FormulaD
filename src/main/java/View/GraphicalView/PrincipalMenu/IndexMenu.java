/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.PrincipalMenu;

import Application.Launcher;
import Controller.ChangeApplicationStateController;
import Events.CreateViewEvent;
import Model.ApplicationState;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class IndexMenu extends JPanel{

    public IndexMenu(CreateViewEvent ev) {
        setLayout(new GridLayout(2, 1));
        try {
            
            IndexMenuItem newGameConfiguration = new IndexMenuItem("./img/view/new.png", new ChangeApplicationStateController(ApplicationState.GameConfiguration, ev.getSender())); 
            IndexMenuItem scoresItem = new IndexMenuItem("./img/view/trophee.png", new ChangeApplicationStateController(ApplicationState.Scores, ev.getSender()));
            
            add(newGameConfiguration);
            add(scoresItem);
        } catch (IOException ex) {
            Logger.getLogger(IndexMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
