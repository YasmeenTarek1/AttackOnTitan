package Model.Weapons;

import Model.Titans.Titan;

import java.util.PriorityQueue;

public class SniperCannon extends Weapon {
    public static final int WEAPON_CODE = 2;    //  Constant corresponds to a specific weapon type

    public SniperCannon(int baseDamage) {
        super(baseDamage);
    }

    public int turnAttack(PriorityQueue<Titan> laneTitans) {            // Attacks the closest titan if it exists
        if (laneTitans.isEmpty())                                            // Exception
            return 0;
        Titan t = laneTitans.peek();
        int resourcesGathered = attack(t);
        if (t.isDefeated())
            laneTitans.poll();
        return resourcesGathered;
    }

}
