/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Rules;

import Events.PlayerLoosePointsEvent;
import Events.RuleInformationEvent;
import Exceptions.MovementRuleException;
import Model.Cars.CarSpeed;
import Model.Cars.CarState;
import Model.Configuration.Weather;
import static Model.Dice.BlackDice;
import Model.Map.Bend;
import Model.Map.BendCell;
import Model.Map.Cell;
import Model.Map.MapElement;
import Model.Map.PitCell;
import Model.Map.StraightLineCell;
import Model.Players.Player;
import Model.SnapShot;
import Model.Traceback;
import com.google.common.eventbus.EventBus;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author matt
 */
public class MovementRule extends AbstractRule {

    public MovementRule() {
    }

    public MovementRule(EventBus eventBus) {
        super(eventBus);
    }

    /**
     * Increment the current Lap of a player
     *
     * @param p
     */
    public void incrementLap(Player p) {
        Traceback currentTraceback = p.getTraceback();
        SnapShot currentSnap = currentTraceback.getLastSnapShot();

        List<Cell> route = currentSnap.getRoute();
        if (route.size() > 1) {
            route.remove(0);
        }
        Cell currentCell = route.get(route.size() - 1);

        String[] commondIdCell = currentCell.getComonId().split("[:]+");

        MapElement elementCell = currentCell.getMapElement();

        int idElement = elementCell.getId();
        int lapNumber = currentSnap.getCurrentLap();

        boolean canIncrement = false;
        boolean doIncrement = false;
        for (Cell cell : route) {
            if (cell.getMapElement().getId() == 0) {
                doIncrement = true;
            }
        }


        if (doIncrement) {
            if (route.get(0).getMapElement().getId() == 9) {
                if (idElement == 0) {
                    if (currentCell instanceof StraightLineCell) {
                        int position = Integer.parseInt(commondIdCell[3]);
                        if (position >= 16) {
                            lapNumber++;
                            canIncrement = true;
                        }
                    } else {
                        lapNumber++;
                        canIncrement = true;
                    }
                } else {
                    lapNumber++;
                    canIncrement = true;
                }
            } else {
                SnapShot previousSnap = currentTraceback.getPenultimateSnapShot();
                List<Cell> routePrev = previousSnap.getRoute();
                if (routePrev.get(0).getMapElement().getId() >= 8) {
                    canIncrement = true;
                } else {
                    SnapShot thirdSnap = currentTraceback.getSnapShots().get(currentTraceback.getSnapShots().size() - 3);
                    List<Cell> routethird = thirdSnap.getRoute();
                    if (routethird.get(0).getMapElement().getId() >= 8) {
                        canIncrement = true;
                    }
                }

            }


        }
        if (canIncrement) {
            lapNumber++;
            p.setLapNumber(lapNumber);
            this.eventBus.post(new RuleInformationEvent(p.getPlayerName() + " is in lap " + lapNumber));
            if (p.getLapNumber() == 3) {
                p.getGame().getPlayersManager().playerHasFinished(p);
                this.eventBus.post(new RuleInformationEvent(p.getPlayerName() + " finished the game"));
            }
        }


    }

    /**
     * If a player rolled on debris
     *
     * @param p
     */
    public void rideOnDebris(Player p) {
        Traceback currentTraceback = p.getTraceback();
        SnapShot currentSnap = currentTraceback.getLastSnapShot();

        List<Cell> route = currentSnap.getRoute();
        if (route.size() > 1) {
            route.remove(0);
        }

        if (currentSnap != null) {
            for (Cell cell : route) {
                if (cell.isScrapOnCell()) {
                    int valueBlackDice = BlackDice.getNumber();
                    //int valueBlackDice = 4; //Just to test
                    Weather currentWeather = p.getGame().getCurrentWeather();
                    if (currentWeather == Weather.Rain) {
                        if (valueBlackDice <= 5) {
                            CarState roadHolding = p.getCar().getCarStates().getRoadHoldingState();
                            if (roadHolding.getCurrentPdv() == 0) {
                                p.setEliminate(true, p.getPlayerName() + " lost all his point of road holding");

                            } else {
                                roadHolding.removePoints(1);
                                this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 1 point of road holding", p));
                            }
                        }
                    } else {
                        if (valueBlackDice <= 4) {
                            CarState roadHolding = p.getCar().getCarStates().getRoadHoldingState();
                            if (roadHolding.getCurrentPdv() == 0) {
                                p.setEliminate(true, p.getPlayerName() + " lost all his point of road holding");

                            } else {
                                roadHolding.removePoints(1);
                                this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 1 point of road holding", p));
                            }
                        }
                    }

                }
            }
        }

    }

