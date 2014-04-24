/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cars;

import Events.CarPositionChangedEvent;
import Model.Map.Cell;
import Model.Pit;
import Model.Players.Player;
import Model.SnapShot;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author matt
 */
public enum Car {

    FerrariOne("Ferrari1", Pit.Ferrari, "./img/cars/ferrari.png", "./img/cars-icon/ferrari.png", null, false, "#DF1638"),
    FerrariTwo("Ferrari2", Pit.Ferrari, "./img/cars/ferrari.png", "./img/cars-icon/ferrari.png", null, false, "#DF1616"),
    RedBullRenault("RedBullRenault", Pit.RedBull, "./img/cars/redBull.png", "./img/cars-icon/redBull.png", null, false, "#0832D8"),
    RedBullToroRosso("ToroRosso", Pit.RedBull, "./img/cars/toroRosso.png", "./img/cars-icon/toroRosso.png", null, false, "#275095"),
    McLaren("McLaren", Pit.Mercedes, "./img/cars/mcLaren.png", "./img/cars-icon/mcLaren.png", null, false, "#7B7B7B"),
    BrawnGP("BrawnGP", Pit.Mercedes, "./img/cars/brawnGP.png", "./img/cars-icon/brawnGP.png", null, false, "#E57D22"),
    BmwSauber("BmwSauber", Pit.Bmw, "./img/cars/bmwSauber.png", "./img/cars-icon/bmwSauber.png", null, false, "#07A6D7"),
    Williams("Williams", Pit.Bmw, "./img/cars/williams.png", "./img/cars-icon/williams.png", null, false, "#B407D7"),
    ForceIndia("ForceIndia", Pit.Renault, "./img/cars/forceIndia.png", "./img/cars-icon/forceIndia.png", null, false, "#12831D"),
    Renault("Renault", Pit.Renault, "./img/cars/renault.png", "./img/cars-icon/renault.png", null, false, "#DFDF05");
    /*
     * ATTRIBUTS
     */
    private boolean canIncrementSpeed = false;
    private CarSpeed speed;
    private boolean available;
    private String name;
    private Pit pit;
    private String picturePath;
    private String pictureIconPath;
    private CarStates carStates;
    private Cell position;
    private Player player;
    private boolean carSpin;
    private String rgbStringColor;
    private Pneumatics pneumatics;

    /*
     * CONSTRUCTORS
     */
    private Car() {
    }

    private Car(String _name, Pit _pit, String _picturePath, String _pictureIconPath, Player player, boolean carSpin, String rgbStringColor) {
        this.name = _name;
        this.available = true; // De base la voiture est disponible
        this.speed = CarSpeed.FirstSpeed; //De base on part de la vittesse 1
        this.pit = _pit;
        this.picturePath = _picturePath;
        this.pictureIconPath = _pictureIconPath;
        this.player = player;
        this.carStates = new CarStates();
        this.carSpin = carSpin;
        this.rgbStringColor = rgbStringColor;
        this.pneumatics = Pneumatics.SoftTires;
    }

    /*
     * GETTEURS
     */
    public CarSpeed getSpeed() {
        return this.speed;
    }

    public Pit getPit() {
        return this.pit;
    }

    public String getPicturePath() {
        return this.picturePath;
    }

    public String getPictureIconPath() {
        return this.pictureIconPath;
    }

    public CarStates getCarStates() {
        return this.carStates;
    }

    public String getName() {
        return this.name;
    }

    public Cell getPosition() {
        return this.position;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public boolean getCanIncrementSpeed() {
        return this.canIncrementSpeed;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getRgbStringColor() {
        return this.rgbStringColor;
    }

    public Pneumatics getPneumatics() {
        return pneumatics;
    }

    public boolean isCarSpin() {
        return carSpin;
    }

    public boolean isCanIncrementSpeed() {
        return canIncrementSpeed;
    }
    
    
    

    /*
     * SETTEURS
     */
    public void setSpeed(CarSpeed _speed) {
        this.speed = _speed;
    }

    public void setAvailable(boolean _available) {
        this.available = _available;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setPosition(Cell _position, LinkedList<Cell> path) {
        SnapShot lastSnap;
        if (this.position != null) {
            this.position.setOcupied(false);
        }
        Cell oldPosition = this.position;
        this.position = _position;
        this.position.setOcupied(true);
        this.position.setPlayer(player);
        this.getPlayer().getTraceback().getLastSnapShot().setRoute(path);
        this.player.getEventBus().post(new CarPositionChangedEvent(player, oldPosition));
    }

    public void setCanIncrementSpeed(boolean canIncrementSpeed) {
        this.canIncrementSpeed = canIncrementSpeed;
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.carStates.setPlayer(player);
    }

    public void setCarSpin(boolean carSpin) {
        this.carSpin = carSpin;
    }
    
    public void setPneumatics(Pneumatics pneumatics){
        this.pneumatics = pneumatics;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPit(Pit pit) {
        this.pit = pit;
    }

    public void setPictureIconPath(String pictureIconPath) {
        this.pictureIconPath = pictureIconPath;
    }

    public void setCarStates(CarStates carStates) {
        this.carStates = carStates;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public void setRgbStringColor(String rgbStringColor) {
        this.rgbStringColor = rgbStringColor;
    }
    
    

    /*
     * UTILS
     */
    /**
     * Return a list of car which is avaible.
     *
     * @return
     */
    public static Collection<Car> getAllAvaibleCar() {
        Car[] cars = Car.values();
        Collection<Car> toReturn = new LinkedList<>();
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].isAvailable()) {
                toReturn.add(cars[i]);
            }
        }
        return toReturn;
    }

    public void resetDefaultValues() {
        this.carStates.resetPdvStates();
    }
}
