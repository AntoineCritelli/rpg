package formation.rpg.item.equipable;

import formation.rpg.exception.InventoryFullException;
import formation.rpg.exception.NoSuchItemInventoryException;
import formation.rpg.personnage.Personnage;

public interface IEquipable {
    
    /**
     * equipe l'item sur le personnage
     * @param target le personnage a equiper
     */
    public void equip(Personnage target) throws InventoryFullException, NoSuchItemInventoryException;


    /**
     * desequipe l'item du personnage et le range dans son Iventaire
     * @param target le joueur a desequiper
     */
    public void unequip(Personnage target) throws InventoryFullException;
}
