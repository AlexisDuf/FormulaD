/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Players;

import Model.Cars.Car;
import Model.Cars.CarSpeed;
import Model.Map.Cell;
import Model.Map.Map;
import Model.Map.StraightLine;
import Model.SnapShot;
import Model.Traceback;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author alexisdufour
 */
public class ComparePosition implements Comparator<Player> {

    /**
     * Compare two player about their distance
     *
     * @param p1 represents a player
     * @param p2 represents an other player
     * @return
     */
    @Override
    public int compare(Player p1, Player p2) {
        Traceback currentTracebackP1 = p1.getTraceback();
        SnapShot currentSnapP1 = currentTracebackP1.getLastSnapShot();

        Traceback currentTracebackP2 = p2.getTraceback();
        SnapShot currentSnapP2 = currentTracebackP2.getLastSnapShot();

        Cell lastCellP1 = currentSnapP1.getLastCell();
        Cell lastCellP2 = currentSnapP2.getLastCell();

        String[] posP1 = lastCellP1.getComonId().split("[:]+");
        String[] posP2 = lastCellP2.getComonId().split("[:]+");

        int currentSpeedP1 = currentSnapP1.getSpeed().ordinal();
        int currentSpeedP2 = currentSnapP2.getSpeed().ordinal();

        //À Modifier si on stock le tour courant dans le player ! 
        int currentLapP1 = currentSnapP1.getCurrentLap();
        int currentLapP2 = currentSnapP2.getCurrentLap();
        
        if ((p1.isHasWin() || p1.isEliminate()) && (!p2.isHasWin() && !p2.isEliminate())) {
            return 1;
        }
        else if ((p2.isHasWin() || p2.isEliminate()) && (!p1.isHasWin() && !p1.isEliminate())) {
            return -1;
        }
        else if (currentLapP1 < currentLapP2) {
            return 1;
        } else if (currentLapP1 > currentLapP2) {
            return -1;
        } else {
            if (posP1[0].equals(posP2[0])) { //if two players are in the same type of Cell(bend or straightLine)
                if (posP1[1].equals(posP2[1])) { //if the players are in the same bend or straight line 
                    if (posP1[3].equals(posP2[3])) { //If the players are at the same distance to the next bend
                        if (currentSpeedP1 < currentSpeedP2) {
                            return 1;
                        } else if (currentSpeedP1 > currentSpeedP2) {
                            return -1;
                        } else {
                            if (posP1[2].equals("d")) {
                                return 1;
                            } else {
                                if (posP1[2].equals("m") && posP2[2].equals("g")) {
                                    return 1;
                                } else {
                                    return -1;
                                }
                            }
                        }
                    } else {//If the players are not at the same distance to the next bend
                        int posP1LB = Integer.parseInt(posP1[3]);
                        int posP2LB = Integer.parseInt(posP2[3]);
                        if (posP1LB < posP2LB) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                } else {//if the players are not in the same bend or straight line
                    int idP1LB = Integer.parseInt(posP1[1]);
                    int idP2LB = Integer.parseInt(posP2[1]);

                    if (idP1LB < idP2LB) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            } else {//if two players are not in the same type of Cell(bend or straightLine)
                int idP1LB = Integer.parseInt(posP1[1]);
                int idP2LB = Integer.parseInt(posP2[1]);

                if (posP1[0].equals("case")) {
                    if (idP1LB < idP2LB || idP1LB == idP2LB) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    if (idP1LB < idP2LB) {
                        return 1;
                    } else {
                        return -1;
                    }
                }

            }
        }



    }

    public static void main(String[] args) {
        ComparePosition compare = new ComparePosition();
        Map map = new Map("/Users/alexisdufour/Downloads/circuit/");

        HumanPlayer p1 = new HumanPlayer("alex", Car.McLaren, 0, null, null);
        HumanPlayer p2 = new HumanPlayer("matt", Car.BmwSauber, 1, null, null);

        p1.getCar().getCarStates().resetPdvStates();
        p1.getCar().setSpeed(CarSpeed.FifthSpeed);
        p2.getCar().getCarStates().resetPdvStates();
        p2.getCar().setSpeed(CarSpeed.SixthSpeed);

        StraightLine line = (StraightLine) map.getElements().get(10);


        LinkedList<Cell> cellSnapP2 = new LinkedList<>();


        Cell[] cellsLeft = line.getLeftWay();
        Cell[] cellsRight = line.getRightWay();
        Cell[] cellsMid = line.getMidWay();



        cellsRight[1].setPlayer(p2);
        cellsRight[1].setOcupied(true);

        cellsMid[1].setPlayer(p1);
        cellsMid[1].setOcupied(true);

        LinkedList<Cell> cellSnap = new LinkedList<>();

        cellSnapP2.add(cellsRight[1]);
        cellSnap.add(cellsMid[1]);


        SnapShot begin = new SnapShot(8, cellSnap, CarSpeed.FifthSpeed, 1);
        p1.getTraceback().setNewSnapShot(begin);

        SnapShot second = new SnapShot(8, cellSnapP2, CarSpeed.SixthSpeed, 1);
        p2.getTraceback().setNewSnapShot(second);
        
        compare.compare(p1, p2);

    }
}
