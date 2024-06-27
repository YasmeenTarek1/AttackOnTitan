package Model.Wall;

import Model.Interfaces.Attackee;

public class Wall implements Attackee , Cloneable {            // A class represents the Walls which Titan attacks
    private final int baseHealth;        //  Original value of the wall’s health
    private int currentHealth;            //  Current Wall's health

    public Wall(int baseHealth) {
        super();
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
    }
    @Override
    public Wall clone() {
        try {
            return (Wall) super.clone();
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
    public int getResourcesValue() { // A wall is destroyed (Has no effect on the player's resources)
        return -1;
    }

}
