package formation.rpg.action;

import formation.rpg.fx.MainController;
import formation.rpg.util.Inputs;
import formation.rpg.world.Room;
import formation.rpg.world.World;

public class Move implements Action {

    /**
     * permet de changer de salle en fonction des possibilitées
     */
    @Override
    public void doo(World world) {
        String roomChoice = "";
        Room[] neighbors = world.getNeighbors(world.getPlayerRoom());
        for (int i=0;i<neighbors.length;i++) {
            if (neighbors[i] != null)
                switch (i) {
                    case 0:
                        roomChoice += "Nord : 0 ";
                        break;
                    case 1:
                        roomChoice += "EST : 1 ";
                        break;
                    case 2:
                        roomChoice += "SUD : 2 ";
                        break;
                    case 3:
                        roomChoice += "OUEST : 3 ";
                        break;
                }
        }
        System.out.println(roomChoice);
        int choix;
        do {
            choix = Inputs.getInt();
        } while (neighbors[choix] == null);
        world.movePlayerTo(choix);
    }

    /**
     * renvoie le descriptif de l'action
     */
    public String toString() {
        return "Changer de salle";
    }

    @Override
    public void dooInterface(World world, MainController controller) {
        world.movePlayerTo(controller.direction);
    }
}
