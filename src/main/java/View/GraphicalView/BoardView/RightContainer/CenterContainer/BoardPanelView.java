/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GraphicalView.BoardView.RightContainer.CenterContainer;

import Events.CarPositionChangedEvent;
import Events.CarStatesChangedEvent;
import Events.ChangedClassementEvent;
import Events.CreateViewEvent;
import Events.CurrentPlayerCarSpeedChanged;
import Events.CurrentPlayerChangedEvent;
import Events.CurrentPlayerDiceValueChangedEvent;
import Events.DivinModeChangedEvent;
import Events.GameOverEvent;
import Events.PlayerFinishedALapEvent;
import Events.PlayerIsEliminateEvent;
import Events.PlayerLoosePointsEvent;
import Events.PlayerWonEvent;
import Events.PlayersSortedEvent;
import Events.RuleInformationEvent;
import Events.ScrapOnCellEvent;
import Events.SelectedBendChangedEvent;
import Events.SelectedPlayerChangedEvent;
import Events.ThrowCurrentPlayerDiceEvent;
import Model.Cars.Car;
import Model.Game;
import Model.Map.Bend;
import Model.Map.BendCell;
import Model.Map.Cell;
import Model.Map.MapElement;
import Model.Players.HumanPlayer;
import Model.Players.Player;
import View.AbstractFormulaDView;
import View.AbstractGameView;
import View.GraphicalView.BoardView.GameOver.GameOverRecapFrame;
import com.google.common.eventbus.Subscribe;
import java.awt.BorderLayout;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.batik.script.Window;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

/**
 *
 * @author wasson
 */
public class BoardPanelView extends JPanel implements AbstractGameView, AbstractFormulaDView {

    JSVGCanvas canvas;
    Document document;
    Window window;
    Game game;
    JPanel panel = this;
    GameOverRecapFrame gameOverFrame;
    /*
     * Game
     */
    private LinkedList<DestinationCellEventManager> listenners = new LinkedList<>();
    private LinkedList<Element> scrapCells = new LinkedList<>();
    private HashMap<Cell, LinkedList<Cell>> currentDestinations;
    private Cell currentCellDestination = null;
    private Element selectedPlayer;
    private Element currentPlayer;
    private boolean isInit = false;
    /*
     * Couleurs définies en static 
     */
    public static String selectedPlayerColor = "#F09F25";
    public static String currentPlayerColor = "#02C31C";
    public static String currentDestinationsColor = "#0564E1";
    public static String currentDestinationsColorHover = "##D1EDF9";
    public static String currentPathColor = "#E97E20";
    public static String roadColor = "#BABABA";
    public static String bendColor = "#656565";
    public static String bendHoverColor = "#808080";
    public static String scrapOnCellColor = "#FEFEFE";

    public BoardPanelView() {
        canvas = new JSVGCanvas();
        canvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
        canvas.addSVGLoadEventDispatcherListener(new SVGLoadEventDispatcherAdapter() {
            @Override
            public void svgLoadEventDispatchStarted(SVGLoadEventDispatcherEvent e) {
                document = canvas.getSVGDocument();
                window = canvas.getUpdateManager().getScriptingEnvironment().createWindow();
                registerListeners();
            }
        });


    }

    @Override
    public void createView(CreateViewEvent ev) {
        canvas.setURI(new File(ev.getSender().getCurrentMap().getFolderPath() + "circuit.svg").toURI().toASCIIString());
        this.setLayout(new BorderLayout());
        this.game = ev.getSender();
        gameOverFrame = new GameOverRecapFrame(game);
        this.add(canvas);
    }

    public void registerListeners() {
        Element elt = document.getElementById("layer1");
        EventTarget t = (EventTarget) elt;

        t.addEventListener("SVGLoad", new OnSVGLoadAction(), false);
    }

