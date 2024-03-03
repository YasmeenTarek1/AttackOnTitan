package game.engine.lanes;
import game.engine.base.Wall;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;
import java.util.*;

public class Lane implements Comparable<Lane>{ //  A class representing the Lanes in which a titan walk on to the wall

    private final Wall laneWall;                     // A wall object found in the lane
    private int dangerLevel;                         // The danger level of a lane based on the number and danger level of the titans on it.
    private final PriorityQueue<Titan> titans;       // All the titans in a given lane
    private final ArrayList<Weapon> weapons;         // All the weapons in a given lane

    public Lane(){
        this(null);
    }
    public Lane(Wall laneWall) {
        this.laneWall = laneWall;
        this.dangerLevel = 0;
        this.titans = new PriorityQueue<>();
        this.weapons = new ArrayList<>();
    }
    public Wall getLaneWall() {
        return laneWall;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public PriorityQueue<Titan> getTitans() {
        return titans;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public int compareTo(Lane o){
        return this.dangerLevel - o.dangerLevel;  // ascending
    }
}
