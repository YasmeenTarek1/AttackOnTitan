package Model.Weapons;

import Model.Interfaces.Attacker;
import Model.Titans.Titan;

import java.util.PriorityQueue;

public abstract class Weapon implements Attacker , Cloneable{
    private final int baseDamage;    // Amount of damage a weapon can cause

    public Weapon(int baseDamage) {
        super();
        this.baseDamage = baseDamage;
    }
    @Override
    public Weapon clone() {
        try {
            return (Weapon) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen since we implement Cloneable
        }
    }

    @Override
    public int getDamage() {
        return this.baseDamage;
    }

    abstract public int turnAttack(PriorityQueue<Titan> laneTitans);

}
