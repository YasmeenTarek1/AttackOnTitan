package Model.Weapons;

import Model.Interfaces.Attacker;
import Model.Titans.Titan;

import java.io.Serial;
import java.io.Serializable;
import java.util.PriorityQueue;

public abstract class Weapon implements Attacker  , Serializable {

    @Serial
    private static final long serialVersionUID = 8L;

    private final int baseDamage;    // Amount of damage a weapon can cause

    public Weapon(int baseDamage) {
        super();
        this.baseDamage = baseDamage;
    }
    @Override
    public int getDamage() {
        return this.baseDamage;
    }

    abstract public int turnAttack(PriorityQueue<Titan> laneTitans);

}
