package Model.Weapons;

import Model.Titans.Titan;

import java.util.PriorityQueue;

public class VolleySpreadCannon extends Weapon {

    public static final int WEAPON_CODE = 3;     //  Constant corresponds to a specific weapon type
    private final int minRange;                  //  A range of distance to inflict damage on titans
    private final int maxRange;                  //  (nothing happens to a titan outside this range)

    public VolleySpreadCannon(int baseDamage, int minRange, int maxRange) {
        super(baseDamage);
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int turnAttack(PriorityQueue<Titan> laneTitans) {            // Attacks any titan within the specified range indicated by the minimum and maximum
        int total_Resources = 0;
        PriorityQueue<Titan> temp = new PriorityQueue<>();
        while (!laneTitans.isEmpty()) {
            Titan t = laneTitans.peek();
            if (t.getDistance() >= minRange && t.getDistance() <= maxRange) {
                laneTitans.poll();
                total_Resources += attack(t);
                if (!t.isDefeated())
                    temp.add(t);
            } else if (t.getDistance() < minRange)
                temp.add(laneTitans.poll());
            else
                break;
        }
        while (!temp.isEmpty())
            laneTitans.add(temp.poll());
        return total_Resources;
    }

}
