package formation.rpg.action;


import formation.rpg.fx.MainController;
import formation.rpg.personnage.Personnage;
import formation.rpg.world.World;

/**
 * Action d'attaquer un enemy dans la même salle
 */
public class Attack implements Action {

    /**
     * recupere le joueur et l'enemy de la salle, fait frapper le joueur puis l'enemy
     */
    @Override
    public void doo(World world) {
        Personnage player = world.getPlayer();
        Personnage enemy = world.getPlayerRoom().getEnemy();

        enemy.ceFaireFrapper(player.frapper());

        if (enemy.getVie() <= 0){
            enemy.die(world.getPlayerRoom());
            return ;
        }
        

        player.ceFaireFrapper(enemy.frapper());
    }

    /**
     * renvoie le descriptif de l'action
     */
    public String toString() {
        return "Attaquer";
    }

    @Override
    public void dooInterface(World world, MainController controller) {
        this.doo(world);
    }
}
