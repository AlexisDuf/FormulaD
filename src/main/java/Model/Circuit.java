/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author matt
 */
public class Circuit implements Serializable {
    private String name;
    private String folderPath;
    private transient Game game;
    
    public Circuit(){
        this("unknow","",null);
    }

    public Circuit(String name, String folderPath, Game game) {
        this.name = name;
        this.folderPath = folderPath;
        this.game = game;
    }
    
    public String getName(){
        return name;
    }

    public String getFolderPath() {
        return folderPath;
    }
    
    public Game getGame(){
        return this.game;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }
    
    public void setGame(Game game){
        this.game = game;
    }
    
    

    
}
