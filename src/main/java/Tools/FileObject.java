/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tools;

/**
 *
 * @author alexisdufour
 */
public class FileObject {
    private byte[] bytes;
    private String nameFile;

    public FileObject() {
    }

    public FileObject(byte[] bytes, String nameFile) {
        this.bytes = bytes;
        this.nameFile = nameFile;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
    
    
    
  
    
}