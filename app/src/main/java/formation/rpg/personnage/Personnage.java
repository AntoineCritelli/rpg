package formation.rpg.personnage;

import formation.rpg.item.equipable.arme.*;
import formation.rpg.item.equipable.armure.*;
import formation.rpg.world.Room;

/**
 * classe représentant un personnage
 */
public class Personnage {
    //#region attribut
    // nom du personnage
    protected String nom;
    // nombre de point de vie
    protected int vie;
    // nombre de point de force
    protected int force;
    // arme equipé
    protected Arme equippedWeapon;
    // armure equipé
    protected Armure armor;
    // inventaire
    protected Inventaire inventaire;
    //#endregion

    /**
     * instancie un nouveau personnage
     * @param nom nom du personnage
     * @param vie point de vie du personnage
     * @param force point de force du personnage
     * @param weapon arme du personnage
     * @param armor armure du personnage
     */
    public Personnage(String nom, int vie, int force, Arme weapon, Armure armor) {
        this.inventaire = new Inventaire();
        this.setNom(nom);
        this.setVie(vie);
        this.setForce(force);
        this.equippedWeapon = weapon;
        this.armor = armor;
    }

    /**
     * calcule le nombre de point de degat effectuer par le personnage
     * @return le nombre de point de degat
     */
    public int frapper() {
        int degat = this.getEquippedWeapon().getDegat();
        if (Math.random() < this.getEquippedWeapon().getCc()+(this.getForce()/100.0f))
            degat *= 2;
        degat *= 1+this.getForce()/10;

        System.out.println(this.getNom() + " utilise " + this.getEquippedWeapon().getName() + " et tente d'infliger " + degat + " dégâts !");
        return degat;
    }

    /**
     * perd les points de vie correspondant au *degat*
     * @param degat les degat fait au personnage
     */
    public void ceFaireFrapper(int degat) {
        degat *= 1-((float)this.getArmor().getDefence()/100);

        this.setVie(this.getVie()-degat);
        System.out.println(this.getNom() + " reçoit " + degat + " ! Il lui reste " + this.getVie() + " points de vie !");
    }
    

    /**
     * retourne une description textuelle de personnage
     */
    @Override
    public String toString() {
        return this.getNom()+" ("+this.getVie()+", " + this.getEquippedWeapon() + ", " + this.getArmor() + ")";
    }

    /**
     * ajoute de la vie au personnage
     * @param amount la quantité de vie ajouté
     */
    public void addVie(int amount) {
        this.vie += amount;
    }

    //#region getter/setter
    public String getNom() {
        return nom;
    }

    public int getVie() {
        return vie;
    }

    public int getForce() {
        return force;
    }

    public Arme getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Arme arme) {
        this.equippedWeapon = arme;
    }

    public Armure getArmor() {
        return armor;
    }

    public void setArmor(Armure armor) {
        this.armor = armor;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }
    //#endregion

    /**
     * permet de supprimer ce personnage d'une piece
     * @param room la piece où le personnage ce trouve
     */
    public void die(Room room) {
        for (int i=0;i<room.getPersonnages().length;i++)
            if (room.getPersonnages()[i] == this)
                room.getPersonnages()[i] = null;
        room.addItem(this.getEquippedWeapon());
        room.addItem(this.getArmor());
        room.addItem(this.getInventaire());
    }

    
}
