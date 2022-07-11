package formation.rpg.exception;

public class NoItemAtThisIndexException extends Exception{

    public NoItemAtThisIndexException() {
        super("Il n'y a pas d'item dans cette case de l'inventaire");
    }
    
}
