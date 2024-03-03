package game.engine.titans;

import game.engine.interfaces.*;

public abstract class Titan implements Attackee , Attacker, Mobil , Comparable<Titan>{
    private final int baseHealth;     //  Original titan’s health
    private int currentHealth;        //  Current titan’s health
    private final int baseDamage;     //  Amount of damage a titan would cause while attacking a wall
    private final int heightInMeters; //  The titan’s height.
    private int distanceFromBase;     //  Distance of a titan from the base(wall)(target)
    private int speed;				  //  Speed of a titan (distance a titan moves each turn).
    private final int resourcesValue; //  Amount of resources that are gained by defeating the titan
    private final int dangerLevel;    //  Danger level of a titan

    public Titan(){
        baseHealth = 0;
        baseDamage = 0;
        heightInMeters = 0;
        resourcesValue = 0;
        dangerLevel = 0;
    }
    
    public Titan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {

        this.baseHealth = baseHealth; // final
		this.currentHealth = baseHealth;
		this.baseDamage = baseDamage;  // final
        this.heightInMeters = heightInMeters;  // final
        this.distanceFromBase = distanceFromBase;
        this.speed = speed;
        this.resourcesValue = resourcesValue;  // final
        this.dangerLevel = dangerLevel;  // final

    }

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getDistance() {
		return distanceFromBase;
	}

	public void setDistance(int distanceFromBase) {
		this.distanceFromBase = distanceFromBase;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	public int getDamage() {
		return baseDamage;
	}

	public int getHeightInMeters() {
		return heightInMeters;
	}

	public int getResourcesValue() {
		return resourcesValue;
	}

	public int getDangerLevel() {
		return dangerLevel;
	}
    
    public int compareTo(Titan o){ //ascending
		return this.distanceFromBase - o.distanceFromBase;
    }
//	public int compareTo(Object o){ //ascending
//		if(o instanceof Titan) {
//			Titan p = (Titan) o;
//			return this.distanceFromBase - p.distanceFromBase;
//		}
//		else
//			return 0;
//	}
   
    
    
    
    
}
