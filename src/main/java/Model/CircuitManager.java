/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Events.CircuitAddedEvent;
import Events.SelectedBendChangedEvent;
import Model.MVC.EventBusHandler;
import Model.Map.Bend;
import Tools.XMLTools;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matt
 */
public class CircuitManager implements Iterable<Circuit> {

    private LinkedList<Circuit> circuits;
    private EventBusHandler eventBusHandler;
    private Bend selectedBend;
    private static String xmlPath = "./circuits/circuits.xml";
    private static String defaultCircuitName = "Circuit de Monaco";
    private Game game;

    public CircuitManager() {
    }

    public CircuitManager(EventBusHandler eventBusHandler, Game game) {
        this.circuits = new LinkedList<>();
        this.eventBusHandler = eventBusHandler;
        this.game = game;
        recoverCircuits();
    }

    public Circuit getDefaultCircuit() {
        Circuit toReturn = null;
        for (Circuit currentCircuit : circuits) {
            if (currentCircuit.getName().equals(defaultCircuitName)) {
                toReturn = currentCircuit;
            }
        }
        return toReturn;
    }

    private class CircuitIterator implements Iterator<Circuit> {

        Iterator it = circuits.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Circuit next() {
            return (Circuit) it.next();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }

    public Circuit createCircuit(String name, String filePath, Game game) {
        Circuit newCircuit;
        if (circuitExist(name)) {
            return null;
        } else {
            newCircuit = new Circuit(name, filePath, game);
            circuits.add(newCircuit);
        }
        eventBusHandler.getEventBus().post(new CircuitAddedEvent(newCircuit));
        return newCircuit;
    }

    private boolean circuitExist(String name) {
        Iterator<Circuit> it = circuits.iterator();
        boolean found = false;
        Circuit currentCircuit;

        while (it.hasNext() && !found) {
            currentCircuit = it.next();
            if (currentCircuit.getName().equals(name)) {
                found = true;
            }
        }
        return found;
    }

    /*
     * Itérator
     */
    @Override
    public Iterator<Circuit> iterator() {
        return new CircuitIterator();
    }

    /*
     * Utils
     */
    public int getNumberOfCircuits() {
        return circuits.size();
    }

    public void setSelectedBend(Bend _selectedBend) {
        selectedBend = _selectedBend;
        eventBusHandler.getEventBus().post(new SelectedBendChangedEvent(selectedBend));
    }

    public Bend getSelectedBend() {
        return selectedBend;
    }

    private void recoverCircuits() {
        try {
            this.circuits = (LinkedList<Circuit>) XMLTools.decodeToFile(xmlPath);
            for (Circuit currentCircuit : circuits) {
                currentCircuit.setGame(game);
            }
        } catch (FileNotFoundException ex) {
            this.circuits.add(new Circuit(defaultCircuitName, "./circuits/monaco/", game));
        }

    }

    public void saveCircuits() {
        try {
            // On récupère le BeanInfo de la classe User
            BeanInfo info = Introspector.getBeanInfo(Circuit.class);

            // On récupère les PropertyDescriptors de la classe User via le BeanInfo
            PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();

            for (PropertyDescriptor descriptor : propertyDescriptors) {

                // On met la propriété "transient" à vrai pour le PropertyDescriptor de l'attribut "game"
                if (descriptor.getName().equals("game")) {
                    descriptor.setValue("transient", Boolean.TRUE);
                }

            }
            XMLTools.encodeToFile(this.circuits, xmlPath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CircuitManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IntrospectionException ex) {
            Logger.getLogger(CircuitManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
