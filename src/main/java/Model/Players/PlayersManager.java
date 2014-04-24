/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Players;

import Events.CurrentPlayerCarSpeedChanged;
import Events.CurrentPlayerChangedEvent;
import Events.CurrentPlayerModeChangedEvent;
import Events.NumberOfPlayersChanged;
import Events.PlayerIsEliminateEvent;
import Events.PlayerWonEvent;
import Events.PlayersSortedEvent;
import Events.SelectedPlayerChangedEvent;
import Events.YourTurnEvent;
import Exceptions.PlayerNameAlreadyExistFormulaDException;
import Model.AILevels;
import Model.ApplicationState;
import Model.Cars.Car;
import Model.Game;
import Model.SnapShot;
import Tools.TokenGenerator;
import com.google.common.eventbus.EventBus;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author matt
 */
public class PlayersManager implements Serializable {

    public static int defaultNumberOfHumanPlayer = 2;
    public static int defaultNumberOfComputerPlayer = 1;
    public static AILevels defaultAILevel = AILevels.Beginner;
    private Collection<HumanPlayer> humanPlayers;
    private Collection<ComputerPlayer> computerPlayers;
    private Collection<Player> winPlayers;
    private Collection<Player> loosePlayers;
    private transient EventBus eventBus;
    private Player currentPlayer;
    private Player selectedPlayer;
    private transient Game game;
    private String token;
    /*
     * Game
     */
    private Collection<Player> players;
    private Iterator<Player> currentPlayerIt = null;
    private boolean currentPlayerSpeedIsChoosed = false;

    public PlayersManager() {
    }

    public PlayersManager(EventBus eventBus, Game game) {
        this.humanPlayers = new LinkedList<>();
        this.computerPlayers = new LinkedList<>();
        this.eventBus = eventBus;
        this.game = game;
        this.winPlayers = new LinkedList<>();
        this.loosePlayers = new LinkedList<>();
        initPlayersManager();
    }

    /*
     * Par défaut 5 joueurs 1 human et 4 computers
     */
    private void initPlayersManager() {
        Car[] cars = Car.values();
        int carIndex = 0;
        for (int i = 0; i < defaultNumberOfHumanPlayer; i++) {
            this.humanPlayers.add(new HumanPlayer("Player" + carIndex, cars[carIndex], carIndex, eventBus, this.game));
            carIndex++;
        }

        for (int i = 0; i < defaultNumberOfComputerPlayer; i++) {
            this.computerPlayers.add(new ComputerPlayer("Player" + carIndex, cars[carIndex], carIndex, defaultAILevel, eventBus, this.game));
            carIndex++;
        }

        Iterator<HumanPlayer> it = this.humanPlayers.iterator();
        if (it.hasNext()) {
            setCurrentPlayer(it.next());
        }
    }

    public void addHumanPlayer(HumanPlayer newHumanPlayer) throws PlayerNameAlreadyExistFormulaDException {
        if (getPlayer(newHumanPlayer.getPlayerName()) != null) {
            throw new PlayerNameAlreadyExistFormulaDException("Player name : " + newHumanPlayer.getPlayerName() + " already exist", this.game);
        } else {
            this.humanPlayers.add(newHumanPlayer);
        }
    }

    public void addComputerPlayer(ComputerPlayer newComputerPlayer) {
        if (getPlayer(newComputerPlayer.getPlayerName()) != null) {
            throw new PlayerNameAlreadyExistFormulaDException("Player name : " + newComputerPlayer.getPlayerName() + " already exist", this.game);
        } else {
            this.computerPlayers.add(newComputerPlayer);
        }
    }

    public boolean removeHumanPlayer(HumanPlayer toRemovePlayer) {
        return this.humanPlayers.remove(toRemovePlayer);
    }

    public boolean removeComputerPlayer(ComputerPlayer toRemovePlayer) {
        return this.computerPlayers.remove(toRemovePlayer);
    }

    public boolean removePlayer(Player player) {
        return this.players.remove(player);
    }

    /*
     * GENERATE GETTERS
     */
    public Collection<Player> getWinPlayers() {
        return winPlayers;
    }

    public Collection<Player> getLoosePlayers() {
        return loosePlayers;
    }

    public static int getDefaultNumberOfHumanPlayer() {
        return defaultNumberOfHumanPlayer;
    }

    public static int getDefaultNumberOfComputerPlayer() {
        return defaultNumberOfComputerPlayer;
    }

