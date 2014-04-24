/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;

import Model.Configuration.Weather;
import Model.Game;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author matt
 */
public class Map implements Serializable{

    private List<MapElement> elements = new LinkedList<>();
    private List<Cell> endLine = new LinkedList<>();
    private List<Cell> startGrid = new LinkedList<>();
    private String folderPath;
    public static final String xmlName = "circuit.xml";
    public static final String pngName = "circuit.png";
    public static final String svgName = "circuit.svg";

    public Map() {
    }
    
    
   
    public Map(String mapPath) {
        this.folderPath = mapPath;
        try {
            DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructeur = fabrique.newDocumentBuilder();
            File xml = new File(mapPath + xmlName);
            Document document = constructeur.parse(xml);
            this.buildMap(document);

        } catch (ParserConfigurationException pce) {
            System.out.println("Erreur de configuration du parseur DOM");
            System.out.println("lors de l'appel à fabrique.newDocumentBuilder();");
        } catch (SAXException se) {
            System.out.println("Erreur lors du parsing du document");
            System.out.println("lors de l'appel à construteur.parse(xml)");
        } catch (IOException ioe) {
            System.out.println("Erreur d'entrée/sortie");
            System.out.println("lors de l'appel à construteur.parse(xml)");
        }

    }
    
    public LinkedList<Cell> getPathBeetweenTwoCell(final Cell departure, final Cell arrival){
        
        class Rec {

            public LinkedList<Cell> fifo = new LinkedList<Cell>();
            public HashMap<Cell, Cell> visited= new HashMap<Cell, Cell>();
            public  LinkedList<Cell>path= new LinkedList<>();
            public boolean found = false;
            public void getDestinationsRec(Cell cell, LinkedList<Cell> traceback) {
                if(cell != null && !found && !visited.containsKey(cell)){
                    if(cell == arrival){
                        this.path = traceback;
                        found = true;
                    }else{
                        traceback.add(cell);
                        visited.put(cell, cell);
                        if(cell.getLeft()!=null){
                            fifo.add(cell.getLeft());
                        }
                        if(cell.getFront()!=null){
                            fifo.add(cell.getFront());
                        }
                        if(cell.getRight()!=null){
                            fifo.add(cell.getRight());
                        }
                        
                        
                    }
                }
                Cell current = fifo.pollFirst();
                if(current != null && !found){
                    getDestinationsRec(current, traceback);
                }
            }
        }
        Rec r = new Rec();
        r.getDestinationsRec(departure, new LinkedList<Cell>());
        return r.path;
    }
    
   
    public String getFolderPath() {
        return folderPath;
    }
    
    public HashMap<Cell, LinkedList<Cell>> getDestinations(Cell cell, int distance, Game game) {
        final boolean rain = (game.getCurrentWeather() == Weather.Rain);
        class Rec {

            public LinkedList<LinkedList<Cell>> list = new LinkedList();
            public HashMap<Cell, LinkedList<Cell>> map= new HashMap<Cell, LinkedList<Cell>>();
            public HashMap<Cell, LinkedList<Cell>> mapDebris= new HashMap<Cell, LinkedList<Cell>>();
            
            private boolean cantMove(Cell cell) {
                Cell leftCell = cell.getLeft();
                Cell rightCell = cell.getRight();
                Cell frontCell = cell.getFront();
                return ((leftCell == null || leftCell.isOcupied()) && (rightCell == null || rightCell.isOcupied()) && (frontCell == null || frontCell.isOcupied()));
            }

            public boolean getDestinationsRec(Cell cell, int distance, boolean canTurnLeft, boolean canTurnRight, LinkedList<Cell> traceback,boolean debris) {
                traceback =(LinkedList<Cell>)traceback.clone();
                if(rain && cell instanceof BendCell){
                    distance += 2;
                } 
                Cell leftCell = cell.getLeft();
                Cell rightCell = cell.getRight();
                Cell frontCell = cell.getFront();
                traceback.add(cell);
                if(cell.isScrapOnCell()){
                    debris = true;
                }
                if (distance <= 0 || cantMove(cell)) {
                    if(distance <= 0 && frontCell!= null &&frontCell.isOcupied()){
                        distance+=2;
                    }
                    if( distance <= 0 && debris && !mapDebris.containsKey(cell) || cantMove(cell)){
                        mapDebris.put(cell, traceback);
                        return true;
                    }else if( distance <= 0 && !map.containsKey(cell) || cantMove(cell)){
                        map.put(cell, traceback);
                        return true;
                    }
                } if(distance >0) {
                    if (canTurnLeft && leftCell != null && !leftCell.isOcupied()) {
                        getDestinationsRec(leftCell, distance - 1, canTurnLeft, false, traceback,debris);
                    }
                    if (canTurnRight && rightCell!= null && !rightCell.isOcupied()) {
                        getDestinationsRec(rightCell, distance - 1, false, canTurnRight, traceback, debris);
                    }
                    if(frontCell != null && !frontCell.isOcupied()){
                        getDestinationsRec(frontCell, distance - 1, canTurnLeft, canTurnRight, traceback,debris);
                    }
                }
                return false;
            }
        }
        Rec r = new Rec();
        r.getDestinationsRec(cell, distance, true, true, new LinkedList<Cell>(),false);
        for (Cell curentcell : r.mapDebris.keySet()) {
                if(!r.map.containsKey(curentcell)){
                     r.map.put(curentcell, r.mapDebris.get(curentcell));
                }
        }
        return r.map;
    }

