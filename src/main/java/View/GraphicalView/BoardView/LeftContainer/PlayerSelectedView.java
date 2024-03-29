/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.BoardView.LeftContainer;

import Events.CarPositionChangedEvent;
import Events.CarStatesChangedEvent;
import Events.ChangedClassementEvent;
import Events.CreateViewEvent;
import Events.CurrentPlayerCarSpeedChanged;
import Events.CurrentPlayerChangedEvent;
import Events.SelectedPlayerChangedEvent;
import Events.ThrowCurrentPlayerDiceEvent;
import Events.CurrentPlayerDiceValueChangedEvent;
import Events.DivinModeChangedEvent;
import Events.GameOverEvent;
import Events.PlayerFinishedALapEvent;
import Events.PlayerIsEliminateEvent;
import Events.PlayerLoosePointsEvent;
import Events.PlayerWonEvent;
import Events.PlayersSortedEvent;
import Events.RuleInformationEvent;
import Events.ScrapOnCellEvent;
import Events.SelectedBendChangedEvent;
import Model.Players.Player;
import View.AbstractFormulaDView;
import View.AbstractGameView;
import com.google.common.eventbus.Subscribe;

/**
 *
 * @author matt
 */
public class PlayerSelectedView extends javax.swing.JPanel implements AbstractGameView, AbstractFormulaDView{
    
    private Player selectedPlayer;

    /**
     * Creates new form InformationsView
     */
    public PlayerSelectedView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        carStatesViewContainer1 = new View.GraphicalView.BoardView.LeftContainer.CarStatesViewContainer();
        jLabel1 = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nbLapLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        positionPlayerLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        numberOfThrowPanel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        playerSpeedLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        carNameLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pitNameLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(114, 167, 178));
        setMaximumSize(new java.awt.Dimension(336, 32767));
        setMinimumSize(new java.awt.Dimension(336, 0));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(47, 47, 47));
        jLabel1.setText("Nom : ");

        playerNameLabel.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        playerNameLabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(47, 47, 47));
        jLabel3.setText("Lap");

        nbLapLabel.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        nbLapLabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(47, 47, 47));
        jLabel5.setText("Position :");

        positionPlayerLabel.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        positionPlayerLabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(47, 47, 47));
        jLabel7.setText("Number :");

        numberOfThrowPanel.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        numberOfThrowPanel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(47, 47, 47));
        jLabel9.setText("Speed");

        playerSpeedLabel.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        playerSpeedLabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(47, 47, 47));
        jLabel11.setText("Car :");

        carNameLabel.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        carNameLabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        jLabel13.setText("Pit :");

        pitNameLabel.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        pitNameLabel.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(76, 76, 76));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Car informations");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(76, 76, 76));

        jLabel16.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("User informations");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(playerNameLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(playerSpeedLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nbLapLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(positionPlayerLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(numberOfThrowPanel))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(carNameLabel)
                                    .addComponent(pitNameLabel)))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carStatesViewContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(playerNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nbLapLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(positionPlayerLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(numberOfThrowPanel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(playerSpeedLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carNameLabel)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pitNameLabel)
                    .addComponent(jLabel13))
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addComponent(carStatesViewContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel carNameLabel;
    private View.GraphicalView.BoardView.LeftContainer.CarStatesViewContainer carStatesViewContainer1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nbLapLabel;
    private javax.swing.JLabel numberOfThrowPanel;
    private javax.swing.JLabel pitNameLabel;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JLabel playerSpeedLabel;
    private javax.swing.JLabel positionPlayerLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void createView(CreateViewEvent ev) {}

    @Override
    public void refreshDice(ThrowCurrentPlayerDiceEvent ev) {}

    @Override
    @Subscribe
    public void refreshClassement(ChangedClassementEvent ev) {
        this.positionPlayerLabel.setText(""+this.selectedPlayer.getClassementPosition());
    }

    @Override
    public void refreshCarPosition(CarPositionChangedEvent ev) {}

    @Override
    public void refreshCurrentPlayer(CurrentPlayerChangedEvent ev) {}

    @Override
    @Subscribe
    public void refreshSelectedPlayer(SelectedPlayerChangedEvent ev) {
        this.carNameLabel.setText(ev.getSender().getSelectedPlayer().getCar().getName());
        this.nbLapLabel.setText(""+ev.getSender().getSelectedPlayer().getLapNumber());
        this.numberOfThrowPanel.setText(""+ev.getSender().getSelectedPlayer().getNumberOfThrow());
        this.pitNameLabel.setText(ev.getSender().getSelectedPlayer().getCar().getPit().getName());
        this.playerNameLabel.setText(ev.getSender().getSelectedPlayer().getPlayerName());
        this.playerSpeedLabel.setText(""+ev.getSender().getSelectedPlayer().getCar().getSpeed());
        this.positionPlayerLabel.setText(""+ev.getSender().getSelectedPlayer().getClassementPosition());
        this.selectedPlayer = ev.getSender().getSelectedPlayer();
        this.carStatesViewContainer1.refreshCurrentPlayer(new CurrentPlayerChangedEvent(selectedPlayer), false);
    }

    @Override
    public void refreshCurrentPlayerDiceValue(CurrentPlayerDiceValueChangedEvent ev) {}

    @Override
    public void refreshCurrentPlayerCarSpeed(CurrentPlayerCarSpeedChanged ev) {}

    @Override
    public void refreshSelectedBend(SelectedBendChangedEvent ev) {
    }
    
    @Override
    public void refreshCarStates(CarStatesChangedEvent ev) {
    }

    @Override
    public void refreshSortedPlayer(PlayersSortedEvent ev) {}

    @Override
    public void refreshPlayerIsEliminate(PlayerIsEliminateEvent ev) {
    }

    @Override
    public void refreshRuleInformation(RuleInformationEvent ev) {
    }

    @Override
    public void refreshPlayerLoosePoint(PlayerLoosePointsEvent ev) {
    }

    @Override
    public void refreshPlayerFinishedLap(PlayerFinishedALapEvent ev) {
        if(this.selectedPlayer == ev.getPlayer()){
            this.nbLapLabel.setText(""+ev.getLap());
        }
    }

    @Override
    public void refreshPlayerWon(PlayerWonEvent ev) {
    }

    @Override
    public void refreshDivinMode(DivinModeChangedEvent ev) {
    }

    @Override
    public void refreshGameOver(GameOverEvent ev) {
    }

    @Override
    public void refreshCell(ScrapOnCellEvent ev) {
    }
    
    
}
