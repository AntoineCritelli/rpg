package formation.rpg.fx;

import formation.rpg.action.Action;
import formation.rpg.action.Move;
import formation.rpg.item.equipable.arme.Arme;
import formation.rpg.item.equipable.armure.Armure;
import formation.rpg.personnage.Personnage;
import formation.rpg.world.Room;
import formation.rpg.world.World;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.controlsfx.control.spreadsheet.Grid;

public class MainController {
    private World world;

    @FXML
    private Text roomText;
    @FXML
    private Button startButton;
    @FXML
    private Button northButton;
    @FXML
    private Button eastButton;
    @FXML
    private Button southButton;
    @FXML
    private Button westButton;
    @FXML
    public GridPane gridD;

    public int direction;
    private Move moveAction;

    @FXML
    protected void onGoNorth() {
        System.out.println("north");
        this.direction = 0;
        moveAction.dooInterface(this.world, this);
        this.afficherRoom();
        this.actions();
    }

    @FXML
    protected void onGoEast() {
        System.out.println("east");
        this.direction = 1;
        moveAction.dooInterface(this.world, this);
        this.afficherRoom();
        this.actions();
    }

    @FXML
    protected void onGoSouth() {
        System.out.println("south");
        this.direction = 2;
        moveAction.dooInterface(this.world, this);
        this.afficherRoom();
        this.actions();
    }

    @FXML
    protected void onGoWest() {
        System.out.println("west");
        this.direction = 3;
        moveAction.dooInterface(this.world, this);
        this.afficherRoom();
        this.actions();
    }

    @FXML
    protected void startTheGame() {
        Personnage joueur = new Personnage("moi", 100, 10, new Arme("Poing", 100, 0.01f), new Armure("rien", 0));
        this.world = new World(joueur);
        afficherRoom();

        northButton.setVisible(true);
        eastButton.setVisible(true);
        southButton.setVisible(true);
        westButton.setVisible(true);
        this.actions();
        startButton.setVisible(false);
    }

    public void afficherRoom() {
        roomText.setText(this.world.getPlayer().toString() + '\n' + '\n' + this.world.getPlayerRoom().toString());

        northButton.setDisable(false);
        eastButton.setDisable(false);
        southButton.setDisable(false);
        westButton.setDisable(false);

        Room[] neighbors = world.getNeighbors(world.getPlayerRoom());

        for (int i=0;i<neighbors.length;i++) {
            if (world.getPlayerRoom().getEnemy() != null || neighbors[i] == null)
                switch (i) {
                    case 0 -> northButton.setDisable(true);
                    case 1 -> eastButton.setDisable(true);
                    case 2 -> southButton.setDisable(true);
                    case 3 -> westButton.setDisable(true);
                }
        }
    }

    protected void actions() {
        int index = 0;
        Action[] actions = this.world.getPlayerRoom().possibleActions();
        for (Action action : actions) {
            if (action instanceof Move) {
                this.moveAction = (Move) action;
                continue;
            }


            System.out.print(index++);
            System.out.println(" : " + action.toString());

            Button actionButton = new Button(action.toString());
            actionButton.setOnMouseClicked(new CustomHandlerEvent(action, this.world, this));
            gridD.add(actionButton, index, 4);

        }
    }


    private static class CustomHandlerEvent implements EventHandler<MouseEvent> {

        Action action;
        World world;
        MainController controller;

        public CustomHandlerEvent(Action action, World world, MainController controller) {
            this.action = action;
            this.world = world;
            this.controller = controller;
        }

        @Override
        public void handle(MouseEvent mouseEvent) {
            this.action.dooInterface(this.world, this.controller);
            controller.afficherRoom();
            controller.actions();
        }
    }
}