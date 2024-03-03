package game.engine.base;

import game.engine.interfaces.Attackee;

public class Wall implements Attackee {  // A class represents the Walls which Titan attacks
    private final int baseHealth; //  Original value of the wall’s health
    private int currentHealth;    //  Current Wall's health

    public Wall(){
        baseHealth = 0;
    }

    public Wall(int baseHealth) {
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    public int getResourcesValue(){
        return -1;
    }
}
