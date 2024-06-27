package Model.Lanes;

import Model.Titans.Titan;
import Model.Wall.Wall;
import Model.Weapons.Weapon;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Lane implements Comparable<Lane> , Cloneable {        // Lanes in which a titan walk on to the wall

    private Wall laneWall;                     // A wall object found in the lane
    private int dangerLevel;                         // The danger level of a lane based on the danger level of the titans on it.
    private PriorityQueue<Titan> titans;       // All titans in a given lane
    private ArrayList<Weapon> weapons;         // All weapons in a given lane

    public Lane(Wall laneWall) {
        super();
        this.laneWall = laneWall;
        this.dangerLevel = 0;
        this.titans = new PriorityQueue<>();
        this.weapons = new ArrayList<>();
    }
    @Override
    public Lane clone() {
        try {
            Lane clonedLane = (Lane) super.clone();
            // Deep clone laneWall
            clonedLane.laneWall = this.laneWall.clone();
            // Deep clone titans
            clonedLane.titans = new PriorityQueue<>();
            for (Titan titan : this.titans) {
                clonedLane.titans.add(titan.clone());
            }
            // Deep clone weapons
            clonedLane.weapons = new ArrayList<>();
            for (Weapon weapon : this.weapons) {
                clonedLane.weapons.add(weapon.clone());
            }
            return clonedLane;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }



    public Wall getLaneWall() {
        return this.laneWall;
    }

    public int getDangerLevel() {
        return this.dangerLevel;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public PriorityQueue<Titan> getTitans() {
        return this.titans;
    }

    public ArrayList<Weapon> getWeapons() {
        return this.weapons;
    }

    @Override
    public int compareTo(Lane o) {
        return this.dangerLevel - o.dangerLevel;        // Ascending
    }

    public void addTitan(Titan titan) {                    // Adding new titan to the lane
        titans.add(titan);
    }

    public void addWeapon(Weapon weapon) {                // Deploying new weapon to the lane
        weapons.add(weapon);
    }

    public void moveLaneTitans() {
        PriorityQueue<Titan> temp = new PriorityQueue<>();

        while (!titans.isEmpty()) {
            Titan t = titans.poll();
            if (!t.hasReachedTarget())
                t.move();
            temp.add(t);
        }

        while (!temp.isEmpty())
            titans.add(temp.poll());
    }

    public int performLaneTitansAttacks() {            // Expect lost Lane / wall --> Handled in Battle class
        if (isLaneLost())
            return -1;

        PriorityQueue<Titan> temp = new PriorityQueue<>();
        int total_Resources_Gathered = 0;

        while (!titans.isEmpty() && titans.peek().getDistance() <= 0) {
            Titan t = titans.poll();
            temp.add(t);
            total_Resources_Gathered += t.attack(this.laneWall);
        }

        while (!temp.isEmpty())
            titans.add(temp.poll());

        return total_Resources_Gathered;            // 0 (the wall is not destroyed yet), -1 (this Wall & Lane are destroyed)
    }

    public int performLaneWeaponsAttacks() {            // Expect dead titans --> Handled in turnAttack
        if (isLaneLost())
            return -1;

        int total_Resources_Gathered = 0;

        for (int i = 0; i < weapons.size(); i++) {
            Weapon w = weapons.get(i);
            total_Resources_Gathered += w.turnAttack(titans);
        }

        return total_Resources_Gathered;            // +ve (Titans are killed), 0 (NO titans killed),
    }

    public boolean isLaneLost() {
        return getLaneWall().isDefeated();
    }

    public void updateLaneDangerLevel() {
        if (isLaneLost()) {
            dangerLevel = 0;
            titans.clear();
            weapons.clear();
            return;
        }

        int newDanger = 0;
        for (Titan t : titans)
            newDanger += t.getDangerLevel();

        this.dangerLevel = newDanger;
    }
}


