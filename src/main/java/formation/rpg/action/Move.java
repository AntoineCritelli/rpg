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
        StringBuilder roomChoice = new StringBuilder();
        Room[] neighbors = world.getNeighbors(world.getPlayerRoom());
        for (int i=0;i<neighbors.length;i++) {
            if (neighbors[i] != null)
                switch (i) {
                    case 0 -> roomChoice.append("Nord : 0 ");
                    case 1 -> roomChoice.append("EST : 1 ");
                    case 2 -> roomChoice.append("SUD : 2 ");
                    case 3 -> roomChoice.append("OUEST : 3 ");
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
