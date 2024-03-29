/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.BoardView.LeftContainer;

import Controller.DecreaseStateController;
import Controller.IncreaseStateController;
import Events.CreateViewEvent;
import Events.CurrentPlayerChangedEvent;
import Model.Cars.CarStates;

/**
 *
 * @author matt
 */
public class CarStatesViewContainer extends javax.swing.JPanel{

    /**
     * Creates new form CarStatesViewContainer
     */
    public CarStatesViewContainer() {
        initComponents();
        desactiveButtons();
    }
    
    public CarStatesViewContainer(CreateViewEvent ev){
        refreshCurrentPlayer(new CurrentPlayerChangedEvent(ev.getSender().getPlayersManager().getCurrentPlayer()), false);
        initListenners(ev);
    }
    
    public void initPanel(CreateViewEvent ev){
        refreshCurrentPlayer(new CurrentPlayerChangedEvent(ev.getSender().getPlayersManager().getCurrentPlayer()), false);
        initListenners(ev);
    }
    
    public void initListenners(CreateViewEvent ev){
        this.bodyItem.initListenners(new IncreaseStateController(ev.getSender(),"body"), new DecreaseStateController(ev.getSender(), "body"));
        this.brakesItem.initListenners(new IncreaseStateController(ev.getSender(),"brakes"), new DecreaseStateController(ev.getSender(), "brakes"));
        this.engineItem.initListenners(new IncreaseStateController(ev.getSender(),"engine"), new DecreaseStateController(ev.getSender(), "engine"));
        this.limpWithSpeedItem.initListenners(new IncreaseStateController(ev.getSender(),"limpWithSpeed"), new DecreaseStateController(ev.getSender(), "limpWithSpeed"));
        this.pneumaticsItem.initListenners(new IncreaseStateController(ev.getSender(),"pneumatics"), new DecreaseStateController(ev.getSender(), "pneumatics"));
        this.roadHoldingItem.initListenners(new IncreaseStateController(ev.getSender(),"roadHolding"), new DecreaseStateController(ev.getSender(), "roadHolding"));
    }
    
    private void activateButtons(){
        this.bodyItem.activateButtons();
        this.brakesItem.activateButtons();
        this.engineItem.activateButtons();
        this.limpWithSpeedItem.activateButtons();
        this.pneumaticsItem.activateButtons();
        this.roadHoldingItem.activateButtons();
    }
    
    private void desactiveButtons(){
        this.bodyItem.desactivateButtons();
        this.brakesItem.desactivateButtons();
        this.engineItem.desactivateButtons();
        this.limpWithSpeedItem.desactivateButtons();
        this.pneumaticsItem.desactivateButtons();
        this.roadHoldingItem.desactivateButtons();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pneumaticsItem = new View.GraphicalView.BoardView.LeftContainer.CarStateViewItem("./img/controlPanelIcon/pneumatic-icon.png","");
        brakesItem = new View.GraphicalView.BoardView.LeftContainer.CarStateViewItem("./img/controlPanelIcon/brakes-icon.png", "");
        limpWithSpeedItem = new View.GraphicalView.BoardView.LeftContainer.CarStateViewItem("./img/controlPanelIcon/limpWithSpeed-icon.png","");
        bodyItem = new View.GraphicalView.BoardView.LeftContainer.CarStateViewItem("./img/controlPanelIcon/body-icon.png", "");
        engineItem = new View.GraphicalView.BoardView.LeftContainer.CarStateViewItem("./img/controlPanelIcon/engine-icon.png", "");
        roadHoldingItem = new View.GraphicalView.BoardView.LeftContainer.CarStateViewItem("./img/controlPanelIcon/roadHolding-icon.png", "");

        setBackground(new java.awt.Color(116, 167, 178));
        setMaximumSize(new java.awt.Dimension(180, 294));
        setMinimumSize(new java.awt.Dimension(180, 294));
        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pneumaticsItem, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
            .addComponent(brakesItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(limpWithSpeedItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bodyItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roadHoldingItem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(engineItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pneumaticsItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brakesItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(limpWithSpeedItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bodyItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(engineItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roadHoldingItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.GraphicalView.BoardView.LeftContainer.CarStateViewItem bodyItem;
    private View.GraphicalView.BoardView.LeftContainer.CarStateViewItem brakesItem;
    private View.GraphicalView.BoardView.LeftContainer.CarStateViewItem engineItem;
    private View.GraphicalView.BoardView.LeftContainer.CarStateViewItem limpWithSpeedItem;
    private View.GraphicalView.BoardView.LeftContainer.CarStateViewItem pneumaticsItem;
    private View.GraphicalView.BoardView.LeftContainer.CarStateViewItem roadHoldingItem;
    // End of variables declaration//GEN-END:variables

    public final void refreshCurrentPlayer(CurrentPlayerChangedEvent ev, boolean modifyMode) {
        CarStates states = ev.getCurrentPlayer().getCar().getCarStates();
        this.bodyItem.setDescription(""+states.getBodyState().getCurrentPdv()+" / "+states.maxPdvBody);
        this.brakesItem.setDescription(""+states.getBrakesState().getCurrentPdv()+" / "+states.maxPdvBrakes);
        this.engineItem.setDescription(""+states.getEngineState().getCurrentPdv()+" / "+states.maxPdvEngine);
        this.limpWithSpeedItem.setDescription(""+states.getLimpWithSpeedState().getCurrentPdv()+" / "+states.maxPdvLimpWithSpeed);
        this.pneumaticsItem.setDescription(""+states.getPneumaticState().getCurrentPdv()+" / "+states.maxPdvPneumatics);
        this.roadHoldingItem.setDescription((""+states.getRoadHoldingState().getCurrentPdv()+" / "+states.maxPdvRoadHolding));
        if(modifyMode){
            activateButtons();
        }else{
            desactiveButtons();
        }
        
    }
}
