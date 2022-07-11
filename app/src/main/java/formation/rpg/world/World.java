package formation.rpg.world;

import java.util.HashMap;

import formation.rpg.personnage.Personnage;
import formation.rpg.util.Vector2;

/**
 * classe représentant le "plateau de jeu"
 */
public class World {

    //#region attributs
    protected HashMap<Vector2, Room> world;
    protected Vector2 playerPosition;
    protected Personnage player;
    //#endregion

    /**
     * instancie un nouveau monde
     * @param joueur le joueur du monde
     */
    public World(Personnage joueur) {
        this.player = joueur;
        this.world = new HashMap<>();
        this.playerPosition = new Vector2(50, 0);
        this.world.put(this.playerPosition, new Room(this.playerPosition, true));
        this.world.get(playerPosition).in(joueur);
        this.createNeighbors(this.playerPosition);
    }

    /**
     * permet de changé de piéce le joueur 
     * @param direction la direction de la nouvelle piéce (North:0,Est:1,South:2,West:3)
     */
    public void movePlayerTo(int direction) {
        Room room = this.getPlayerRoom();
        this.getNeighbors(room)[direction].in(room.out());
        this.createNeighbors(this.getNeighbors(room)[direction].position);
        switch (direction) {
            case 0 -> this.playerPosition = this.playerPosition.getNorth();
            case 1 -> this.playerPosition = this.playerPosition.getEst();
            case 2 -> this.playerPosition = this.playerPosition.getSouth();
            case 3 -> this.playerPosition = this.playerPosition.getWest();
        }
    }

    /**
     * retour les quatres piéce voisines
     * @param room la piéce centrale
     * @return un tableau des quatres piéces voisines
     */
    public Room[] getNeighbors(Room room) {
        Room[] neighbors = new Room[4];

        neighbors[0] = this.world.getOrDefault(room.position.getNorth(), null);
        neighbors[1] = this.world.getOrDefault(room.position.getEst(), null);
        neighbors[2] = this.world.getOrDefault(room.position.getSouth(), null);
        neighbors[3] = this.world.getOrDefault(room.position.getWest(), null);

        return neighbors;
    }

    /**
     * crée les piéces voisines 
     * @param position la position de la piéce central
     */
    private void createNeighbors(Vector2 position) {
        if (!this.world.containsKey(position.getNorth()))
            if (Math.random() < 0.8f)
                this.world.put(position.getNorth(), new Room(position.getNorth()));
        if (!this.world.containsKey(position.getEst()))
            if (Math.random() < 0.8f)
                this.world.put(position.getEst(), new Room(position.getEst()));
        if (!this.world.containsKey(position.getSouth()))
            if (Math.random() < 0.8f)
                this.world.put(position.getSouth(), new Room(position.getSouth()));
        if (!this.world.containsKey(position.getWest()))
            if (Math.random() < 0.8f)
                this.world.put(position.getWest(), new Room(position.getWest()));
    }
    
    //#region getter/setter
    public Personnage getPlayer() {
        return this.player;
    }

    public Vector2 getPlayerPosition() {
        return this.playerPosition;
    }

    public Room getPlayerRoom() {
        return this.world.get(this.playerPosition);
    }
    //#endregion

}
