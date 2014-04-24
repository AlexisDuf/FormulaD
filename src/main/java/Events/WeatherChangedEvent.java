/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Model.Configuration.Weather;

/**
 *
 * @author matt
 */
public class WeatherChangedEvent {
    private Weather weather;

    public WeatherChangedEvent(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }
    
}