    public List<Cell> getEndLine() {
        return endLine;
    }

    public List<Cell> getStartGrid() {
        return startGrid;
    }
    
    private void buildMap(Document document) {

        NodeList bends, straightLines, succesors,standBounds, neighbors, arival, startGrid;
        bends = document.getElementsByTagName("virage");
        straightLines = document.getElementsByTagName("ligneDroite");
        succesors = document.getElementsByTagName("successeur");
        neighbors = document.getElementsByTagName("voisin");
        arival = document.getElementsByTagName("traverse");
        startGrid = document.getElementsByTagName("polePosition");
        
        Node current;
        NodeList curlist;
        String curname = "";
        String curdirection = "";
        int curid = 0;
        int nbarrest = 0;
        int rightsize = 0, leftsize = 0, midsize = 0, from = 0, to = 0;
        int [] adjacencyMatrix;
        //builiding bends
        for (int i = 0; i < bends.getLength(); i++) {

            current = bends.item(i);
            curid = Integer.valueOf(current.getAttributes().getNamedItem("id").getTextContent());
            curlist = current.getChildNodes();

            for (int j = 0; j < curlist.getLength(); j++) {
                current = curlist.item(j);
                if (current.getNodeName() == "name") {
                    curname = current.getTextContent();
                }
                if (current.getNodeName() == "direction") {
                    curdirection = current.getTextContent();
                }
                if (current.getNodeName() == "arrets") {
                    nbarrest = Integer.valueOf(current.getTextContent());
                }
                if (current.getNodeName() == "voie") {
                    if (current.getAttributes().getNamedItem("position").getTextContent().equals("gauche")) {
                        leftsize = Integer.valueOf(current.getChildNodes().item(1).getTextContent());
                    } else if (current.getAttributes().getNamedItem("position").getTextContent().equals("milieu")) {
                        midsize = Integer.valueOf(current.getChildNodes().item(1).getTextContent());
                    } else if (current.getAttributes().getNamedItem("position").getTextContent().equals("droite")) {
                        rightsize = Integer.valueOf(current.getChildNodes().item(1).getTextContent());
                    }
                }
            }
            this.elements.add(new Bend(curid, curname, curdirection.equals("left"), nbarrest, midsize, leftsize, rightsize));
        }
        //building straight lines
        for (int i = 0; i < straightLines.getLength(); i++) {

            current = straightLines.item(i);
            curid = Integer.valueOf(current.getAttributes().getNamedItem("id").getTextContent());
            from = Integer.valueOf(current.getAttributes().getNamedItem("from").getTextContent());
            to = Integer.valueOf(current.getAttributes().getNamedItem("to").getTextContent());

            curlist = current.getChildNodes();

            for (int j = 0; j < curlist.getLength(); j++) {
                current = curlist.item(j);

                if (current.getNodeName() == "voie") {
                    if (current.getAttributes().getNamedItem("position").getTextContent().equals("gauche")) {
                        leftsize = Integer.valueOf(current.getChildNodes().item(1).getTextContent());
                    } else if (current.getAttributes().getNamedItem("position").getTextContent().equals("milieu")) {
                        midsize = Integer.valueOf(current.getChildNodes().item(1).getTextContent());
                    } else if (current.getAttributes().getNamedItem("position").getTextContent().equals("droite")) {
                        rightsize = Integer.valueOf(current.getChildNodes().item(1).getTextContent());
                    }
                }
            }

            this.elements.add(new StraightLine(curid, midsize, leftsize, rightsize, this.getBend(from), this.getBend(to)));
        }
        //build pitWay
        int length=0,first=0;
        Cell endPitFront=null, endPitLeft=null,beginPitA=null,beginPitB=null;
        standBounds = document.getElementsByTagName("stands").item(0).getChildNodes();
        for (int i = 0; i < standBounds.getLength(); i++) {
            current = standBounds.item(i);
            if(current.getNodeName() == "sortie"){
                if(endPitLeft == null){
                    endPitLeft=getCellFromString(current.getTextContent());
                }else{
                    endPitFront=getCellFromString(current.getTextContent());
                }
                    
            }
            if(current.getNodeName() == "entree"){
                if(beginPitA == null){
                    beginPitA=getCellFromString(current.getTextContent());
                    
                }else{
                    beginPitB=getCellFromString(current.getTextContent());
                }
                    
            }
            if(current.getNodeName() == "longueur"){
                length = Integer.valueOf(current.getTextContent());
            }
            if(current.getNodeName() == "premierStand"){
                String s =current.getTextContent();
                String d ="[:]+";
                String[] t = s.split(d);
                s=t[1];
                first = Integer.valueOf(s);
            }
            
        }
        PitWay pit = new PitWay(0, length, first,endPitFront, endPitLeft);
        this.elements.add(pit);
        beginPitA.setRight(pit.getCell("", 0));
        beginPitB.setRight(pit.getCell("", 0));
        
        //link with successors 
        String fromS, toS,delim ;
        String[] tokensFrom,tokensTo;
        Cell fromC, toC;

        for(int i = 0; i< succesors.getLength(); i++){
            
            current=succesors.item(i);
            fromS=current.getAttributes().getNamedItem("from").getTextContent();
            toS=current.getAttributes().getNamedItem("to").getTextContent();
            fromC = getCellFromString(fromS);
            toC = getCellFromString(toS);
            String delims = "[:]+";
            tokensFrom = fromS.split(delims);
            tokensTo = toS.split(delims);
            if(tokensFrom[2].equals(tokensTo[2])){
                fromC.setFront(toC);
            }else{
                if(tokensFrom[2].equals("d")){
                    fromC.setLeft(toC);
                }else
                if(tokensFrom[2].equals("g")){
                    fromC.setRight(toC);
                }else
                if(tokensTo[2].equals("g")){
                    fromC.setLeft(toC);
                }else{
                    fromC.setRight(toC);
                }
                
            }
        }
        for (int i = 0; i < neighbors.getLength(); i++) {

            current = neighbors.item(i);
            fromC  = getCellFromString(current.getAttributes().getNamedItem("from").getTextContent());
            toC  = getCellFromString(current.getAttributes().getNamedItem("to").getTextContent());
            fromC.addNeighbor(toC);
            toC.addNeighbor(fromC);
        }
       //set the end line
       current = arival.item(0);
       this.endLine.add(getCellFromString(current.getTextContent()));
       current = arival.item(1);
       this.endLine.add(getCellFromString(current.getTextContent()));
       String rightLineId = current.getTextContent();
       rightLineId = rightLineId.replaceFirst("d", "m");
       this.endLine.add(getCellFromString(new String(rightLineId)));
       //set the startGrid
       current = startGrid.item(0);
       String currentLeft = current.getTextContent();
       String currentRight = current.getTextContent().replaceFirst("g", "d");
       String delims = "[:]+";
       String[] tokens = currentLeft.split(delims);
       
       int currInd = Integer.valueOf(tokens[3]);
       for(int i=0;i<5;i++){
           this.startGrid.add(this.getCellFromString(currentLeft));
           this.startGrid.add(this.getCellFromString(currentRight));
           currentLeft = currentLeft.replaceFirst(new Integer(currInd).toString(),new Integer(currInd-3).toString());
           currentRight = currentRight.replaceFirst(new Integer(currInd).toString(),new Integer(currInd-3).toString());
           currInd -= 3;
           
       }
       
       LinkedList<Cell> ll = new LinkedList<>();
       for(MapElement element: this.elements){
           if(element instanceof StraightLine){
               RaceWay way = (RaceWay)element;
               Cell left = way.getLeftWay()[way.getLeftWay().length-1];
               Cell right = way.getRightWay()[way.getRightWay().length-1];
               String comonId= way.getMidWay()[way.getMidWay().length-1].getFront().getComonId();
               
                if(left.getFront() == null){
                    comonId = comonId.replaceFirst("m", "g");
                    left.setFront(getCellFromString(comonId));
                    left.setRight(way.getMidWay()[way.getMidWay().length-1]);
                }else if(right.getFront() == null){
                    comonId = comonId.replaceFirst("m", "d");
                    right.setFront(getCellFromString(comonId));
                    right.setLeft(way.getMidWay()[way.getMidWay().length-1]);
                }
           }
       } 
    }

