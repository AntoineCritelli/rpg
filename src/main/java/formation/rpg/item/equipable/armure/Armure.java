package formation.rpg.item.equipable.armure;

import formation.rpg.exception.InventoryFullException;
import formation.rpg.exception.NoSuchItemInventoryException;
import formation.rpg.item.Item;
import formation.rpg.item.equipable.IEquipable;
import formation.rpg.personnage.Personnage;

/**
 * classe représentant une armure
 */
public class Armure extends Item implements IEquipable{

    //#region attributs
    // point de defence
    protected int defence;
    //#endregion

    /**
     * intiancie une armure
     * @param nom nom de l'armure
     * @param defence nombre de point de defence
     */
    public Armure(String nom, int defence) {
        super(nom);
        this.setDefence(defence);
    }

    //#region getter/setter

    public int getDefence() {
        return defence;
    }

    private void setDefence(int defence) {
        this.defence = defence;
    }
    //#endregion


    @Override
    public String toString() {
        return this.getName() + "(defence:" + this.getDefence() + ")";
    }


    @Override
    public void equip(Personnage player)  throws InventoryFullException, NoSuchItemInventoryException {
        if (player.getArmor() != null) 
            player.getInventaire().addItem(player.getArmor());
        player.setArmor(this);
        player.getInventaire().removeItem(this);
    }

    @Override
    public void unequip(Personnage target) {
        // TODO Auto-generated method stub
        
    }
    
}