    @Override
    @Subscribe
    public void refreshDice(final ThrowCurrentPlayerDiceEvent ev) {
        if (game.getPlayersManager().getCurrentPlayer() instanceof HumanPlayer) {
            currentDestinations = ev.getDestinations();
            this.canvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(new Runnable() {
                @Override
                public void run() {
                    Element elt;
                    EventListener clickListenner;
                    EventListener hoverListenner;
                    EventListener mouseOutListenner;
                    EventTarget t;

                    int i = 0;

                    for (Cell currentCellDestination : currentDestinations.keySet()) {
                        i++;
                        /*
                         * On récupère l'élèment
                         */
                        elt = document.getElementById(currentCellDestination.getComonId());
                        /*
                         * On ajoute les différents écouteurs
                         */
                        hoverListenner = new OnDestCellMouseOverAction(elt, currentCellDestination);
                        mouseOutListenner = new OnDestCellMouseOutAction(elt, currentCellDestination);
                        clickListenner = new OnDestCellClickAction(currentCellDestination);
                        t = (EventTarget) elt;

                        t.addEventListener("mouseover", hoverListenner, false);
                        t.addEventListener("mouseout", mouseOutListenner, false);
                        t.addEventListener("click", clickListenner, false);

                        listenners.add(new DestinationCellEventManager(currentCellDestination, elt, clickListenner, hoverListenner, mouseOutListenner));

                        changeElementColor(elt, currentDestinationsColor);
                    }
                }
            });
        }
    }

    @Override
    @Subscribe
    public void refreshClassement(ChangedClassementEvent ev) {
    }

    @Override
    @Subscribe
    public void refreshCarPosition(final CarPositionChangedEvent ev) {
        this.canvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(new Runnable() {
            @Override
            public void run() {
                Element elt;
                /*
                 * Ancienne position
                 */


                if (ev.getOldPosition() != null) {
                    String hashOldCell = ev.getOldPosition().getComonId();
                    elt = document.getElementById(hashOldCell);
                    if (ev.getOldPosition() instanceof BendCell) {
                        if (ev.getOldPosition().isScrapOnCell()) {
                            changeElementColor(elt, scrapOnCellColor);
                        } else {
                            changeElementColor(elt, bendColor);
                        }

                    } else {
                        if (ev.getOldPosition().isScrapOnCell()) {
                            changeElementColor(elt, scrapOnCellColor);
                        } else {
                            changeElementColor(elt, roadColor);
                        }

                    }

                }



                /*
                 * Nouvelle position
                 */
                if (!ev.getPlayer().isEliminate() && !ev.getPlayer().isHasWin()) {
                    String hashNewCell = ev.getNewPosition().getComonId();
                    elt = document.getElementById(hashNewCell);
                    changeElementColor(elt, ev.getPlayer().getCar().getRgbStringColor());
                }


            }
        });

    }

    @Override
    @Subscribe
    public void refreshCurrentPlayer(CurrentPlayerChangedEvent ev) {
        
    }

    @Override
    @Subscribe
    public void refreshSelectedPlayer(final SelectedPlayerChangedEvent ev) {
        this.canvas.getUpdateManager().getUpdateRunnableQueue().invokeLater(new Runnable() {
            @Override
            public void run() {
                if (selectedPlayer != null && document.getElementById(ev.getOldSelectedPlayer().getCar().getPosition().getComonId())
                        == selectedPlayer
                        && !ev.getOldSelectedPlayer().isEliminate() && !ev.getOldSelectedPlayer().isHasWin()) {
                    /*
                     * On remet l'ancien joueur sélectionné à sa couleur d'origine
                     */
                    changeElementColor(selectedPlayer, ev.getOldSelectedPlayer().getCar().getRgbStringColor());
                }
                /*
                 * On colore le nouveau joueur sélectionné
                 */
                selectedPlayer = document.getElementById(ev.getSender().getSelectedPlayer().getCar().getPosition().getComonId());
                changeElementColor(selectedPlayer, selectedPlayerColor);
                canvas.repaint();
            }
        });

    }

    @Override
    @Subscribe
    public void refreshCurrentPlayerDiceValue(CurrentPlayerDiceValueChangedEvent ev) {
    }

    @Override
    @Subscribe
    public void refreshCurrentPlayerCarSpeed(CurrentPlayerCarSpeedChanged ev) {
    }

    @Override
    public void refreshCarStates(CarStatesChangedEvent ev) {
    }

