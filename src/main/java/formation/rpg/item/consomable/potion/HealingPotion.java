package formation.rpg.item.consomable.potion;

import formation.rpg.exception.NoSuchItemInventoryException;
import formation.rpg.item.Item;
import formation.rpg.item.consomable.IConsomable;
import formation.rpg.personnage.Personnage;

/**
 * classe representant une potion de soing
 */
public class HealingPotion extends Item implements IConsomable{

    public HealingPotion() {
        super("Potion de soin");
    }

    @Override
    public void consume(Personnage player) throws NoSuchItemInventoryException {
        player.getInventaire().removeItem(this);
        System.out.println("Vous avez récuperé 10 HP");
        player.addVie(10);
    }

    /**
     * representation textuelle de l'item
     */
    @Override
    public String toString() {
        return "Potion de soin";
    }
    
}
