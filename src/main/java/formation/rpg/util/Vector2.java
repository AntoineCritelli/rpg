package formation.rpg.util;

/**
 * classe permetant de représenté un vecteur2 
 */
public class Vector2 {
    private int x;
    private int y;

    /**
     * crée un vecteur2 de coordonnées (0,0)
     */
    public Vector2() {
        this(0, 0);
    }

    /**
     * crée un vecteur2 de coordonnées (x,y)
     * @param x la coordonnée x du vecteur
     * @param y la coordonnée y du vecteur
     */
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * renvoie un hachcode identique pour des coordonnées identiques
     */
    @Override
    public int hashCode() {
        return ((this.x+this.y)/(this.y-this.x+1));
    }

    /**
     * permet de tester l'equivalence de deux vecteurs
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector2))
            return false;
        Vector2 v = (Vector2) obj;
        return this.getX()==v.getX() && this.getY()==v.getY();
    }

    /**
     * representation textuelle du vecteur
     */
    @Override
    public String toString() {
        return "("+this.x+","+this.y+")";
    }

    //#region getter/setter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * permet de recuperer le vecteur de coordonnée x,y+1
     * @return un nouveau vecteur2
     */
    public Vector2 getNorth() {
        return new Vector2(this.x, this.y+1);
    }
    /**
     * permet de recuperer le vecteur de coordonnée x+1,y
     * @return un nouveau vecteur2
     */
    public Vector2 getEst() {
        return new Vector2(this.x+1, this.y);
    }
    /**
     * permet de recuperer le vecteur de coordonnée x,y-1
     * @return un nouveau vecteur2
     */
    public Vector2 getSouth() {
        return new Vector2(this.x, this.y-1);
    }
    /**
     * permet de recuperer le vecteur de coordonnée x-1,y
     * @return un nouveau vecteur2
     */
    public Vector2 getWest() {
        return new Vector2(this.x-1, this.y);
    }
    //#endregion
    
}
