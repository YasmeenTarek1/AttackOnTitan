package Model.Weapons;

import Model.Titans.Titan;

import java.util.PriorityQueue;

public class WallTrap extends Weapon {
    public static final int WEAPON_CODE = 4;     //  Constant corresponds to a specific weapon type

    public WallTrap(int baseDamage) {
        super(baseDamage);
    }


    public int turnAttack(PriorityQueue<Titan> laneTitans) {        // Attacks the closest titan only if it reached the wall
        if (laneTitans.isEmpty())
            return 0;
        int resources_gathered = 0;
        Titan t = laneTitans.peek();
        if (t.hasReachedTarget()) {
            resources_gathered = attack(t);
            if (t.isDefeated())
                laneTitans.poll();
        }
        return resources_gathered;
    }

}