    public void changeElementColor(Element elt, String color) {
        String style = elt.getAttribute("style");
        style = style.replaceFirst("\\Afill:(.*?);", "fill:" + color + ";");
        elt.setAttribute("style", style);
    }

    private void clearAllCurrentDestination() {
        Element elt;
        for (Cell cell : currentDestinations.keySet()) {
            elt = document.getElementById(cell.getComonId());
            if (cell instanceof BendCell) {
                if (cell.isScrapOnCell()) {
                    changeElementColor(elt, scrapOnCellColor);
                } else {
                    changeElementColor(elt, bendColor);
                }

            } else {
                if (cell.isScrapOnCell()) {
                    changeElementColor(elt, scrapOnCellColor);
                } else {
                    changeElementColor(elt, roadColor);
                }

            }


        }
    }

    private void colorBend(Bend bend, String bendRgbStringColor) {
        Element elt;
        Cell[] leftWay, rightWay, midWay;
        leftWay = bend.getLeftWay();
        rightWay = bend.getRightWay();
        midWay = bend.getMidWay();

        colorWay(leftWay, bendRgbStringColor);
        colorWay(midWay, bendRgbStringColor);
        colorWay(rightWay, bendRgbStringColor);

        if (!isInit) {
            initBendListenners(bend);
        }
    }

    private void initBendListenners(Bend bend) {
        initBendWayListenners(bend.getLeftWay(), bend);
        initBendWayListenners(bend.getMidWay(), bend);
        initBendWayListenners(bend.getRightWay(), bend);
    }

    private void initBendWayListenners(Cell[] way, Bend bend) {
        EventTarget t;
        for (int i = 0; i < way.length; i++) {
            t = (EventTarget) document.getElementById(way[i].getComonId());
            t.addEventListener("click", new OnBendClickAction(bend), false);
            t.addEventListener("mouseover", new OnBendHoverAction(bend, way[i]), false);
            t.addEventListener("mouseout", new OnBendOutAction(bend, way[i]), false);
        }
    }

    private void colorWay(Cell[] way, String bendRgbStringColor) {
        Element elt;
        for (int i = 0; i < way.length; i++) {
            elt = document.getElementById(way[i].getComonId());
            if (way[i].isScrapOnCell()) {
                changeElementColor(elt, scrapOnCellColor);
            } else {
                changeElementColor(elt, bendRgbStringColor);
            }

        }
    }

    private void colorPlayersCell() {
        Element elt;
        Collection<Player> players = game.getPlayersManager().getPlayers();
        for (Player player : players) {
            if (!player.isEliminate() && !player.isHasWin()) {
                elt = document.getElementById(player.getCar().getPosition().getComonId());
                changeElementColor(elt, player.getCar().getRgbStringColor());
            }

        }
    }

    private void colorCurrentDestinations() {
        Element elt;
        for (Cell cell : currentDestinations.keySet()) {
            elt = document.getElementById(cell.getComonId());
            changeElementColor(elt, currentDestinationsColor);
        }
    }

    @Override
    public void refreshSelectedBend(SelectedBendChangedEvent ev) {
    }

    @Override
    public void refreshSortedPlayer(PlayersSortedEvent ev) {
    }

