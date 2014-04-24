/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.GameConfiguration;

import Events.CreateViewEvent;
import View.GraphicalView.GameConfiguration.RightContainer.RightGameConfigurationPanel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public final class GameConfigurationContainer extends JPanel{

    private ChoosePresetsGame leftPanel;
    private RightGameConfigurationPanel rightContainer;
    private CurrentPlayerConfiguration centerPanel;
    private boolean isConnect = true;

    public GameConfigurationContainer(CreateViewEvent ev) {
        this.setLayout(new BorderLayout());
        createView(ev);
        ev.getSender().getEventBusHandler().addFormulaDView(leftPanel, ev.getSender());
        this.setOpaque(false);
    }

    public void refreshView(CreateViewEvent ev) {
    }

    public void createView(CreateViewEvent ev) {
        this.rightContainer = new RightGameConfigurationPanel(ev);
        ev.getSender().getEventBusHandler().addFormulaDView(this.rightContainer.getPlayerSelectionItemContainer(), ev.getSender());
        this.centerPanel = new CurrentPlayerConfiguration(ev);
        ev.getSender().getEventBusHandler().addFormulaDView(this.centerPanel, ev.getSender());
        this.leftPanel = new ChoosePresetsGame();

        this.add(this.rightContainer, BorderLayout.EAST);
        this.add(this.leftPanel, BorderLayout.WEST);
        this.add(this.centerPanel, BorderLayout.CENTER);       
    }
    
}
