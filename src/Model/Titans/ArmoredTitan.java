package Model.Titans;

public class ArmoredTitan extends Titan {
    public static final int TITAN_CODE = 3;            // Constant corresponds to a specific titan type

    public ArmoredTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
                        int resourcesValue, int dangerLevel) {
        super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);

    }

    public int takeDamage(int damage) {            // Only takes quarter of the intended damage when attacked
        return super.takeDamage(damage / 4);        // +ve (Titan is killed), 0 (no one isDefeated)
    }

    public String toString() {
        return "" + TITAN_CODE;
    }
}
