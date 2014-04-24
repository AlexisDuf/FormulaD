/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Game;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import javax.swing.JFrame;

/**
 *
 * @author alexisdufour
 */
public class KonamiController extends AbstractFormulaDController implements KeyListener {

    int[] keyCode;
    private static int[] realKeyCode = {38, 38, 40, 40, 37,39, 37, 39, 66, 65};

    public KonamiController(Game model) {        
        super(model);
        keyCode = new int[10];
    }
    
 
    private boolean checkCode(){
        int i = 0; 
        boolean goodCode = true;
        
        do{
            if(keyCode[i] != realKeyCode[i]){
                goodCode = false;
            }
            i++;
        }while(i < keyCode.length && goodCode);
        
        return goodCode;
    }

    /**
     * If the value of keycode is correct the value is added, if the konami code is the correct code god mode is activated
     * @param valueCode
     * @return 
     */
    private boolean setValue(int valueCode) {
        int i = 0;
        boolean goodCode = false;
        boolean continueCheck = true;
        
        while(continueCheck){
            if(realKeyCode[i]!= keyCode[i]){
                continueCheck = false;
            } else {
                i++;
            }            
        }
        
        if(valueCode == realKeyCode[i]){
            keyCode[i] = valueCode; 
            goodCode = checkCode();
        } else {
            keyCode = new int[10];
        }     
        System.out.println(goodCode);
        return goodCode;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean code;
        System.out.println(e.getKeyCode());
        code = setValue(e.getKeyCode());
        if(code){
            if(this.model.getDivinMode()){
                this.model.setDivinMode(false);
            }else{
                this.model.setDivinMode(true);
            } 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    
}
