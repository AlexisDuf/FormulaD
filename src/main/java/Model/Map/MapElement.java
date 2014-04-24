/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Map;

import java.io.Serializable;

/**
 *
 * @author wasson
 */
public abstract class MapElement implements Serializable{
    private int id;

    public MapElement() {
    }
    
    public int getId() {
        return id;
    }
    
    public MapElement(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
