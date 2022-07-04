package formation.rpg.action;

import formation.rpg.exception.InventoryFullException;
import formation.rpg.exception.NoSuchItemInventoryException;
import formation.rpg.fx.MainController;
import formation.rpg.item.Item;
import formation.rpg.item.consomable.IConsomable;
import formation.rpg.item.equipable.IEquipable;
import formation.rpg.personnage.Personnage;
import formation.rpg.util.Inputs;
import formation.rpg.world.Room;
import formation.rpg.world.World;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class PickUp implements Action{

    /**
     * permet de ramaser l'item d'une salle et le mettre dans l'inventaire du joueur
     */
    @Override
    public void doo(World world) {

        try {
            Room room = world.getPlayerRoom();
            Personnage player = world.getPlayer();

            // afficher mieux les items
            System.out.println(room.itemsToString());
            // demandeer la selection de l'item
            int choix = Inputs.getInt("selectionnez un item à ramasser");
            // recuperer l'item et le metre dans l'inventaire
            Item chosenItem = room.getItem().get(choix);
            player.getInventaire().addItem(chosenItem);
            room.removeItem(chosenItem);
        } catch (InventoryFullException e){
            System.out.println("L'inventaire est plein !");
        }
    }
    
    /**
     * renvoie le descriptif de l'action
     */
    public String toString() {
        return "prendre";
    }

    @Override
    public void dooInterface(World world, MainController controller) {

        Room room = world.getPlayerRoom();
        Personnage player = world.getPlayer();
        GridPane newGrid = new GridPane();
        newGrid.setHgap(100);
        List<Item> items = new ArrayList<>();

        // afficher mieux les items
        int index = 0;
        Button button = new Button("retour");
        button.setOnMouseClicked(new CustomHandlerEvent(new CancelItem("retour"), world, controller, button));
        newGrid.add(button, index++, 1);

        for (Item item : room.getItem()) {
            if (item == null)
                continue;
            button = new Button(item.toString());
            button.setOnMouseClicked(new CustomHandlerEvent(item, world, controller, button));
            newGrid.add(button, index++, 1);
        }

        controller.gridD.add(newGrid, 1, 5, 1, index);


    }

    private class CustomHandlerEvent implements EventHandler<MouseEvent> {

        Item item;
        World world;
        MainController controller;
        Button button;

        public CustomHandlerEvent(Item item, World world, MainController controller, Button button) {
            this.item = item;
            this.world = world;
            this.controller = controller;
            this.button = button;
        }

        @Override
        public void handle(MouseEvent mouseEvent) {
            if (this.item instanceof PickUp.CancelItem)
                ((CancelItem) this.item).retour(this.button);

            try {
                world.getPlayer().getInventaire().addItem(this.item);
                world.getPlayerRoom().removeItem(this.item);
                System.out.println("pris");
            } catch (InventoryFullException e){
                System.out.println("L'inventaire est plein !");
            }


            this.button.getParent().setVisible(false);
            controller.afficherRoom();
        }
    }

    private class CancelItem extends Item {

        public CancelItem(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return super.getName();
        }

        public void retour(Button button) {
            button.getParent().setVisible(false);
        }

    }
}
