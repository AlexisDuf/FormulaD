/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author matt
 */
public enum Pit {
    Ferrari("Ferrari", "./img/pit/ferrari-icon.jpg"),
    RedBull("RedBull", "./img/pit/red-bull-icon.jpg"),
    Mercedes("Mercedes", "./img/pit/mercedes-icon.png"),
    Renault("Renault", "./img/pit/renault-icon.png"),
    Bmw("BMW", "./img/pit/bmw-icon.png"),
    Toyota("Toyota", "./img/pit/toyota-icon.png");
    
    
    private String name;
    private String picturePath;
    
    private Pit(){
        
    }
    
    private Pit(String _name, String _picturePath){
        this.name = _name;
        this.picturePath = _picturePath;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getPicturePath(){
        return this.picturePath;
    }
}
