/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.BoardView;

import View.GraphicalView.BoardView.RightContainer.CenterContainer.BoardPanelView;
import Events.CreateViewEvent;
import View.ConsoleView;
import View.GraphicalView.BoardView.LeftContainer.LeftBoardViewContainer;
import View.GraphicalView.BoardView.RightContainer.CenterContainer.PlayerGameIconContainer;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class GameView extends JPanel{
    
    private BoardPanelView boardPanel;
    private LeftBoardViewContainer leftBoardPanel;
    private PlayerGameIconContainer playerGameIconContainer;
    
    
    public GameView(CreateViewEvent ev){
        this.setLayout(new BorderLayout());
        this.boardPanel = new BoardPanelView();
        this.leftBoardPanel = new LeftBoardViewContainer(ev);
        this.playerGameIconContainer = new PlayerGameIconContainer();
       
        ev.getSender().getEventBusHandler().addFormulaDView(this.playerGameIconContainer, ev.getSender());
        ev.getSender().getEventBusHandler().addFormulaDView(this.boardPanel, ev.getSender());
        
        
        this.add(this.boardPanel, BorderLayout.CENTER);
        this.add(this.leftBoardPanel, BorderLayout.WEST);
        this.add(this.playerGameIconContainer, BorderLayout.SOUTH);
    }
    
}
