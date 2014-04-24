/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.BoardView.GameOver;

import javax.swing.ImageIcon;

/**
 *
 * @author matt
 */
public class GameOverRecapItem extends javax.swing.JPanel {

    /**
     * Creates new form GameOverRecapItem
     */
    public GameOverRecapItem() {
        initComponents();
    }
    
    public GameOverRecapItem(String iconPath, String playerName){
        initComponents();
        this.carIconLabel.setIcon(new ImageIcon(iconPath));
        this.playerNameLabel.setText(playerName);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerNameLabel = new javax.swing.JLabel();
        carIconLabel = new javax.swing.JLabel();

        setForeground(new java.awt.Color(255, 255, 255));
        setEnabled(false);
        setMaximumSize(new java.awt.Dimension(500, 70));
        setMinimumSize(new java.awt.Dimension(500, 70));
        setPreferredSize(new java.awt.Dimension(500, 70));

        playerNameLabel.setFont(new java.awt.Font("Ubuntu", 0, 25)); // NOI18N
        playerNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        playerNameLabel.setText("Matthieu");

        carIconLabel.setMaximumSize(new java.awt.Dimension(70, 70));
        carIconLabel.setMinimumSize(new java.awt.Dimension(70, 70));
        carIconLabel.setPreferredSize(new java.awt.Dimension(70, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(carIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(carIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(playerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel carIconLabel;
    private javax.swing.JLabel playerNameLabel;
    // End of variables declaration//GEN-END:variables
}
