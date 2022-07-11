package formation.rpg.exception;

public class InventoryFullException extends Exception {
    
    public InventoryFullException() {
        super("L'inventaire est rempli");
    }

}
