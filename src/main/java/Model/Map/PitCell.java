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
public class PitCell extends Cell implements Serializable{

    public PitCell() {
    }

    
    public PitCell(String comonId, MapElement element) {
        super(comonId, element);
    }
    
}
