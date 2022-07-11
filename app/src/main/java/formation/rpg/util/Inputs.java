package formation.rpg.util;

import java.util.Scanner;

/**
 * classe permentant de recupérer des valeurs entrées par l'utilisateur
 */
public class Inputs {

    public static final Scanner scan = new Scanner(System.in);

    /**
     * recupere un String entré par l'utilisateur 
     * @return le String entré par l'utilisateur
     */
    public static String getString() {
        return Inputs.getString("");
    }

    /**
     * affiche un message a l'utilisateur et recupere un String entré par l'utilisateur 
     * @param message le message a afficher
     * @return le String entré par l'utilisateur
     */
    public static String getString(String message) {
        System.out.print(message);
        return Inputs.scan.next();
    }

    /**
     * recupere un int entré par l'utilisateur 
     * @return un int entré par l'utilisateur 
     */
    public static int getInt() {
        return Inputs.getInt("");
    }

    /**
     * affiche un message a l'utilisateur et recupere un int entré par l'utilisateur 
     * @param message le message a afficher
     * @return le int entré par l'utilisateur
     */
    public static int getInt(String message) {
        System.out.println(message);
        return Inputs.scan.nextInt();
    }

}
