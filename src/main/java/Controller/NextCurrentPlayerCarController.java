/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cars.Car;
import Model.Game;
import java.awt.event.ActionEvent;

/**
 *
 * @author matt
 */
public class NextCurrentPlayerCarController extends AbstractFormulaDController{

    public static Car getNextAvailableCar(Car currentCar){
        Car returnCar;
        Car[] cars = Car.values();
        boolean found = false;
        int carIndex = currentCar.ordinal();
        int i = carIndex;
        while (i<cars.length && !found) {
            if(cars[i].isAvailable()){
                found = true;
            }
            i++;
        }
        if(found){
            if(i==cars.length){
                i--;
            }
            return cars[i];
        }else{
            i=0;
            while(i<carIndex && !found){
                if(cars[i].isAvailable()){
                    found = true;
                }
                i++;
            }
            
            if(found){
                if(i==carIndex){
                    i--;
                }
                return cars[i];
            }else{
                return null;
            }
        }
    }

    public NextCurrentPlayerCarController(Game model) {
        super(model);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Car nextCar = getNextAvailableCar(this.model.getPlayersManager().getCurrentPlayer().getCar());
        if(nextCar != null){
            this.model.getPlayersManager().getCurrentPlayer().setCar(nextCar);
        }
    }
    
}
