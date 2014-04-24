/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;

import java.io.Serializable;

/**
 *
 * @author matt
 */
public abstract class Way extends MapElement implements Serializable{

    public Way() {
    }
    
    public Way(int id) {
        super(id);
    }

    public abstract Cell getCell(String string, Integer index);
    
}
