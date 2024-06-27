package Model.Titans;

import Model.Interfaces.Attackee;
import Model.Interfaces.Attacker;
import Model.Interfaces.Mobil;

public abstract class Titan implements Attacker, Attackee, Mobil, Comparable<Titan> , Cloneable{
    private final int baseHealth;     //  Original titan’s health
    private int currentHealth;        //  Current titan’s health
    private final int baseDamage;     //  Amount of damage a titan would cause while attacking a wall
    private final int heightInMeters; //  The titan’s height.
    private int distanceFromBase;     //  Distance of a titan from the base(wall)(target)
    private int speed;                  //  Speed of a titan (distance moved per turn).
    private final int resourcesValue; //  Resources gained by defeating the titan
    private final int dangerLevel;    //  Danger level of a titan (Used in calculating danger level of the lane)

    public Titan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        super();
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.distanceFromBase = distanceFromBase;
        this.speed = speed;
        this.resourcesValue = resourcesValue;
        this.dangerLevel = dangerLevel;
    }
    @Override
    public Titan clone() {
        try {
            return (Titan) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen since we implement Cloneable
        }
    }

    public int getBaseHealth() {
        return this.baseHealth;
    }

    @Override
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    @Override
    public void setCurrentHealth(int health) {
        this.currentHealth = health < 0 ? 0 : health;
    }

    @Override
    public int getDamage() {
        return this.baseDamage;
    }

    public int getHeightInMeters() {
        return this.heightInMeters;
    }

    @Override
    public int getDistance() {
        return this.distanceFromBase;
    }

    @Override
    public void setDistance(int distance) {
        this.distanceFromBase = distance < 0 ? 0 : distance;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getResourcesValue() {
        return this.resourcesValue;
    }

    public int getDangerLevel() {
        return this.dangerLevel;
    }

    @Override
    public int compareTo(Titan o) // prioritizing the nearest titans according to the wall (Ascending)
    {
        return this.distanceFromBase - o.distanceFromBase;
    }

}
