/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.GameConfiguration;

import Controller.ChangeAILevelCurrentComputerPlayer;
import Events.ApplicationStateChangedEvent;
import Events.CarStatesChangedEvent;
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
import View.AbstractFormulaDView;
import com.google.common.eventbus.Subscribe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

/**
 *
 * @author matt
 */
public class ModifyCarCaracteristicsFrame extends javax.swing.JFrame implements AbstractFormulaDView, AbstractFormulaDConfigurationGameView {

    private boolean isConnect = true;

    /**
     * Creates new form ModifyCarCaracteristicsFrame
     */
    public ModifyCarCaracteristicsFrame() {
        initComponents();
        this.setVisible(false);
        this.setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
        this.closeFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        choosePneumaticsPanel1 = new View.GraphicalView.GameConfiguration.ChoosePneumaticsPanel();
        jLabel5 = new javax.swing.JLabel();
        carStatesViewContainer1 = new View.GraphicalView.BoardView.LeftContainer.CarStatesViewContainer();
        carNameLabel = new javax.swing.JLabel();
        pitNameLabel = new javax.swing.JLabel();
        closeFrameButton = new View.GraphicalView.FormulaDButtons.FormulaDWarningButton();
        carIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(48, 114, 178));

        jPanel1.setBackground(new java.awt.Color(116, 167, 178));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(210, 50, 45));
        jLabel1.setText("Modify car's caracteristics :");

        jLabel2.setBackground(new java.awt.Color(48, 114, 178));
        jLabel2.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name :");

        jLabel3.setBackground(new java.awt.Color(48, 114, 178));
        jLabel3.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Pit :");

        jLabel4.setBackground(new java.awt.Color(48, 114, 178));
        jLabel4.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(210, 50, 45));
        jLabel4.setText("Pneumatics :");

        choosePneumaticsPanel1.setBackground(new java.awt.Color(116, 167, 178));
        choosePneumaticsPanel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout choosePneumaticsPanel1Layout = new javax.swing.GroupLayout(choosePneumaticsPanel1);
        choosePneumaticsPanel1.setLayout(choosePneumaticsPanel1Layout);
        choosePneumaticsPanel1Layout.setHorizontalGroup(
            choosePneumaticsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        choosePneumaticsPanel1Layout.setVerticalGroup(
            choosePneumaticsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jLabel5.setBackground(new java.awt.Color(48, 114, 178));
        jLabel5.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(210, 50, 45));
        jLabel5.setText("Caracteristics :");

        carStatesViewContainer1.setForeground(new java.awt.Color(255, 255, 255));

        carNameLabel.setBackground(new java.awt.Color(48, 114, 178));
        carNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        carNameLabel.setText("jLabel6");

        pitNameLabel.setBackground(new java.awt.Color(48, 114, 178));
        pitNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        pitNameLabel.setText("jLabel7");

        closeFrameButton.setText("Close");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(choosePneumaticsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pitNameLabel))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                            .addComponent(carNameLabel)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeFrameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(carStatesViewContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(carIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(carNameLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pitNameLabel))
                .addGap(35, 35, 35)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(choosePneumaticsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(carStatesViewContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(closeFrameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel carIcon;
    private javax.swing.JLabel carNameLabel;
    private View.GraphicalView.BoardView.LeftContainer.CarStatesViewContainer carStatesViewContainer1;
    private View.GraphicalView.GameConfiguration.ChoosePneumaticsPanel choosePneumaticsPanel1;
    private View.GraphicalView.FormulaDButtons.FormulaDWarningButton closeFrameButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel pitNameLabel;
    // End of variables declaration//GEN-END:variables

    @Subscribe
    public void refreshCaracteristicsPoints(CarStatesChangedEvent ev){
        refreshCurrentPlayer(new CurrentPlayerChangedEvent(ev.getPlayer()));
    }
    
    
    @Override
    public void createView(CreateViewEvent ev) {
        this.carStatesViewContainer1.initPanel(ev);
        this.choosePneumaticsPanel1.initPanel(ev);
        refreshCurrentPlayer(new CurrentPlayerChangedEvent(ev.getSender().getPlayersManager().getCurrentPlayer()));
    }

    @Override
    public void refreshApplicationState(ApplicationStateChangedEvent ev) {
    }

    @Override
    public void refreshWeather(WeatherChangedEvent ev) {
    }

    @Override
    @Subscribe
    public void refreshCurrentPlayer(CurrentPlayerChangedEvent ev) {
        this.carStatesViewContainer1.refreshCurrentPlayer(ev, true); // Can increase and dicrease caracteristics points
        this.carNameLabel.setText(ev.getCurrentPlayer().getCar().getName());
        this.pitNameLabel.setText(ev.getCurrentPlayer().getCar().getPit().getName());
        ImageIcon newCarIcon = new ImageIcon(ev.getCurrentPlayer().getCar().getPictureIconPath());
        this.carIcon.setIcon(newCarIcon);
    }

    @Override
    public void refreshNumberOfPlayers(NumberOfPlayersChanged ev) {
    }

    @Override
    public void refreshCircuitAdded(CircuitAddedEvent ev) {
    }

    @Override
    public void refreshPlayerName(PlayerNameChangedEvent ev) {
    }

    @Override
    public void refreshCurrentCar(CurrentPlayerCarChangedEvent ev) {
        refreshCurrentPlayer(new CurrentPlayerChangedEvent(ev.getPlayer()));
    }

    @Override
    public void refreshCurrentPlayerMode(CurrentPlayerModeChangedEvent ev) {
    }

    @Override
    public void refreshCurrentComputerPlayerLevel(ChangeAILevelCurrentComputerPlayer ev) {
    }

    @Override
    public void refreshCircuit(CircuitChangedEvent ev) {
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
}