package formation.rpg.exception;

public class NoSuchItemInventoryException extends Exception{

    public NoSuchItemInventoryException() {
        super("L'item n'est pas dans l'inventaire");
    }
    
}
