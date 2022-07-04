package formation.rpg.personnage;

import java.util.Arrays;
import java.util.List;

import formation.rpg.exception.InventoryFullException;
import formation.rpg.exception.NoItemAtThisIndexException;
import formation.rpg.exception.NoSuchItemInventoryException;
import formation.rpg.item.Item;

/**
 * classe représentant un inventaire
 */
public class Inventaire {
    public Item[] inventaire;
    public int indexFirstBlankStop;

    /**
     * instancie un nouvel inventaire
     */
    public Inventaire() {
        this.indexFirstBlankStop = 0;
        this.inventaire = new Item[10];
    }

    /**
     * renvoie l'item de l'inventaire a la case index
     * @param index le numero de la case
     * @return l'item a la case index
     * @throws NoItemAtThisIndexException s'il n'y a pas d'item dans la case index
     */
    public Item getItemAt(int index) throws NoItemAtThisIndexException{
        if (this.inventaire[index] != null)
            return this.inventaire[index];
        throw new NoItemAtThisIndexException();
    }

    /**
     * ajoute un item dans la première case disponible de l'inventaire
     * @param item l'item a ajouté
     * @throws InventoryFullException si l'inventaire est rempli
     */
    public void addItem(Item item) throws InventoryFullException{
        if (this.indexFirstBlankStop < this.inventaire.length) {
            this.inventaire[indexFirstBlankStop++] = item;
            return ;
        }
        throw new InventoryFullException();
    }

    /**
     * enlève l'item de l'inventaire
     * @param item l'item à enlever
     * @throws NoSuchItemInventoryException si l'item n'est pas dans l'inventaire
     */
    public void removeItem(Item item) throws NoSuchItemInventoryException{
        for (int i=0;i<this.inventaire.length;i++) {
            if (this.inventaire[i] == item) {
                this.inventaire[i] = null;
                this.indexFirstBlankStop = i;
                return ;
            }
        }
        throw new NoSuchItemInventoryException();
    }

    @Override
    public String toString() {
        if (this.inventaire.length == 0)
            return "Votre inventaire est vide";
            
        StringBuilder output = new StringBuilder(" Inventaire :\n");
        for (int i=0;i<this.inventaire.length;i++) {
            if (this.inventaire[i] != null)
                output.append(i).append(" : ").append(this.inventaire[i].toString()).append("\n");
        }
        return output.toString();
    }

    /**
     * retourle le contenue de l'inventaire sous la forme d'une liste
     * @return l'inventaire sous forme de liste
     */
    public List<Item> toList() {
        return Arrays.asList(this.inventaire);
    }
}
