/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Rules;

import Events.PlayerIsEliminateEvent;
import Events.PlayerLoosePointsEvent;
import Events.RuleInformationEvent;
import Exceptions.SpeedRuleException;
import Model.Cars.Car;
import Model.Cars.CarSpeed;
import Model.Cars.CarState;
import static Model.Dice.BlackDice;
import Model.Players.Player;
import Model.SnapShot;
import com.google.common.eventbus.EventBus;
import java.util.Collection;

/**
 *
 * @author matt
 */
public class SpeedRule extends AbstractRule {

    public SpeedRule() {
    }

    public SpeedRule(EventBus eventBus) {
        super(eventBus);
    }

    /**
     * Check how the player demoted for apply penalization
     *
     * @param p
     */
    public void checkSpeedApplicated(Player p) {
        SnapShot previousSnap = p.getTraceback().getPenultimateSnapShot();
        SnapShot currentSnap = p.getTraceback().getLastSnapShot();

        // get the index of the speed to determinate if the player demotes or raise the  gearbox
        int previousSpeed = previousSnap.getSpeed().ordinal();
        int currentSPeed = currentSnap.getSpeed().ordinal();

        int diff = currentSPeed - previousSpeed;
        // if diff < 0, the player demotes
        if (diff < 0) {
            //Get differents states I need 
            CarState brakes = p.getCar().getCarStates().getBrakesState();
            CarState Limp = p.getCar().getCarStates().getLimpWithSpeedState();
            CarState engine = p.getCar().getCarStates().getEngineState();


            if ((Limp.getCurrentPdv() == 0)) { // If I stay 1 brake pdv 
                p.setEliminate(true, "Limp's points are null so " + p.getPlayerName() + " is eliminated");
                p.getCar().getPosition().setScrapOnCell(true);
                this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where " + p.getPlayerName() + " was eliminated"));

            }

            switch (diff) {
                case -3:
                    if ((brakes.getCurrentPdv() == 0) || (engine.getCurrentPdv() == 0)) {
                        p.setEliminate(true, "brake's or engine's points are null so " + p.getPlayerName() + " is eliminated");
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where " + p.getPlayerName() + " was eliminated"));

                    } else {
                        brakes.removePoints(1);
                        Limp.removePoints(1);
                        engine.removePoints(1);
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + p.getPlayerName() + " lost engine's point "));
                        this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 1 point of engine, 1 point of brake & 1 point of limp", p));
                    }
                    break;
                case -2:
                    if ((brakes.getCurrentPdv() == 0)) {
                        p.setEliminate(true, p.getPlayerName() + " lost all his points of brake ");
                        p.getCar().getPosition().setScrapOnCell(true);
                        this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where " + p.getPlayerName() + " was eliminated"));

                    } else {
                        brakes.removePoints(1);
                        Limp.removePoints(1);
                        this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + "lost 1 point of brake & 1 point of Limp ", p));
                    }
                    break;
                case -1:
                    Limp.removePoints(1);
                    this.eventBus.post(new PlayerLoosePointsEvent(p.getPlayerName() + " lost 1 point of limp ", p));
                    break;
                default:
                    throw new SpeedRuleException();
            }
        } else {
            if (diff > 1) {
                throw new SpeedRuleException();
            }
        }


    }

    /**
     * Check if the player must retire an engine's point
     *
     * @param p
     */
    public void breakEngine(Player p) {
        Collection<Car> carAvaible = Car.getAllAvaibleCar();

        SnapShot currentSnap = p.getTraceback().getLastSnapShot();
        int valueDice = currentSnap.getValueDice();

        if (currentSnap != null) {
            if (valueDice == 20 || valueDice == 30) {
                for (Car car : carAvaible) {
                    int valueBlackDice = BlackDice.getNumber();
                    //int valueBlackDice = 4; //Force valueDice to test if the rules are great
                    CarState engineOtherCar = car.getCarStates().getEngineState();
                    if (valueDice == 20 && car.getSpeed().ordinal() == 4) {
                        if (valueBlackDice <= 4) {
                            if (engineOtherCar.getCurrentPdv() == 0) {
                                car.getPlayer().setEliminate(true, car.getPlayer().getPlayerName() + " lost all his point of engine");
                                car.getPosition().setScrapOnCell(true);
                                this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + car.getPlayer().getPlayerName() + " was eliminated"));

                            } else {
                                engineOtherCar.removePoints(1);
                                car.getPosition().setScrapOnCell(true);
                                this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + car.getPlayer().getPlayerName() + " lost engine's point "));
                                this.eventBus.post(new PlayerLoosePointsEvent(car.getPlayer().getPlayerName() + " lost 1 point of engine", p));
                            }
                        }
                    }
                    if (valueDice == 30 && car.getSpeed().ordinal() == 5) {
                        if (valueBlackDice <= 4) {
                            if (engineOtherCar.getCurrentPdv() == 0) {
                                car.getPlayer().setEliminate(true, car.getPlayer().getPlayerName() + " lost all his point of engine");
                                car.getPosition().setScrapOnCell(true);
                                this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + car.getPlayer().getPlayerName() + " was eliminated"));

                            } else {
                                engineOtherCar.removePoints(1);
                                car.getPosition().setScrapOnCell(true);
                                this.eventBus.post(new RuleInformationEvent("There is a scrap on the cell where the " + car.getPlayer().getPlayerName() + " lost engine's point "));
                                this.eventBus.post(new PlayerLoosePointsEvent(car.getPlayer().getPlayerName() + " lost 1 point of engine", p));
                            }
                        }
                    }
                }
            }
        }
    }

    public static CarSpeed[] getSpeedCanBeApllicated(Player p) {
        int currentIndexSpeed = 0;
        SnapShot currentSnap = p.getTraceback().getPenultimateSnapShot();
        currentIndexSpeed = currentSnap.getSpeed().ordinal();


        CarSpeed[] possibleSpeed;

        if (!p.getCar().getCanIncrementSpeed()) {
            possibleSpeed = new CarSpeed[1];
            possibleSpeed[0] = CarSpeed.values()[0];
            return possibleSpeed;
        } else {
            switch (currentIndexSpeed) {
                case 5:
                    possibleSpeed = new CarSpeed[4];
                    possibleSpeed[0] = CarSpeed.values()[currentIndexSpeed];
                    possibleSpeed[1] = CarSpeed.values()[currentIndexSpeed - 1];
                    possibleSpeed[2] = CarSpeed.values()[currentIndexSpeed - 2];
                    possibleSpeed[3] = CarSpeed.values()[currentIndexSpeed - 3];
                    return possibleSpeed;


                case 4:
                    possibleSpeed = new CarSpeed[5];
                    possibleSpeed[0] = CarSpeed.values()[currentIndexSpeed + 1];
                    possibleSpeed[1] = CarSpeed.values()[currentIndexSpeed];
                    possibleSpeed[2] = CarSpeed.values()[currentIndexSpeed - 1];
                    possibleSpeed[3] = CarSpeed.values()[currentIndexSpeed - 2];
                    possibleSpeed[4] = CarSpeed.values()[currentIndexSpeed - 3];
                    return possibleSpeed;


                case 3:
                    possibleSpeed = new CarSpeed[5];
                    possibleSpeed[0] = CarSpeed.values()[currentIndexSpeed + 1];
                    possibleSpeed[1] = CarSpeed.values()[currentIndexSpeed];
                    possibleSpeed[2] = CarSpeed.values()[currentIndexSpeed - 1];
                    possibleSpeed[3] = CarSpeed.values()[currentIndexSpeed - 2];
                    possibleSpeed[4] = CarSpeed.values()[currentIndexSpeed - 3];
                    return possibleSpeed;


                case 2:
                    possibleSpeed = new CarSpeed[4];
                    possibleSpeed[0] = CarSpeed.values()[currentIndexSpeed + 1];
                    possibleSpeed[1] = CarSpeed.values()[currentIndexSpeed];
                    possibleSpeed[2] = CarSpeed.values()[currentIndexSpeed - 1];
                    possibleSpeed[3] = CarSpeed.values()[currentIndexSpeed - 2];
                    return possibleSpeed;


                case 1:
                    possibleSpeed = new CarSpeed[3];
                    possibleSpeed[0] = CarSpeed.values()[currentIndexSpeed + 1];
                    possibleSpeed[1] = CarSpeed.values()[currentIndexSpeed];
                    possibleSpeed[2] = CarSpeed.values()[currentIndexSpeed - 1];
                    return possibleSpeed;


                case 0:
                    possibleSpeed = new CarSpeed[2];
                    possibleSpeed[0] = CarSpeed.values()[currentIndexSpeed];
                    possibleSpeed[1] = CarSpeed.values()[currentIndexSpeed + 1];
                    return possibleSpeed;
                default:
                    throw new SpeedRuleException();
            }
        }



    }

    @Override
    public void applyRuleBeforeSpeedSelection(Player p) {
    }

    @Override
    public void applyRuleAfterSpeedSelection(Player p) {
        checkSpeedApplicated(p);
    }

    @Override
    public void applyRuleAfterDiceThrow(Player p) {
        breakEngine(p);
    }

    @Override
    public void applyRuleAfterDecision(Player p) {
    }
}
