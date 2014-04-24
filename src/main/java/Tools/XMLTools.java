/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Model.Game;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexisdufour
 */
public class XMLTools {

    public XMLTools() {
    }

    public static void encodeToFile(Object o, String fileName) throws FileNotFoundException {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName))) {
            // serialisation de l'objet
            encoder.writeObject(o);
            encoder.flush();
            encoder.close();
        }
    }

    public static Object decodeToFile(String fileName) throws FileNotFoundException {
        Object object = null;
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName))) {
            // deserialisation de l'objet
            object = decoder.readObject();
            decoder.close();
        }
        return object;
    }

    public static void applyTransient(Class c, String attributName) {
        try {
            // On récupère le BeanInfo de la classe User
            BeanInfo info = Introspector.getBeanInfo(c);
            // On récupère les PropertyDescriptors de la classe User via le BeanInfo
            PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();

            for (PropertyDescriptor descriptor : propertyDescriptors) {

                // On met la propriété "transient" à vrai pour le PropertyDescriptor de l'attribut "password"
                if (descriptor.getName().equals(attributName)) {
                    descriptor.setValue("transient", Boolean.TRUE);
                }

            }
        } catch (IntrospectionException ex) {
            Logger.getLogger(XMLTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
