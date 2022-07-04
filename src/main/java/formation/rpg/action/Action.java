package formation.rpg.action;


import formation.rpg.fx.MainController;
import formation.rpg.world.World;

/**
 * Interface representant une action possible
 */
public interface Action {

    public void doo(World world);

    public void dooInterface(World world, MainController controller);
}