    public int getNbBend() {
        int nb = 0;
        for (MapElement mapElement : elements) {
            if (mapElement instanceof Bend) {
                nb++;
            }
        }
        return nb;
    }
    public Cell getCellFromString(String phrase){
        
        String delims = "[:]+";
        String[] tokens = phrase.split(delims);
        Way el=null;
        
        if(tokens[0].equals("virage")){
            el = this.getBend(Integer.valueOf(tokens[1]));
        }else if(tokens[0].equals("case")){
            el = this.getStraightLine(Integer.valueOf(tokens[1]));
        }else if(tokens[0].equals("stand")){
            el = this.getStand();
        }
        if(el!=null){
            return el.getCell(tokens[2],Integer.valueOf(tokens[3]));
        }else{
            return null;
        }
        
    }
    public Bend getBend(int id) {
        Bend bend = null;
        for (MapElement mapElement : elements) {
            if (mapElement instanceof Bend && mapElement.getId() == id) {
                bend = (Bend) mapElement;
            }
        }
        return bend;
    }
    public StraightLine getStraightLine(int id) {
        StraightLine straightLine = null;
        for (MapElement mapElement : elements) {
            if (mapElement instanceof StraightLine && mapElement.getId() == id) {
                straightLine = (StraightLine) mapElement;
            }
        }
        return straightLine;
    }

    

    private PitWay getStand() {
        PitWay pitWay = null;
        for (MapElement mapElement : elements) {
            if (mapElement instanceof PitWay) {
                pitWay = (PitWay) mapElement;
            }
        }
        return pitWay;
    }

    public static String getXmlName() {
        return xmlName;
    }

    public static String getPngName() {
        return pngName;
    }

    public static String getSvgName() {
        return svgName;
    }

    public void setElements(List<MapElement> elements) {
        this.elements = elements;
    }

    public void setEndLine(List<Cell> endLine) {
        this.endLine = endLine;
    }

    public void setStartGrid(List<Cell> startGrid) {
        this.startGrid = startGrid;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }
    
    
    
    

    public List<MapElement> getElements() {
        return elements;
    }
    
    
    public static void main(String[] args) {
        HashMap<Cell, LinkedList<Cell>> c;
        Map a = new Map("/home/wasson/Documents/");
        Cell b =a.getCellFromString("case:1:g:0");
        Cell bb  = a.getCellFromString("case:2:g:0");
        LinkedList<Cell> f = a.getPathBeetweenTwoCell(b, bb);
        
    }

    
    
}
