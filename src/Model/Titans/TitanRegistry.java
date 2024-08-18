package Model.Titans;

import java.io.Serial;
import java.io.Serializable;

public class TitanRegistry implements Serializable {
    // A place to store the titan’s information that was read from the csv file read in the data loader.

    @Serial
    private static final long serialVersionUID = 4L;

    private final int code;                 // Type of titan.
    private final int baseHealth;           //  Original titan’s health
    private final int baseDamage;           //  Amount of damage a titan would cause while attacking a wall
    private final int heightInMeters;
    private final int speed;                // distance moved per turn
    private final int resourcesValue;       // resources gained by defeating the titan
    private final int dangerLevel;            // Used in calculating danger level of the lane

    public TitanRegistry(int code, int baseHealth, int baseDamage, int heightInMeters, int speed, int resourcesValue,
                         int dangerLevel) {
        super();
        this.code = code;
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.speed = speed;
        this.resourcesValue = resourcesValue;
        this.dangerLevel = dangerLevel;
    }

    public Titan spawnTitan(int distanceFromBase) {        // Converting from titan registry to titan
        switch (code) {
            case PureTitan.TITAN_CODE:
                return new PureTitan(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed,
                        resourcesValue, dangerLevel);
            case AbnormalTitan.TITAN_CODE:
                return new AbnormalTitan(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed,
                        resourcesValue, dangerLevel);
            case ArmoredTitan.TITAN_CODE:
                return new ArmoredTitan(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed,
                        resourcesValue, dangerLevel);
            case ColossalTitan.TITAN_CODE:
                return new ColossalTitan(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed,
                        resourcesValue, dangerLevel);
            default:
                return null; // EXCEPTION
        }
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
