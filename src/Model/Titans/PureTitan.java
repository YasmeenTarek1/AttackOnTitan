package Model.Titans;

public class PureTitan extends Titan {
    public static final int TITAN_CODE = 1;    // Constant corresponds to a specific titan type

    public PureTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
    }

    public String toString() {
        return "" + TITAN_CODE;
    }
}
