/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Model.Game;
import View.ConsoleView;
import View.GraphicalView.FormulaDGraphicalView;

/**
 *
 * @author matt
 */
public class Launcher {
    

    
    public static void main(String[] args) {
        /*
         * Création du jeu
         */
        Game game = new Game();
        
        /*
         * Création des circuits 
         */
        
       // game.getCircuitManager().createCircuit("Monaco", "", 50, 100);
        
        FormulaDGraphicalView currentJFrame = new FormulaDGraphicalView("Formula De", 1500, 1000, true);
        game.getEventBusHandler().addFormulaDView(currentJFrame, game);
        game.getEventBusHandler().addFormulaDView(new ConsoleView(), game);
    }
}
