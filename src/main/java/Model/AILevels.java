/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Players.AI.AIInterface;
import Model.Players.AI.GogolAI;
import Model.Players.Player;

/**
 *
 * @author matt
 */
public enum AILevels {
    Beginner("Beginner"){

        @Override
        public AIInterface getIA(Game game, Player player) {
            return new GogolAI(game, player);
        }

    };
    
    public abstract AIInterface getIA(Game game, Player player);
    
    private String name;
    
    
    
    private AILevels(String _name){
        this.name = _name;
        
    }
    
    public String getName(){
        return this.name;
    }
}
