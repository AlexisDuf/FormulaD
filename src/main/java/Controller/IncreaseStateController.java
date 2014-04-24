/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author matt
 */
public class IncreaseStateController implements ActionListener {
    private Game game;
    private String carState;

    public IncreaseStateController(Game game, String carState) {
        this.game = game; 
        this.carState = carState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(carState){
            case "pneumatics":
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().addPneumaticsPoints(1);
                break;
            case "brakes" :
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().addBrakesPoints(1);
                break;
            case "limpWithSpeed" :
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().addLimpWithSpeedPoints(1);
                break;
            case "body" :
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().addBodyPoints(1);
                break;
            case "engine" :
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().addEnginePoints(1);
                break;
            case "roadHolding" :
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().addRoadHoldingPoints(1);
                break;
        }
    }
    
    
    
}
