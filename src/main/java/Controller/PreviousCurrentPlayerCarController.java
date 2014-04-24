/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cars.Car;
import Model.Game;
import Model.Players.Player;
import java.awt.event.ActionEvent;

/**
 *
 * @author matt
 */
public class PreviousCurrentPlayerCarController extends AbstractFormulaDController {
    
    public static Car getPreviousAvailableCar(Car currentCar){
        Car returnCar;
        Car[] cars = Car.values();
        boolean found = false;
        int carIndex = currentCar.ordinal();
        int i = carIndex;
        while (i>=0 && !found) {
            if(cars[i].isAvailable()){
                found = true;
            }
            i--;
        }
        if(found){
            if(i==-1){
                i++;
            }
            return cars[i];
        }else{
            i=cars.length-1;
            while(i>carIndex && !found){
                if(cars[i].isAvailable()){
                    found = true;
                }
                i--;
            }
            
            if(found){
                if(i==carIndex){
                    i++;
                }
                return cars[i];
            }else{
                return null;
            }
        }
    }

    public PreviousCurrentPlayerCarController(Game model){
        super(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Car previousCar = getPreviousAvailableCar(this.model.getPlayersManager().getCurrentPlayer().getCar());
        if(previousCar != null){
            this.model.getPlayersManager().getCurrentPlayer().setCar(previousCar);
        }
    }
    
}
