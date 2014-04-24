/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Configuration;

/**
 *
 * @author matt
 */
public enum Weather {
    Sun("Sun"),
    Rain("Rain");
    
    private String name;
    
    private Weather(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
