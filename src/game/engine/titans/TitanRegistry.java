package game.engine.titans;

public class TitanRegistry {

    // A place to store the titan’s information that was read from the csv file.

    private final int code;                 // Type of titan.
    private final int baseHealth;           //  Original titan’s health
    private final int baseDamage;           //  Amount of damage a titan would cause while attacking a wall
    private final int heightInMeters;
    private final int speed;
    private final int resourcesValue;       //  Amount of resources that are gained by defeating the titan
    private final int dangerLevel;

    public TitanRegistry(){
        this.code = 0;
        this.baseHealth = 0;
        this.baseDamage = 0;
        this.heightInMeters = 0;
        this.speed = 0;
        this.resourcesValue = 0;
        this.dangerLevel = 0;
    }

    public TitanRegistry(int code, int baseHealth, int baseDamage, int heightInMeters, int speed, int resourcesValue, int dangerLevel) {
        this.code = code;
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.speed = speed;
        this.resourcesValue = resourcesValue;
        this.dangerLevel = dangerLevel;
    }
    public int getCode() {
        return code;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getHeightInMeters() {
        return heightInMeters;
    }

    public int getSpeed() {
        return speed;
    }

    public int getResourcesValue() {
        return resourcesValue;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }


}
