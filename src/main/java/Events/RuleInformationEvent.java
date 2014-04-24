/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

/**
 *
 * @author matt
 */
public class RuleInformationEvent {
    private String message;

    public RuleInformationEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    
}
