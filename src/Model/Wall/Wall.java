package Model.Wall;

import Model.Interfaces.Attackee;

import java.io.Serial;
import java.io.Serializable;

public class Wall implements Attackee  , Serializable {            // A class represents the Walls which Titan attacks

    @Serial
    private static final long serialVersionUID = 5L;

    private final int baseHealth;        //  Original value of the wall’s health
    private int currentHealth;            //  Current Wall's health

    public Wall(int baseHealth) {
        super();
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
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
