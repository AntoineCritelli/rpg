package formation.rpg.world;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import formation.rpg.action.Action;
import formation.rpg.action.Attack;
import formation.rpg.action.InventaireActions;
import formation.rpg.action.Move;
import formation.rpg.action.PickUp;
import formation.rpg.item.Item;
import formation.rpg.item.consomable.potion.HealingPotion;
import formation.rpg.item.equipable.arme.Arme;
import formation.rpg.item.equipable.armure.Armure;
import formation.rpg.personnage.Inventaire;
import formation.rpg.personnage.Personnage;
import formation.rpg.util.Vector2;

/**
 * classe représentant une piéce du monde
 */
public class Room {
    
    //#region attribut
    protected LinkedList<Item> items;
    protected Personnage[] personnages = new Personnage[2];
    protected float spawnItem = 0.5f;
    protected float spawnEnemy = 0.3f;
    protected Vector2 position;
    //#endregion

    /**
     * instancie une nouvelle piece
     * @param position la position de la piéce
     * @param start flag debut de jeu
     */
    public Room(Vector2 position, boolean start) {
        this.position = position;
        this.items = new LinkedList<>();

        if (start) {
            this.personnages[1] = new Personnage("Enemy", 50, 10, new Arme("poing", 1, 0.01f), new Armure("rien", 0));
            return ;
        }
        

        if (Math.random() < spawnEnemy)
            this.personnages[1] = new Personnage("Enemy", 50, 10, new Arme("poing", 1, 0.01f), new Armure("rien", 0));
        if (Math.random() < spawnItem)
            if (Math.random() < 0.5f)
                this.items.add(new Arme("epée", 5, 0.3f));
            else   
                this.items.add(new HealingPotion());
    }

    /**
     * instancie une nouvelle piéce
     * @param position la position de la piéce
     */
    public Room(Vector2 position) {
        this(position, false);
    }

    /**
     * liste les actions possible dans la piéce
     * @return un tableau d'Action possible
     */
    public Action[] possibleActions() {
        List<Action> actions = new ArrayList<>();

        if (this.personnages[1] != null) {
            actions.add(new Attack());
            actions.add(new InventaireActions());
        } else {
            // passif mode
            actions.add(new Move());
            actions.add(new InventaireActions());

            if (this.items.size() > 0)
                actions.add(new PickUp());
        }

        return actions.toArray(new Action[0]);
    }

    /**
     * permet de mettre le joueur dans la piéce
     * @param joueur le joueur entrant
     */
    public void in(Personnage joueur) {
        this.personnages[0] = joueur;
    }

    /**
     * permet d'enlevé un joueur de la piéce
     * @return le joueur à enlevé
     */
    public Personnage out() {
        Personnage joueur = this.personnages[0];
        this.personnages[0] = null;
        return joueur;
    }

    /**
     * retourne une représentation textuelle de la piéce
     */
    public String toString() {
        String output = "";
        output += "Votre position : " + this.position.toString() + "\n";
        output += (this.items.size() > 0)?this.itemsToString()+"\n":"Il n'y a pas d'item au sol\n";
        output += (this.personnages[1] != null)?this.personnages[1]+"\n":"Il n'y a pas d'enemy dans la salle\n";
        return output;
    }

    /**
     * renvoie une description textuelle des items au sol
     * @return dectiption textuelle des items au sol
     */
    public String itemsToString() {
        StringBuilder output = new StringBuilder();
        for (int i=0;i<this.items.size();i++)
            if (this.items.get(i) != null)
                output.append(i).append(" : ").append(this.items.get(i).toString()).append("\n");
        return output.toString();
    }


    //#region getter/setter

    /**
     * returne l'enemy de la piéce
     * @return un enemy
     */
    public Personnage getEnemy() {
        return this.personnages[1];
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addItem(Inventaire inventaire) {
        this.items.addAll(inventaire.toList());
    }

    public List<Item> getItem() {
        return this.items;
    }

    public Personnage[] getPersonnages() {
        return this.personnages;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }
    //#endregion
}
