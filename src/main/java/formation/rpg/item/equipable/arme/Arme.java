package formation.rpg.item.equipable.arme;

import formation.rpg.exception.InventoryFullException;
import formation.rpg.exception.NoSuchItemInventoryException;
import formation.rpg.item.Item;
import formation.rpg.item.equipable.IEquipable;
import formation.rpg.personnage.Personnage;

/**
 * classe representant une arme
 */
public class Arme extends Item implements IEquipable{

    //#region attributs
    // degat de l'item
    protected int degat;
    // chance de coûts critique de l'item
    protected float cc;
    //#endregion

    /**
     * instancie un nouvel item
     * @param nom le nom de l'item
     * @param degat les degats de l'item
     * @param cc les chance de coûts critique de l'item
     */
    public Arme(String nom, int degat, float cc) {
        super(nom);
        this.setDegat(degat);
        this.setCc(cc);
    }

    //#region getter/setter

    public int getDegat() {
        return degat;
    }

    public float getCc() {
        return cc;
    }

    private void setDegat(int degat) {
        this.degat = degat;
    }


    private void setCc(float cc) {
        this.cc = cc;
    }
    //#endregion


    /**
     * représentation textuelle de l'arme
     */
    @Override
    public String toString() {
        return super.getName() + "(dégat:" + this.getDegat() + ",cc:" + this.getCc() + ")";
    }

    /**
     * permet d'equipé l'arme sur le joueur
     */
    @Override
    public void equip(Personnage target) throws InventoryFullException, NoSuchItemInventoryException{
        if (target.getEquippedWeapon() != null) 
            target.getInventaire().addItem(target.getEquippedWeapon());
        target.setEquippedWeapon(this);
        target.getInventaire().removeItem(this);
    }

    @Override
    public void unequip(Personnage target) {
        // TODO Auto-generated method stub
        
    }
    
    
}
