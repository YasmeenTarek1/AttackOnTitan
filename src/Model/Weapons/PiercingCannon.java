package Model.Weapons;

import Model.Titans.Titan;

import java.util.PriorityQueue;

public class PiercingCannon extends Weapon {
    public static final int WEAPON_CODE = 1;    //  Constant corresponds to a specific weapon type

    public PiercingCannon(int baseDamage) {
        super(baseDamage);
    }

    public int turnAttack(PriorityQueue<Titan> laneTitans) {        // Attacks the closest 5 titans if they exist
        PriorityQueue<Titan> temp = new PriorityQueue<>();
        int totalResourcesGathered = 0;
        for (int i = 0; i < 5 && !laneTitans.isEmpty(); i++) {
            Titan t = laneTitans.poll();
            int resources_value = attack(t);            // Edit resources value and score in battle class
            if (!t.isDefeated())                            // Removing Dead Titans
                temp.add(t);
            totalResourcesGathered += resources_value;
        }
        while (!temp.isEmpty())
            laneTitans.add(temp.poll());
        return totalResourcesGathered;
    }


}
