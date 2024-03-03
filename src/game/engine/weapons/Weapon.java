package game.engine.weapons;

import game.engine.interfaces.Attacker;

public abstract class Weapon implements Attacker {
    private final int baseDamage; // Amount of damage a weapon can cause

    public Weapon(){
    	baseDamage = 0;
    }
    public Weapon(int baseDamage) {
        this.baseDamage = baseDamage;
    }
    public int getDamage(){
        return baseDamage;
    }
}