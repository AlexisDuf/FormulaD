/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cars;

/**
 *
 * @author matt
 */
public enum Pneumatics {
    HardTires("Hard Tires"),
    SoftTires("Soft Tires"),
    RainTires("Rain Tires");
    
    private String name;

    private Pneumatics(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
    
}
