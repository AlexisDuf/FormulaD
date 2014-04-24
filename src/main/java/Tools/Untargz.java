/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author alexisdufour
 */
public class Untargz {
    public static final String tarForm = "tar";
    public static final String gzForm = "gz";
    public static final String xmlForm = "xml";
    public static final String pngForm = "png";
    public static final String svgForm = "svg";
    
    /**
     * 
     */
    public Untargz() {
        
    }

    
    /**
     * Delete a file
     * @param filer
     * @throws IOException 
     */
    public static void deleteFile(String filer) throws IOException{
        File file = new File(filer);
        Files.delete(file.toPath());
    }
    
    
     /**
     * Create folder 
     * @param s
     * @return 
     */
    public static String createFolder(String s){        
        File dir = new File(s);
        dir.mkdir(); 
        return dir.getAbsolutePath()+ "/";
    }
    
    /**
     * Copy a file
     * @param src
     * @param destFile
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static boolean copyFile(File src , String destFile) throws FileNotFoundException, IOException{
        String[] str = src.getName().split("[.]+");
        if (checkValidity(str)) {
            Files.copy(src.toPath(), new FileOutputStream(destFile));            
            return true;
        }
        return false;
    }
    
    /**
     * Check if the file is a tar.gz
     * @param split
     * @return 
     */
    private static boolean checkValidity(String[] split){
        if (split.length == 3) {
            return split[1].equals(tarForm) && split[2].equals(gzForm); 
        }
        return false;      
    }
    
    /**
     * Check if the form of file is correct, Tar.gz file need a svg file, xml file and png file
     * @param archive
     * @param dest
     * @return
     * @throws IOException 
     */
    public static LinkedList<FileObject> checkValityOfFile(TarArchiveInputStream archive, String dest) throws IOException{
        
        
        LinkedList<FileObject> fileObject = new LinkedList<FileObject>();
        ArchiveEntry next;
        int i = 0;
               
        boolean viewXml = false ; 
        boolean viewSvg = false ; 
        boolean viewPng = false ; 
        
        while(((next = archive.getNextTarEntry())!=null) &&  i<=3){
            i++;
            String[] str = next.getName().split("[.]+");
            byte[] content = new byte[(int) next.getSize()];
            archive.read(content, 0, content.length);
            FileObject o = new FileObject(content, dest + "/" + next.getName());
            if (str.length == 2) {           
                if((viewSvg==false) && str[1].equals(svgForm)){
                    viewSvg = true;
                    fileObject.add(o);
                }else if((viewPng==false) && str[1].equals(pngForm)){
                    viewPng = true;
                    fileObject.add(o);
                }else if((viewXml==false) && str[1].equals(xmlForm)){
                    viewXml = true;
                    fileObject.add(o);
                }
            }            
        }
        
        if((next== null) && (i==3) && viewXml && viewPng && viewSvg){
            return fileObject;
        }else{
            return null;
        }
        
        
       
    }
    

    /**
     * Untar a file
     * @param destFile
     * @param destFolder
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static boolean untargzFile(String destFile, String destFolder) throws FileNotFoundException, IOException{
        
       FileInputStream inFile = new FileInputStream(destFile);
       GZIPInputStream ginFile = new GZIPInputStream(inFile);
       
       TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(ginFile);
        
       LinkedList<FileObject> listOfFile = checkValityOfFile(tarArchiveInputStream, destFolder);
       
       if(listOfFile!=null){
           for(FileObject contentFile: listOfFile){
                OutputStream  out = new FileOutputStream(contentFile.getNameFile());
                IOUtils.write(contentFile.getBytes(), out);
           }
       }
      
        return false;
    }
}
