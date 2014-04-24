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
public class DecreaseStateController implements ActionListener {
    private Game game;
    private String carState;

    public DecreaseStateController(Game game, String carState) {
        this.game = game;
        this.carState = carState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(carState){
            case "pneumatics":
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().removePneumaticsPoints(1);
                break;
            case "brakes" :
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().removeBrakesPoints(1);
                break;
            case "limpWithSpeed" :
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().removeLimpWithSpeedPoints(1);
                break;
            case "body" :
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().removeBodyPoints(1);
                break;
            case "engine" :
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().removeEnginePoints(1);
                break;
            case "roadHolding" :
                this.game.getPlayersManager().getCurrentPlayer().getCar().getCarStates().removeRoadHoldingPoints(1);
                break;
        }
    }
    
    
}
