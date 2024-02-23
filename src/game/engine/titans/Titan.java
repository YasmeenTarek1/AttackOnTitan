package game.engine.titans;

import game.engine.interfaces.Attackee;
import game.engine.interfaces.Attacker;
import game.engine.interfaces.Mobil;

public abstract class Titan implements Attackee , Attacker,Mobil , Comparable<Titan> {
    private final int baseHealth;
    private int currentHealth;
    private final int baseDamage;
    private final int heightInMeters;
    private int distanceFromBase;
    private int speed;
    private final int resourcesValue;
    private final int dangerLevel;

    public Titan(){
        baseHealth=0;
        baseDamage=0;
        heightInMeters=0;
        resourcesValue=0;
        dangerLevel=0;
    }
    
    public Titan(int baseHealth, int baseDamage, int heightInMeters,
            int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.distanceFromBase = distanceFromBase;
        this.speed = speed;
        this.resourcesValue = resourcesValue;
        this.dangerLevel = dangerLevel;
        this.currentHealth=baseHealth;
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
    	
    	return this.distanceFromBase-o.distanceFromBase;
    	
    }
   
    
    
    
    
}
