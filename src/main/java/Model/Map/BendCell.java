/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author matt
 */
public class BendCell extends Cell implements Serializable{

    public BendCell() {
    }

    
    public BendCell(String comonId,MapElement element) {
        super(comonId, element);
    }
    
    

    public BendCell(boolean ocupied, Cell right, Cell front, Cell left, Set<Cell> neighbors, String comonId, MapElement element) {
        super(ocupied, right, front, left, neighbors, comonId,element);
    }
    
}