    public static AILevels getDefaultAILevel() {
        return defaultAILevel;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Game getGame() {
        return game;
    }

    public String getToken() {
        return token;
    }

    public Iterator<Player> getCurrentPlayerIt() {
        return currentPlayerIt;
    }

    public boolean isCurrentPlayerSpeedIsChoosed() {
        return currentPlayerSpeedIsChoosed;
    }

    /*
     * GENERATE SETTERS
     */
    public static void setDefaultNumberOfHumanPlayer(int defaultNumberOfHumanPlayer) {
        PlayersManager.defaultNumberOfHumanPlayer = defaultNumberOfHumanPlayer;
    }

    public static void setDefaultNumberOfComputerPlayer(int defaultNumberOfComputerPlayer) {
        PlayersManager.defaultNumberOfComputerPlayer = defaultNumberOfComputerPlayer;
    }

    public static void setDefaultAILevel(AILevels defaultAILevel) {
        PlayersManager.defaultAILevel = defaultAILevel;
    }

    public void setHumanPlayers(Collection<HumanPlayer> humanPlayers) {
        this.humanPlayers = humanPlayers;
    }

    public void setComputerPlayers(Collection<ComputerPlayer> computerPlayers) {
        this.computerPlayers = computerPlayers;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCurrentPlayerIt(Iterator<Player> currentPlayerIt) {
        this.currentPlayerIt = currentPlayerIt;
    }

    /*
     * 
     */
    public int getNnumberOfPlayers() {
        return (this.computerPlayers.size() + this.humanPlayers.size());
    }

    public boolean getcurrentPlayerSpeedIsChoosed() {
        return this.currentPlayerSpeedIsChoosed;
    }

    public void setCurrentPlayerSpeedIsChoosed(boolean isChoosed) {
        SnapShot lastSnapShot;
        this.currentPlayerSpeedIsChoosed = isChoosed;
        lastSnapShot = this.currentPlayer.getTraceback().getLastSnapShot();
        lastSnapShot.setSpeed(this.currentPlayer.getCar().getSpeed());
        this.eventBus.post(new CurrentPlayerCarSpeedChanged(this, currentPlayer));
    }

    public void setCurrentPlayer(Player _currentPlayer) {
        this.currentPlayer = _currentPlayer;
        this.eventBus.post(new CurrentPlayerChangedEvent(currentPlayer));
        this.token = TokenGenerator.getNextToken();
        this.currentPlayer.setToken(this.token);
        /*
         * La fonction est utilisée pendant la configuration d'une partie 
         * on n'ajoute donc un snapShot que si le jeu est lancé
         */
        if (this.game.getApplicationState() == ApplicationState.Game) {
            this.currentPlayer.getTraceback().setNewSnapShot(new SnapShot());
        }
        this.eventBus.post(new YourTurnEvent(token));
    }

    public void playerHasFinished(Player winPlayer) {
        this.winPlayers.add(winPlayer);
        winPlayer.setHasWin(true);
        this.eventBus.post(new PlayerWonEvent(winPlayer));
    }

    public Collection<Player> getUnsortPlayers() {
        LinkedList<Player> players = new LinkedList<>();
        players.addAll(this.computerPlayers);
        players.addAll(this.humanPlayers);
        return players;
    }

    public Player getSelectedPlayer() {
        return this.selectedPlayer;
    }

    public Collection<Player> getPlayers() {
        return this.players;
    }

    public Collection<ComputerPlayer> getComputerPlayers() {
        return this.computerPlayers;
    }

    public Collection<HumanPlayer> getHumanPlayers() {
        return this.humanPlayers;
    }

    /*
     * Return null if Player has not found
     */
    public Player getPlayer(String name) {
        boolean found = false;
        Player currentPlayer = null;
        Collection<Player> players = getUnsortPlayers();
        Iterator<Player> it = players.iterator();
        while (!found && it.hasNext()) {
            currentPlayer = it.next();
            if (currentPlayer.getPlayerName().equals(name)) {
                found = true;
            }
        }
        if (found) {
            return currentPlayer;
        } else {
            return null;
        }
    }

    public void setNumberOfPlayers(int numberOfPlayer) {
        this.computerPlayers.clear();
        this.humanPlayers.clear();

        Car[] cars = Car.values();
        for (int i = 0; i < cars.length; i++) {
            cars[i].setAvailable(true);
        }
        int carIndex = 0;

        /*
         * On définit au moins un joueur humain 
         */
        this.humanPlayers.add(new HumanPlayer("HumanPlayer0", cars[carIndex], carIndex, this.eventBus, this.game));
        carIndex++;
        numberOfPlayer--;

        /*
         * Le reste des joueurs est définit en compuerPlayers et première niveau de difficulté
         */
        for (int i = 0; i < numberOfPlayer; i++) {
            this.computerPlayers.add(new ComputerPlayer("ComputerPlayer" + i, cars[carIndex], carIndex, defaultAILevel, this.eventBus, this.game));
            carIndex++;
        }

        this.game.getEventBusHandler().getEventBus().post(new NumberOfPlayersChanged(this));

        Iterator<HumanPlayer> it = this.humanPlayers.iterator();
        if (it.hasNext()) {
            setCurrentPlayer(it.next());
        }

    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setComputerModePlayer(Player currentPlayer) {
        if (currentPlayer instanceof HumanPlayer) {
            ComputerPlayer newPlayer = new ComputerPlayer(currentPlayer.getPlayerName(), currentPlayer.getCar(), currentPlayer.getClassementPosition(), ComputerPlayer.defaultLevel, this.game.getEventBusHandler().getEventBus(), this.game);
            this.humanPlayers.remove(currentPlayer);
            this.computerPlayers.add(newPlayer);
            setCurrentPlayer(newPlayer);
            this.game.getEventBusHandler().getEventBus().post(new CurrentPlayerModeChangedEvent(this, this.currentPlayer));
        }
    }

    public void setHumanModePlayer(Player currentPlayer) {
        if (currentPlayer instanceof ComputerPlayer) {
            HumanPlayer newPlayer = new HumanPlayer(currentPlayer.getPlayerName(), currentPlayer.getCar(), currentPlayer.getClassementPosition(), this.game.getEventBusHandler().getEventBus(), this.game);
            this.computerPlayers.remove(currentPlayer);
            this.humanPlayers.add(newPlayer);
            setCurrentPlayer(newPlayer);
            this.game.getEventBusHandler().getEventBus().post(new CurrentPlayerModeChangedEvent(this, this.currentPlayer));
        }

    }

    public void setSelectedPlayer(Player player) {
        Player oldSelectedPlayer = this.selectedPlayer;
        this.selectedPlayer = player;
        this.game.getEventBusHandler().getEventBus().post(new SelectedPlayerChangedEvent(this, oldSelectedPlayer));
    }

    public void setPlayers(Collection<Player> players) {
        this.players = players;
        setCurrentPlayer(getNextCurrentPlayer());
    }

    /*
     * Fonction relatives au jeu
     */
    public Player getNextCurrentPlayer() {
        if (currentPlayerIt == null) {
            this.currentPlayerIt = players.iterator();
            return this.currentPlayerIt.next();
        } else {
            if (this.currentPlayerIt.hasNext()) {
                return this.currentPlayerIt.next();
            } else {
                Collections.sort((List) players, new ComparePosition());
                int classementPosition = 1;
                for (Player currentPlayer : players) {
                    currentPlayer.setClassementPosition(classementPosition);
                    classementPosition++;
                }
                this.currentPlayerIt = players.iterator();
                this.game.getEventBusHandler().getEventBus().post(new PlayersSortedEvent(this));
                return this.currentPlayerIt.next();
            }
        }
    }

    public boolean isPlayersEmpty() {
        boolean toReturn = true;
        for (Player currentPlayer : players) {
            if(!currentPlayer.isEliminate() && !currentPlayer.isHasWin()){
                toReturn = false;
            }
        }
        return toReturn;
    }

    public String toString() {
        String toReturn = "";
        int position = 1;
        toReturn += "\nQualifications results :\n";
        for (Player currentPlayer : this.players) {
            toReturn += "\n\n" + position + ".\t" + currentPlayer.playerName;
            position++;
        }
        toReturn += "\n\n\n";
        return toReturn;
    }

    public void playerWin(Player player) {
        this.winPlayers.add(player);
        this.eventBus.post(new PlayerWonEvent(player));
        if (this.isPlayersEmpty()) {
            this.game.end();
        }
        
    }

    public void playerEliminated(Player player, String cause) {
        this.game.getPlayersManager().getLoosePlayers().add(player);
        this.eventBus.post(new PlayerIsEliminateEvent(player, cause));
        if (this.isPlayersEmpty()) {
            this.game.end();
        }
    }
}
