/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View.GraphicalView.GameConfiguration;


import Model.Game;
import Tools.Untargz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author wasson
 */
public class ChooseCircuitfileChooser extends JFileChooser implements ActionListener{
    
    private Game game;

        
    public ChooseCircuitfileChooser(Game game) {
        FileFilter filter = new FileNameExtensionFilter("tar.gz", ".tar.gz");
        this.setFileFilter(filter);
        this.game = game;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("ApproveSelection")){
            File file = this.getSelectedFile();
            try {
                String folder = Untargz.createFolder("./circuits/"+FilenameUtils.removeExtension(this.getSelectedFile().getName()));
                Untargz.untargzFile(file.getAbsolutePath(), folder);
                this.game.getCircuitManager().createCircuit(FilenameUtils.removeExtension(this.getSelectedFile().getName()),folder,this.game);
                this.setVisible(false);
            } catch (IOException ex) {
                Logger.getLogger(ChooseCircuitfileChooser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
