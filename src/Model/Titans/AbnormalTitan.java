package Model.Titans;

import Model.Interfaces.Attackee;

public class AbnormalTitan extends Titan {
    public static final int TITAN_CODE = 2;    // Constant corresponds to a specific titan type

    public AbnormalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);

    }

    public int attack(Attackee target) {            // Performs the attack action on target twice per turn
        int first_resources = super.attack(target);
        return target.isDefeated() ? first_resources : super.attack(target);    // 0 (no one isDefeated), -1 (Wall is destroyed)
    }

    public String toString() {
        return "" + TITAN_CODE;
    }

}