    @Override
    @Subscribe
    public void refreshPlayerIsEliminate(PlayerIsEliminateEvent ev) {
        Element elt;
        Cell cell;
        cell = ev.getPlayer().getCar().getPosition();
        elt = document.getElementById(cell.getComonId());
        if (cell instanceof BendCell) {
            if (cell.isScrapOnCell()) {
                changeElementColor(elt, scrapOnCellColor);
            } else {
                changeElementColor(elt, bendColor);
            }

        } else {
            if (cell.isScrapOnCell()) {
                changeElementColor(elt, scrapOnCellColor);
            } else {
                changeElementColor(elt, roadColor);
            }
        }
        JOptionPane.showMessageDialog(this,
                ev.toString(), "Elimination", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    @Subscribe
    public void refreshRuleInformation(RuleInformationEvent ev) {
        JOptionPane.showMessageDialog(this,
                ev.getMessage());
    }

    @Override
    @Subscribe
    public void refreshPlayerLoosePoint(PlayerLoosePointsEvent ev) {
        JOptionPane.showMessageDialog(this,
                ev.getMessage());
    }

    @Override
    @Subscribe
    public void refreshPlayerFinishedLap(PlayerFinishedALapEvent ev) {
        JOptionPane.showMessageDialog(this,
                "" + ev.getPlayer().getPlayerName() + " has finished his lap number : " + (ev.getLap() - 1));
    }

    @Override
    @Subscribe
    public void refreshPlayerWon(PlayerWonEvent ev) {
        Element elt;
        Cell cell;
        cell = ev.getPlayer().getCar().getPosition();
        elt = document.getElementById(cell.getComonId());
        if (cell instanceof BendCell) {
            if (cell.isScrapOnCell()) {
                changeElementColor(elt, scrapOnCellColor);
            } else {
                changeElementColor(elt, bendColor);
            }

        } else {
            if (cell.isScrapOnCell()) {
                changeElementColor(elt, scrapOnCellColor);
            } else {
                changeElementColor(elt, roadColor);
            }

        }
        JOptionPane.showMessageDialog(this,
                "" + ev.getPlayer().getPlayerName() + " was finished his course ! Felicitations !");
    }

    @Override
    public void refreshDivinMode(DivinModeChangedEvent ev) {
    }

    @Override
    @Subscribe
    public void refreshGameOver(GameOverEvent ev) {
        gameOverFrame.refresh(ev);
        gameOverFrame.setVisible(true);
    }

    @Override
    @Subscribe
    public void refreshCell(ScrapOnCellEvent ev) {
        if (!ev.getScrapOnCell().isOcupied()) {
            Element elt = document.getElementById(ev.getScrapOnCell().getComonId());
            this.scrapCells.add(elt);
            changeElementColor(elt, scrapOnCellColor);
        }

    }

    private void colorScrapCells() {
        for (Element currentCell : scrapCells) {
            changeElementColor(currentCell, scrapOnCellColor);
        }
    }

    /*
     * Event Listenners
     */
    public class OnSVGLoadAction implements EventListener {

        @Override
        public void handleEvent(Event evt) {
            Element elt;
            NodeList nl;
            Bend currentBend;

            /*
             * On réinitialise la couleur de l'ensemble des cellules
             */

            nl = document.getElementsByTagName("path");
            for (int i = 0; i < nl.getLength(); i++) {
                elt = (Element) nl.item(i);
                changeElementColor(elt, "#BABABA");
            }

            /*
             * On colore les cellules où se trouvent des joueurs
             */

            Collection<Player> players = game.getPlayersManager().getPlayers();
            for (Player currentPlayer : players) {
                elt = document.getElementById(currentPlayer.getCar().getPosition().getComonId());
                changeElementColor(elt, currentPlayer.getCar().getRgbStringColor());
            }

            List<MapElement> elements = game.getCurrentMap().getElements();
            for (MapElement mapElement : elements) {
                if (mapElement instanceof Bend) {
                    currentBend = (Bend) mapElement;
                    colorBend(currentBend, bendColor);
                }
            }
            isInit = true;
            JOptionPane.showMessageDialog(panel, game.getPlayersManager());
        }
    }

    public class OnDestCellClickAction implements EventListener {

        private Cell destination;

        public OnDestCellClickAction(Cell destination) {
            this.destination = destination;
        }

        @Override
        public void handleEvent(Event evt) {
            LinkedList<EventListener> els;
            Element elt;
            EventTarget et;

            /*
             * On decolore les cellules possibles d'arrivée
             */
            clearAllCurrentDestination();

            /*
             * On decolore le chemin survolé
             */
            LinkedList<Cell> path = currentDestinations.get(destination);
            for (Cell cell : path) {
                elt = document.getElementById(cell.getComonId());
                if (cell instanceof BendCell) {
                    if (cell.isScrapOnCell()) {
                        changeElementColor(elt, scrapOnCellColor);
                    } else {
                        changeElementColor(elt, bendColor);
                    }

                } else {
                    if (cell.isScrapOnCell()) {
                        changeElementColor(elt, scrapOnCellColor);
                    } else {
                        changeElementColor(elt, roadColor);
                    }

                }

            }

            /*
             * On remove les écouteurs qu'il y avait sur les différentes cases
             */
            for (DestinationCellEventManager currentLis : listenners) {
                currentLis.removeListenners();
            }
            /*
             * On vide la liste contenant les EventManager
             */
            listenners.removeAll(listenners);

            /*
             * On set la nouvelle position et on appelle la fonction qui notifie que 'l'on a terminé le tour
             */
            game.getPlayersManager().getCurrentPlayer().getCar().setPosition(destination, path);
            currentDestinations = null;
            game.getPlayersManager().getCurrentPlayer().endOfOperations();

        }
    }

    public class OnDestCellMouseOverAction implements EventListener {

        private Element destination;
        private Cell cell;

        public OnDestCellMouseOverAction(Element el, Cell cell) {
            this.destination = el;
            this.cell = cell;
        }

        @Override
        public void handleEvent(Event evt) {
            LinkedList<Cell> path;
            Element elt;
            path = currentDestinations.get(cell);
            currentCellDestination = this.cell;
            for (Cell currentCell : path) {
                elt = document.getElementById(currentCell.getComonId());
                if (currentCell.isScrapOnCell()) {
                    changeElementColor(elt, scrapOnCellColor);
                } else {
                    changeElementColor(elt, currentPathColor);
                }

            }
            changeElementColor(destination, currentDestinationsColorHover);
            Car currentPlayerCar = game.getPlayersManager().getCurrentPlayer().getCar();
            changeElementColor(document.getElementById(currentPlayerCar.getPosition().getComonId()), currentPlayerCar.getRgbStringColor());
        }
    }

    public class OnDestCellMouseOutAction implements EventListener {

        private Element destination;
        private Cell cell;

        public OnDestCellMouseOutAction(Element destination, Cell cell) {
            this.destination = destination;
            this.cell = cell;
        }

        @Override
        public void handleEvent(Event evt) {
            LinkedList<Cell> path;
            Element elt;
            path = currentDestinations.get(cell);
            for (Cell currentCell : path) {
                elt = document.getElementById(currentCell.getComonId());
                if (currentDestinations.containsKey(currentCell)) {
                    changeElementColor(elt, currentDestinationsColor);
                } else {
                    if (currentCell instanceof BendCell) {
                        changeElementColor(elt, bendColor);
                    } else {
                        changeElementColor(elt, roadColor);
                    }

                }

            }
            changeElementColor(destination, currentDestinationsColor);
            Car currentPlayerCar = game.getPlayersManager().getCurrentPlayer().getCar();
            changeElementColor(document.getElementById(currentPlayerCar.getPosition().getComonId()), currentPlayerCar.getRgbStringColor());
        }
    }

    public class OnBendClickAction implements EventListener {

        private Bend bend;

        public OnBendClickAction(Bend bend) {
            this.bend = bend;
        }

        @Override
        public void handleEvent(Event evt) {
            game.getCircuitManager().setSelectedBend(bend);
        }
    }

    public class OnBendHoverAction implements EventListener {

        private Bend bend;
        private Cell cell;

        public OnBendHoverAction(Bend bend, Cell cell) {
            this.bend = bend;
            this.cell = cell;
        }

        @Override
        public void handleEvent(Event evt) {
            if (currentDestinations == null || !currentDestinations.containsKey(cell)) {
                colorBend(bend, bendHoverColor);
                colorPlayersCell();
                colorScrapCells();
                if (currentDestinations != null) {
                    colorCurrentDestinations();
                }
            }

        }
    }

    public class OnBendOutAction implements EventListener {

        private Bend bend;
        private Cell cell;

        public OnBendOutAction(Bend bend, Cell cell) {
            this.bend = bend;
            this.cell = cell;
        }

        @Override
        public void handleEvent(Event evt) {
            if (currentDestinations == null || !currentDestinations.containsKey(cell)) {
                colorBend(bend, bendColor);
                colorPlayersCell();
                colorScrapCells();
                if (currentDestinations != null) {
                    colorCurrentDestinations();
                }
            }

        }
    }
}
