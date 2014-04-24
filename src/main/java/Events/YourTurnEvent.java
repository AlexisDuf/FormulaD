/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

/**
 *
 * @author matt
 */
public class YourTurnEvent {
    private String token;

    public YourTurnEvent(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    
    
}
