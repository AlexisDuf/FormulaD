/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;

import Events.ScrapOnCellEvent;
import Model.Players.Player;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author matt
 */
public class Cell implements Serializable {

    private boolean ocupied;
    private Player player = null;
    private boolean scrapOnCell;
    private Cell right, front, left;
    private Set<Cell> neighbors;
    private String comonId;
    private MapElement mapElement;

    public Cell() {
    }

    public Cell(String comonId, MapElement element) {
        this.mapElement = element;
        this.comonId = comonId;
        this.ocupied = false;
        this.right = null;
        this.front = null;
        this.left = null;
        this.neighbors = new HashSet<Cell>();
    }

    public Cell(boolean ocupied, Cell right, Cell front, Cell left, Set<Cell> neighbors, String comonId, MapElement element) {
        this.mapElement = element;
        this.comonId = comonId;
        this.ocupied = ocupied;
        this.right = right;
        this.front = front;
        this.left = left;
        this.neighbors = neighbors;
        this.scrapOnCell = false;
    }

    public MapElement getMapElement() {
        return mapElement;
    }

    public Player getPlayer() {
        if(ocupied){
           return player; 
        }else{
            return null;
        }
        
    }

    public void setPlayer(Player player) {
        this.setOcupied(player != null);
        this.player = player;
    }

    public String getComonId() {
        return comonId;
    }

    public boolean isScrapOnCell() {
        return scrapOnCell;
    }

    public void setScrapOnCell(boolean scrapOnCell) {
        this.scrapOnCell = scrapOnCell;
        if(player != null){
            player.getGame().getPlayersManager().getEventBus().post(new ScrapOnCellEvent(this,player.getGame()));
        }
    }

    public boolean isOcupied() {
        return ocupied;
    }

    public Set<Cell> getNeighbors() {
        return neighbors;
    }

    public Cell getLeft() {
        return left;
    }

    public Cell getRight() {
        return right;
    }

    public Cell getFront() {
        return front;
    }

    public void setOcupied(boolean ocupied) {
        this.ocupied = ocupied;
    }

    public void addNeighbor(Cell cell) {
        neighbors.add(cell);

    }

    public void setRight(Cell right) {
        this.right = right;
        right.addNeighbor(this);
        addNeighbor(right);
    }

    public void setFront(Cell front) {
        this.front = front;
        front.addNeighbor(this);
        addNeighbor(front);
    }

    public void setLeft(Cell left) {
        this.left = left;
        left.addNeighbor(this);
        addNeighbor(left);
    }

    public void setNeighbors(Set<Cell> neighbors) {
        this.neighbors = neighbors;
    }

    public void setComonId(String comonId) {
        this.comonId = comonId;
    }

    public void setMapElement(MapElement mapElement) {
        this.mapElement = mapElement;
    }

    @Override
    public Cell clone() throws CloneNotSupportedException {
        return new Cell(ocupied, right, front, left, neighbors, comonId, mapElement);
    }
}
