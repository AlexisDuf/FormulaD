/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Configuration.Weather;
import Model.Game;
import java.awt.event.ActionEvent;

/**
 *
 * @author matt
 */
public class ChangeWeatherController extends AbstractFormulaDController {
    
    private Weather weather;

    public ChangeWeatherController(Game model, Weather _weather) {
        super(model);
        this.weather = _weather;
    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.setWeather(weather);
    }
    
}
