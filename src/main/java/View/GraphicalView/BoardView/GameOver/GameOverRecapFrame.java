/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.BoardView.GameOver;

import Events.GameOverEvent;
import Model.Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class GameOverRecapFrame extends JFrame {
    private GameOverRecapContainer container;
    
    private JPanel mainPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon m = new ImageIcon("./img/backgrounds/gameOverBackground.jpg");
                Image monImage = m.getImage();
                g.drawImage(monImage, 0, 0, this);

            }
    };

    public GameOverRecapFrame(Game game) throws HeadlessException {
        this.container = new GameOverRecapContainer(game);
        this.add(mainPanel);
        this.mainPanel.add(this.container);
        this.setSize(new Dimension(600, 250));
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
    
    public void refresh(GameOverEvent ev){
        this.container.refresh(ev);
    }
    
}
