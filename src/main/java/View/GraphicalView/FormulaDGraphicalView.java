/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView;

import Controller.ChangeAILevelCurrentComputerPlayer;
import Controller.CloseApplicationController;
import Controller.KonamiController;
import Events.ApplicationStateChangedEvent;
import Events.CircuitAddedEvent;
import Events.CircuitChangedEvent;
import Events.CreateViewEvent;
import Events.CurrentPlayerCarChangedEvent;
import Events.CurrentPlayerChangedEvent;
import Events.CurrentPlayerModeChangedEvent;
import Events.NumberOfPlayersChanged;
import Events.PlayerNameChangedEvent;
import Events.WeatherChangedEvent;
import View.AbstractFormulaDConfigurationGameView;
import Model.Game;
import View.AbstractFormulaDView;
import View.GraphicalView.BoardView.GameView;
import View.GraphicalView.GameConfiguration.GameConfigurationContainer;
import View.GraphicalView.PrincipalMenu.IndexMenu;
import View.GraphicalView.ScoresView.ScoresViewPanel;
import com.google.common.eventbus.Subscribe;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class FormulaDGraphicalView implements AbstractFormulaDConfigurationGameView, AbstractFormulaDView{

    /*
     * Attributs
     */
    private JFrame jframe;
    private JPanel mainPanel;
    private boolean isConnect = true;
    
    private GameConfigurationContainer gameConfigurationPanel=null;
    private IndexMenu indexMenuPanel=null;
    private ScoresViewPanel scoresView = null;

    /*
     * Constructor
     */
    public FormulaDGraphicalView(String _title, int _xSize, int _ySize, boolean _resizable) {
        this.jframe = new JFrame();
        this.jframe.setLocationRelativeTo(null);
        this.jframe.setTitle(_title);
        this.jframe.setSize(_xSize, _ySize);
        this.jframe.setResizable(_resizable);

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon m = new ImageIcon("./img/view/principalMenuBackground.jpg");
                Image monImage = m.getImage();
                g.drawImage(monImage, 0, 0, this);

            }
        };
        this.jframe.add(mainPanel);
        this.jframe.setVisible(true);
    }

    @Override
    public void createView(CreateViewEvent ev) {
        Game game = ev.getSender();
        this.jframe.addKeyListener(new KonamiController(game));
        this.jframe.addWindowListener(new CloseApplicationController(game));
        
        if(this.gameConfigurationPanel == null){
          this.gameConfigurationPanel = new GameConfigurationContainer(ev); 
        }
        
        if(this.indexMenuPanel == null){
           this.indexMenuPanel = new IndexMenu(ev); 
        }
        
        if(this.scoresView == null){
            this.scoresView = new ScoresViewPanel(ev);
        }
        

        switch (game.getApplicationState()) {
            case MainMenu:
                this.mainPanel.removeAll();
                mainPanel.setLayout(new BorderLayout());
                mainPanel.add(this.indexMenuPanel, BorderLayout.WEST);
                this.mainPanel.repaint();
                this.mainPanel.revalidate();
                break;

            case GameConfiguration:
                this.mainPanel.removeAll();
                this.mainPanel.add(this.gameConfigurationPanel, BorderLayout.CENTER);
                this.mainPanel.revalidate();
                this.mainPanel.repaint();
                break;

            case Game:
                this.mainPanel.removeAll();
                this.mainPanel.add(new GameView(ev), BorderLayout.CENTER);
                this.mainPanel.revalidate();
                this.mainPanel.repaint();
                break;
                
            case Scores:
                this.mainPanel.removeAll();
                this.mainPanel.add(this.scoresView, BorderLayout.CENTER);
                this.mainPanel.revalidate();
                this.mainPanel.repaint();
         }


    }

    @Override
    public boolean isConnect() {
        return this.isConnect;
    }

    @Override
    public void connectView() {
        this.isConnect = true;
    }

    @Override
    public void disconnectView() {
        this.isConnect = false;
    }

    @Override
    @Subscribe
    public void refreshApplicationState(ApplicationStateChangedEvent ev) {
        try {
            this.createView(new CreateViewEvent(ev.getSender()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void refreshWeather(WeatherChangedEvent ev) {}

    @Override
    public void refreshCurrentPlayer(CurrentPlayerChangedEvent ev) {}

    @Override
    public void refreshNumberOfPlayers(NumberOfPlayersChanged ev) {}

    @Override
    public void refreshCircuitAdded(CircuitAddedEvent ev) {}

    @Override
    public void refreshPlayerName(PlayerNameChangedEvent ev) {}

    @Override
    public void refreshCurrentCar(CurrentPlayerCarChangedEvent ev) {}

    @Override
    public void refreshCurrentPlayerMode(CurrentPlayerModeChangedEvent ev) {}

    @Override
    public void refreshCurrentComputerPlayerLevel(ChangeAILevelCurrentComputerPlayer ev) {}

    @Override
    public void refreshCircuit(CircuitChangedEvent ev) {
    }
}
