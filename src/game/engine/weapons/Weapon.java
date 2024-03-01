package game.engine.weapons;

import game.engine.interfaces.Attacker;

public abstract class Weapon implements Attacker {
    private final int baseDamage; // Amount of damage a weapon can cause
    private final int WEAPON_CODE;  //  Constant corresponds to a specific weapon type

    public Weapon(){
    	baseDamage = 0;
        WEAPON_CODE = -1;
    }
    public Weapon(int baseDamage , int WEAPON_CODE) {
        this.baseDamage = baseDamage;
        this.WEAPON_CODE = WEAPON_CODE;
    }
    public int getWEAPON_CODE(){
        return WEAPON_CODE;
    }

    public int getDamage(){
        return baseDamage;
    }
}