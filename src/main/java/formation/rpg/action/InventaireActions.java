package formation.rpg.action;


import formation.rpg.exception.InventoryFullException;
import formation.rpg.exception.NoItemAtThisIndexException;
import formation.rpg.exception.NoSuchItemInventoryException;
import formation.rpg.fx.MainController;
import formation.rpg.item.Item;
import formation.rpg.item.consomable.IConsomable;
import formation.rpg.item.equipable.IEquipable;
import formation.rpg.util.Inputs;
import formation.rpg.world.World;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class InventaireActions implements Action  {

    /**
     * affiche l'inventaire et propose d'utilisé les equipements
     */
    @Override
    public void doo(World world) {
        System.out.println(world.getPlayer().getInventaire().toString());

        int choix = Inputs.getInt("-1 : retour, numéro de l'item pour l'utilisé");
        if (choix == -1)
            return ;


        try {

            Item chosenItem = world.getPlayer().getInventaire().getItemAt(choix);
            System.out.println("Vous venez d'utilisé : " + chosenItem.toString());

            if (chosenItem instanceof IConsomable)
                ((IConsomable) chosenItem).consume(world.getPlayer());
            else if (chosenItem instanceof IEquipable)
                ((IEquipable) chosenItem).equip(world.getPlayer());

        } catch (NoItemAtThisIndexException e) {
            System.out.println("Il n'y a pas d'item dans cette case");
        } catch (NoSuchItemInventoryException e) {
            System.out.println("L'item n'est pas dans l'inventaire");
        } catch (InventoryFullException e) {
            System.out.println("Vous n'avez plus de place dans votre inventaire");
        }
    }

    /**
     * renvoie le descriptif de l'action
     */
    public String toString() {
        return "Afficher inventaire";
    }

    @Override
    public void dooInterface(World world, MainController controller) {
        GridPane newGrid = new GridPane();
        newGrid.setHgap(100);

        int index = 0;
        Button button = new Button("retour");
        button.setOnMouseClicked(new CustomHandlerEvent(new CancelItem("retour"), world, controller, button));
        newGrid.add(button, index++, 1);

        for (Item item : world.getPlayer().getInventaire().toList()) {
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
            try {

                if (this.item instanceof IConsomable)
                    ((IConsomable) this.item).consume(world.getPlayer());
                else if (this.item instanceof IEquipable)
                    ((IEquipable) this.item).equip(world.getPlayer());
                else if (this.item instanceof CancelItem)
                    ((CancelItem) this.item).retour(this.button);

            } catch (NoSuchItemInventoryException e) {
                System.out.println("L'item n'est pas dans l'inventaire");
            } catch (InventoryFullException e) {
                System.out.println("Vous n'avez plus de place dans votre inventaire");
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