    /**
     * If the car can't move but he still has movement points.
     *
     * @param p
     */
    public void remainingMovement(Player p) {
        Traceback currentTraceback = p.getTraceback();
        SnapShot currentSnap = currentTraceback.getLastSnapShot();

        SnapShot previousSnap = currentTraceback.getPenultimateSnapShot();

        List<Cell> route = currentSnap.getRoute();
        if (route.size() > 1) {
            route.remove(0);
        }

        List<Cell> previousRoute = previousSnap.getRoute();
        if (previousRoute.size() > 1) {
            previousRoute.remove(0);
        }


        Cell endPreviousCell = previousRoute.get(previousRoute.size() - 1);
        Cell penultimatePreviousCell = null;

        if (previousRoute.size() > 2) {
            penultimatePreviousCell = previousRoute.get(previousRoute.size() - 2);
        }


        Cell endCell = route.get(route.size() - 1);

        int valueDice = currentSnap.getValueDice();
        int nbOfMovement = route.size();

        if (nbOfMovement < valueDice && !(endCell instanceof PitCell)) {
            CarState brakes = p.getCar().getCarStates().getBrakesState();
            CarState tire = p.getCar().getCarStates().getPneumaticState();

            if ((nbOfMovement == 1) && (endCell.equals(endPreviousCell) || endCell.equals(penultimatePreviousCell)) && !endCell.getFront().isOcupied()) {
                if (endCell.equals(endPreviousCell)) {
                    if (tire.getCurrentPdv() == 0) {
                        p.setEliminate(true, p.getPlayerName() + " lost all his point of tire");
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                    } else {
                        tire.removePoints(1);
                        this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 1 point of tire", p));
                        if (tire.getCurrentPdv() == 0) {
                            carSpin(p);
                        }
                    }
                }
                if (endCell.equals(penultimatePreviousCell)) {
                    if (brakes.getCurrentPdv() == 0) {
                        p.setEliminate(true, p.getPlayerName() + " lost all his points of brake");
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                    } else {
                        brakes.removePoints(1);
                        this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 1 brake's point", p));
                    }
                }
            } else {
                int diff = valueDice - nbOfMovement;

                Player frontPlayer = route.get(route.size() - 1).getFront().getPlayer();

                CarState bodyCarFront = frontPlayer.getCar().getCarStates().getBodyState();

                if (brakes.getCurrentPdv() == 0 && bodyCarFront.getCurrentPdv() == 0) {
                    p.setEliminate(true, p.getPlayerName() + " lost all his points of brake ");
                    frontPlayer.setEliminate(true, frontPlayer.getPlayerName() + " lost all his points of body ");
                    p.getCar().getPosition().setScrapOnCell(true);
                    frontPlayer.getCar().getPosition().setScrapOnCell(true);
                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));
                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " was eliminated"));
                } else {
                    //Apply penalty on the next car but actually i can't get the car on the next case
                    if (diff <= 6) {
                        switch (diff) {
                            case 1:
                                brakes.removePoints(1);
                                bodyCarFront.removePoints(1);
                                frontPlayer.getCar().getPosition().setScrapOnCell(true);
                                this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " lost points of body"));
                                this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + "lost 1 point of brake & " + frontPlayer.getPlayerName() + " lost 1 point of body", p));
                                break;
                            case 2:
                                if (brakes.getCurrentPdv() >= 2) {
                                    brakes.removePoints(2);
                                    this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 2 brakes point", p));
                                } else {
                                    p.setEliminate(true, p.getPlayerName() + " lost all his points of brake");
                                    p.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                                }
                                if (bodyCarFront.getCurrentPdv() >= 2) {
                                    bodyCarFront.removePoints(2);
                                    frontPlayer.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " lost points of body"));
                                    this.eventBus.post(new PlayerLoosePointsEvent(frontPlayer.getPlayerName() + " lost 2 points of body", p));
                                } else {
                                    frontPlayer.setEliminate(true, frontPlayer.getPlayerName() + " lost all his points of body");
                                    frontPlayer.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " was eliminated"));

                                }
                                break;
                            case 3:
                                if (brakes.getCurrentPdv() >= 3) {
                                    brakes.removePoints(3);
                                    this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 3 points of brake", p));
                                } else {
                                    p.setEliminate(true, p.getPlayerName() + " lost all his points of brake");
                                    p.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                                }
                                if (bodyCarFront.getCurrentPdv() >= 3) {
                                    bodyCarFront.removePoints(3);
                                    frontPlayer.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " lost points of body"));
                                    this.eventBus.post(new PlayerLoosePointsEvent(frontPlayer.getPlayerName() + " lost 3 points of body", p));
                                } else {
                                    frontPlayer.setEliminate(true, frontPlayer.getPlayerName() + " lost all his points of body");
                                    frontPlayer.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " was eliminated"));

                                }
                                break;
                            case 4:
                                if (brakes.getCurrentPdv() >= 3 && tire.getCurrentPdv() >= 1) {
                                    brakes.removePoints(3);
                                    tire.removePoints(1);
                                    this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 3 points of brake & 1 point of tire", p));
                                } else {
                                    p.setEliminate(true, p.getPlayerName() + " lost all his points of brake or tire ");
                                    p.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                                }
                                if (bodyCarFront.getCurrentPdv() >= 4) {
                                    bodyCarFront.removePoints(4);
                                    frontPlayer.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " lost points of body"));
                                    this.eventBus.post(new PlayerLoosePointsEvent(frontPlayer.getPlayerName() + " lost 4 points of body", p));
                                } else {
                                    frontPlayer.setEliminate(true, frontPlayer.getPlayerName() + " lost all his points of body");
                                    frontPlayer.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " was eliminated"));

                                }
                                break;
                            case 5:
                                if (brakes.getCurrentPdv() >= 3 && tire.getCurrentPdv() >= 2) {
                                    brakes.removePoints(3);
                                    tire.removePoints(2);
                                    this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 3 points of brake & 2 points of tire", p));
                                } else {
                                    p.setEliminate(true, p.getPlayerName() + " lost all his points of brake or tire ");
                                    p.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                                }
                                if (bodyCarFront.getCurrentPdv() >= 5) {
                                    bodyCarFront.removePoints(5);
                                    this.eventBus.post(new PlayerLoosePointsEvent(frontPlayer.getPlayerName() + " lost 5 points of body", p));
                                } else {
                                    frontPlayer.setEliminate(true, frontPlayer.getPlayerName() + " lost all his points of body");
                                    frontPlayer.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " was eliminated"));

                                }
                                break;
                            case 6:
                                if (brakes.getCurrentPdv() >= 3 && tire.getCurrentPdv() >= 3) {
                                    brakes.removePoints(3);
                                    tire.removePoints(3);
                                    this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 3 points of brake & 3 points of tire", p));
                                } else {
                                    p.setEliminate(true, p.getPlayerName() + "lost all his points of brake or tire ");
                                    p.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                                }
                                if (bodyCarFront.getCurrentPdv() >= 6) {
                                    bodyCarFront.removePoints(6);
                                    frontPlayer.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " lost points of body"));
                                    this.eventBus.post(new PlayerLoosePointsEvent(frontPlayer.getPlayerName() + " lost 6 points of body", p));
                                } else {
                                    frontPlayer.setEliminate(true, frontPlayer.getPlayerName() + " lost all his points of body");
                                    frontPlayer.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " was eliminated"));

                                }
                                break;
                            default:
                                throw new MovementRuleException();
                        }
                        if (tire.getCurrentPdv() == 0) {
                            carSpin(p);
                        }
                    } else {
                        p.setEliminate(true, p.getPlayerName() + " still has too much movement ");
                        frontPlayer.setEliminate(true, p.getPlayerName() + "still has too much movement so " + frontPlayer.getPlayerName() + " is eliminated");
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));
                        frontPlayer.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + frontPlayer.getPlayerName() + " was eliminated"));
                    }
                    hanging(p);
                }
            }

        }
    }

    /**
     * Check if the player must do a spin (tête à queue)
     *
     * @param p
     */
    public void carSpin(Player p) {
        p.getCar().setSpeed(CarSpeed.FirstSpeed);
        if (!p.getCar().isCarSpin()) {
            p.getCar().setCarSpin(true);
        }
        this.eventBus.post(new RuleInformationEvent(p.getPlayerName() + " made a spin "));
    }

    /**
     * When a car go out bend without stopping
     *
     * @param p
     */
    public void badMouvement(Player p) {
        Traceback currentTraceback = p.getTraceback();
        SnapShot currentSnap = currentTraceback.getLastSnapShot();

        SnapShot thirdSnap = null;
        List<Cell> thirdRoute = null;
        Cell thirdEnd = null;
        String[] commondIdCellThirdPos = null;

        SnapShot forthSnap = null;
        List<Cell> forthRoute = null;
        Cell forthEnd = null;
        String[] commondIdCellForthPos = null;

        SnapShot fifthSnap = null;
        List<Cell> fifthRoute = null;
        Cell fifthEnd = null;
        String[] commondIdCellFifthPos = null;

        if (currentTraceback.getSnapShots().size() > 2) {
            if (!(currentTraceback.getSnapShots().get(currentTraceback.getSnapShots().size() - 3) == null)) {
                thirdSnap = currentTraceback.getSnapShots().get(currentTraceback.getSnapShots().size() - 3);
                thirdRoute = thirdSnap.getRoute();
                if (thirdRoute.size() > 1) {
                    thirdRoute.remove(0);
                }

                thirdEnd = thirdRoute.get(thirdRoute.size() - 1);
                commondIdCellThirdPos = thirdEnd.getComonId().split("[:]+");
            }
        }

        if (currentTraceback.getSnapShots().size() > 3) {
            if (!(currentTraceback.getSnapShots().get(currentTraceback.getSnapShots().size() - 4) == null)) {
                forthSnap = currentTraceback.getSnapShots().get(currentTraceback.getSnapShots().size() - 4);
                forthRoute = forthSnap.getRoute();
                if (forthRoute.size() > 1) {
                    forthRoute.remove(0);
                }
                forthEnd = forthRoute.get(forthRoute.size() - 1);
                commondIdCellForthPos = forthEnd.getComonId().split("[:]+");
            }
        }

        if (currentTraceback.getSnapShots().size() > 4) {
            if (!(currentTraceback.getSnapShots().get(currentTraceback.getSnapShots().size() - 5) == null)) {
                fifthSnap = currentTraceback.getSnapShots().get(currentTraceback.getSnapShots().size() - 5);
                fifthRoute = fifthSnap.getRoute();
                if (fifthRoute.size() > 1) {
                    fifthRoute.remove(0);
                }
                fifthEnd = fifthRoute.get(fifthRoute.size() - 1);
                commondIdCellFifthPos = fifthEnd.getComonId().split("[:]+");
            }
        }




        SnapShot previousSnap = currentTraceback.getPenultimateSnapShot();




        boolean outBend = false;
        int minStop = 0;

        List<Cell> route = currentSnap.getRoute();
        if (route.size() > 1) {
            route.remove(0);
        }

        List<Cell> previousRoute = previousSnap.getRoute();
        if (previousRoute.size() > 1) {
            previousRoute.remove(0);
        }

        Cell previousEnd = previousRoute.get(previousRoute.size() - 1);
        Cell end = route.get(route.size() - 1);

        String[] commondIdCellEndPos = end.getComonId().split("[:]+");
        String[] commondIdCellPreviousPos = previousEnd.getComonId().split("[:]+");




        int posEndCar = Integer.parseInt(commondIdCellEndPos[commondIdCellEndPos.length - 1]);


        boolean doSpin = p.getCar().isCarSpin();

        if (previousEnd instanceof StraightLineCell && end instanceof StraightLineCell) {// if the car passes through a bend apply rules
            for (Cell cell : route) {
                if (cell instanceof BendCell) {
                    Bend bend = (Bend) cell.getMapElement();
                    if (!outBend) {
                        minStop = bend.getMinStop();
                        outBend = true;
                    }
                }
            }
            if (outBend) {
                switch (minStop) {
                    case 1:
                        if (doSpin) {
                            if (posEndCar >= 1) {
                                p.setEliminate(true, p.getPlayerName() + " is eliminated because he have exceeded the number of case which is authorized when he must do a spin ");
                                p.getCar().getPosition().setScrapOnCell(true);
                                this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                            } else {
                                carSpin(p);
                            }
                        }

                        break;
                    case 2:
                        p.setEliminate(true, p.getPlayerName() + " is eliminated because you didn't stop in the bend");
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                        break;
                    case 3:
                        p.setEliminate(true, p.getPlayerName() + " is eliminated because you didn't stop in the bend");
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                    default:
                        throw new MovementRuleException();
                }
            }
        }
        if (previousEnd instanceof BendCell && ((end instanceof StraightLineCell) || ((end instanceof BendCell) && !(commondIdCellPreviousPos[1].equals(commondIdCellEndPos[1]))))) {
            Bend bend = (Bend) previousEnd.getMapElement();
            minStop = bend.getMinStop();

            switch (minStop) {
                case 1:
                    if (thirdEnd instanceof BendCell) {
                        if (!(commondIdCellThirdPos[1].equals(commondIdCellPreviousPos[1]))) {
                            if (doSpin) {
                                if (posEndCar >= 1) {
                                    p.setEliminate(true, p.getPlayerName() + " is eliminated because he have exceeded the number of case which is authorized when he must do a spin ");
                                    p.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                                } else {
                                    carSpin(p);
                                }
                            }
                        }
                    }
                    break;
                case 2:// two stop
                    if ((thirdEnd instanceof BendCell) && (commondIdCellThirdPos[1].equals(commondIdCellPreviousPos[1]))) {
                        if (forthEnd instanceof BendCell && !(commondIdCellForthPos[1].equals(commondIdCellThirdPos[1]))) {
                            //if the car comes another bend , i check the id of bend
                            if (doSpin) {
                                if (posEndCar >= 1) {
                                    p.setEliminate(true, p.getPlayerName() + " is eliminated because he have exceeded the number of case which is authorized when he must do a spin ");
                                    p.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                                } else {
                                    carSpin(p);
                                }
                            }

                        }
                    } else { // if the player didn't stop only once in the bend
                        if (doSpin) {
                            if (posEndCar >= 1) {
                                p.setEliminate(true, p.getPlayerName() + " is eliminated because he have exceeded the number of case which is authorized when he must do a spin ");
                                p.getCar().getPosition().setScrapOnCell(true);
                                this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                            } else {
                                carSpin(p);
                            }
                        }
                    }
                    break;
                case 3:// three stop
                    if (!(thirdEnd instanceof BendCell) || !(commondIdCellThirdPos[1].equals(commondIdCellPreviousPos[1]))) { // if the player stopped only once in the bend he is eliminate
                        p.setEliminate(true, p.getPlayerName() + " is eliminated because he didn't enough stop in the bend");
                    } else {
                        if ((forthEnd instanceof BendCell) && (commondIdCellForthPos[1].equals(commondIdCellThirdPos[1]))) {
                            if (fifthEnd instanceof BendCell && !(commondIdCellFifthPos[1].equals(commondIdCellForthPos[1]))) {
                                //if the car comes another bend , i check the id of bend
                                if (doSpin) {
                                    if (posEndCar >= 1) {
                                        p.setEliminate(true, p.getPlayerName() + " is eliminated because he have exceeded the number of case which is authorized when he must do a spin ");
                                        p.getCar().getPosition().setScrapOnCell(true);
                                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                                    } else {
                                        carSpin(p);
                                    }
                                }
                            }
                        } else { // if the player stopped only twice in the bend
                            if (doSpin) {
                                if (posEndCar >= 1) {
                                    p.setEliminate(true, p.getPlayerName() + " is eliminated because he have exceeded the number of case which is authorized when he must do a spin ");
                                    p.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                                } else {
                                    carSpin(p);
                                }
                            }
                        }
                    }
                    break;
                default:
                    throw new MovementRuleException();
            }
        }
        if (previousEnd instanceof StraightLineCell && end instanceof BendCell) {
            int idBend = Integer.valueOf(commondIdCellEndPos[1]);
            int idLine = Integer.valueOf(commondIdCellPreviousPos[1]);
            if (idLine == 9 && idBend < idLine) {
                for (Cell cell : route) {
                    if (cell instanceof BendCell) {
                        Bend bend = (Bend) cell.getMapElement();
                        if (!outBend) {
                            minStop = bend.getMinStop();
                            outBend = true;
                        }
                    }
                }
                if (outBend) {
                    switch (minStop) {
                        case 1:
                            if (doSpin) {
                                p.setEliminate(true, p.getPlayerName() + " is eliminated because he have exceeded the number of case which is authorized when he must do a spin ");
                                p.getCar().getPosition().setScrapOnCell(true);
                                this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                            }
                            break;
                        case 2:
                            p.setEliminate(true, p.getPlayerName() + " is eliminated because he didn't enough stop in the bend");
                            p.getCar().getPosition().setScrapOnCell(true);
                            this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                            break;
                        case 3:
                            p.setEliminate(true, p.getPlayerName() + " is eliminated because he didn't enough stop in the bend");
                            p.getCar().getPosition().setScrapOnCell(true);
                            this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));

                            break;
                        default:
                            throw new MovementRuleException();
                    }
                }
            } else {
                if (idLine < idBend) {
                    for (Cell cell : route) {
                        if (cell instanceof BendCell) {
                            Bend bend = (Bend) cell.getMapElement();
                            if (!outBend) {
                                minStop = bend.getMinStop();
                                outBend = true;
                            }
                        }
                    }
                    if (outBend) {
                        switch (minStop) {
                            case 1:
                                if (doSpin) {
                                    p.setEliminate(true, p.getPlayerName() + " is eliminated because he have exceeded the number of case which is authorized when he must do a spin ");
                                    p.getCar().getPosition().setScrapOnCell(true);
                                    this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));
                                }
                                break;
                            case 2:
                                p.setEliminate(true, p.getPlayerName() + " is eliminated because he didn't enough stop in the bend");
                                p.getCar().getPosition().setScrapOnCell(true);
                                this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));
                                break;
                            case 3:
                                p.setEliminate(true, p.getPlayerName() + " is eliminated because he didn't enough stop in the bend");
                                p.getCar().getPosition().setScrapOnCell(true);
                                this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));
                                break;
                            default:
                                throw new MovementRuleException();
                        }
                    }
                }
            }

        }





    }

    /**
     * When a player stop his car around others cars
     *
     * @param p
     */
    public void hanging(Player p) {
        Traceback currentTraceback = p.getTraceback();
        SnapShot currentSnap = currentTraceback.getLastSnapShot();
        int nbLaunchDice = 0;
        List<Cell> route = currentSnap.getRoute();
        if (route.size() > 1) {
            route.remove(0);
        }


        LinkedList<Player> neigborsPlayer = new LinkedList<>();

        Cell lastCell = route.get(route.size() - 1);

        Set<Cell> neighbors = lastCell.getNeighbors();

        for (Cell cell : neighbors) {// I store the neighbors if the cell occupied
            if (cell.isOcupied()) {
                neigborsPlayer.add(cell.getPlayer());
                nbLaunchDice++;
            }
        }
        Weather currentWeather = p.getGame().getCurrentWeather();

        int i = 0;
        CarState body = p.getCar().getCarStates().getBodyState();

        while (i < nbLaunchDice && !p.isEliminate()) {
            int valueBlackDice = BlackDice.getNumber();
            //int valueBlackDice = 1; //Just to test  

            if (currentWeather == Weather.Rain) {
                if (valueBlackDice <= 2) {
                    if (body.getCurrentPdv() == 0) {
                        p.setEliminate(true, p.getPlayerName() + " is eliminated because the body's points are null");
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));
                    } else {
                        body.removePoints(1);
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " lost points of body"));
                        this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 1 point of body", p));
                    }
                }
            } else {
                if (valueBlackDice == 1) {
                    if (body.getCurrentPdv() == 0) {
                        p.setEliminate(true, p.getPlayerName() + " is eliminated because the body's points are null");
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " was eliminated"));
                    } else {
                        body.removePoints(1);
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " lost points of body"));
                        this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 1 point of body", p));
                    }
                }
            }

            i++;
        }

        // I throw the dice for all neighbors.
        for (Player neighbor : neigborsPlayer) {
            CarState bodyNeighbor = neighbor.getCar().getCarStates().getBodyState();
            int valueBlackDice = BlackDice.getNumber();
            //int valueBlackDice = 1; //Just to test

            if (currentWeather == Weather.Rain) {
                if (valueBlackDice <= 1) {
                    if (bodyNeighbor.getCurrentPdv() == 0) {
                        neighbor.setEliminate(true, neighbor.getPlayerName() + " is eliminated because the body's points are null");
                        neighbor.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + neighbor.getPlayerName() + " was eliminated"));
                    } else {
                        bodyNeighbor.removePoints(1);
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + neighbor.getPlayerName() + " lost points of body"));
                        this.eventBus.post(new PlayerLoosePointsEvent(neighbor.getPlayerName() + " lost 1 point of body", neighbor));
                    }
                }
            } else {
                if (valueBlackDice == 1) {
                    if (bodyNeighbor.getCurrentPdv() == 0) {
                        neighbor.setEliminate(true, neighbor.getPlayerName() + " is eliminated because the body's points are null");
                        neighbor.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + neighbor.getPlayerName() + " was eliminated"));
                    } else {
                        bodyNeighbor.removePoints(1);
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + neighbor.getPlayerName() + " lost points of body"));
                        this.eventBus.post(new PlayerLoosePointsEvent(neighbor.getPlayerName() + " lost 1 point of body", neighbor));
                    }
                }
            }



        }


    }

    @Override
    public void applyRuleBeforeSpeedSelection(Player p) {
    }

    @Override
    public void applyRuleAfterSpeedSelection(Player p) {
    }

    @Override
    public void applyRuleAfterDiceThrow(Player p) {
    }

    @Override
    public void applyRuleAfterDecision(Player p) {
        rideOnDebris(p);

        badMouvement(p);

        remainingMovement(p);

        hanging(p);
    }
}
