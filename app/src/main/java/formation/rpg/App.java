package formation.rpg;

import formation.rpg.action.Action;
import formation.rpg.item.equipable.arme.*;
import formation.rpg.item.equipable.armure.*;
import formation.rpg.personnage.*;
import formation.rpg.util.*;
import formation.rpg.world.*;

public class App {
    
    public static void main(String[] args) {
        String name = Inputs.getString("Votre nom : ");
        System.out.println("Bonjour " + name);
        System.out.println("Debut de l'aventure --------------------------------------------------------");
        Personnage joueur = new Personnage(name, 100, 10, new Arme("Poing", 100, 0.01f), new Armure("rien", 0));


        World world = new World(joueur);

        do {
            Room playerRoom = world.getPlayerRoom();

            System.out.println(world.getPlayer().toString());
            System.out.println(playerRoom.toString());

            int index = 0;
            Action[] actions = playerRoom.possibleActions();
            for (Action action : actions) {
                System.out.print(index++);
                System.out.println(" : " + action.toString());
            }
            actions[Inputs.getInt()].doo(world);
            System.out.println("-----------------------------------------------");
        } while (world.getPlayer().getVie() > 0);
    }
    
}
