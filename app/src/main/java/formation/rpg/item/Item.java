package formation.rpg.item;

/**
 * interface permetant de représenter un Item
 */
public abstract class Item {

    protected String name;

    public Item(String name) {
        this.setName(name);
    }


    /**
     * représentation textuelle de l'item
     */
    @Override
    public abstract String toString();


    //#region getter/setter
    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }
    //#endregion

}
