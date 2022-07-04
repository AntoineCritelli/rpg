package formation.rpg.item.consomable;

import formation.rpg.exception.NoSuchItemInventoryException;
import formation.rpg.personnage.Personnage;

public interface IConsomable {
    
    /**
     * consome un objet 
     * @param target le personnage qui consome l'item
     */
    void consume(Personnage target) throws NoSuchItemInventoryException;

}
