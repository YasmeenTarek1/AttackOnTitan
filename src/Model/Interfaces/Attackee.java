package Model.Interfaces;

public interface Attackee {        // Interface containing the methods available to all objects GETS ATTACKED
    // Titan , Wall implements Attackee interface
    int getCurrentHealth();

    void setCurrentHealth(int health);

    int getResourcesValue();            // Used when the object is killed / destroyed
    // (titan --> has effect on the player's resources)
    // (Wall --> has NO effect on the player's resources)

    default boolean isDefeated() {
        return this.getCurrentHealth() <= 0;
    }

    default int takeDamage(int damage) {    // returns Resources value if a titan is defeated or a wall is destroyed, Otherwise 0
        this.setCurrentHealth(getCurrentHealth() - damage);
        return isDefeated() ? getResourcesValue() : 0; // +ve (Titan is killed), 0 (no one isDefeated), -1 (Wall is destroyed)
    }

}
