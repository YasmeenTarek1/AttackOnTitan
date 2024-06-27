package Model.Titans;

public class ColossalTitan extends Titan {
    public static final int TITAN_CODE = 4;    // Constant corresponds to a specific titan type

    public ColossalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);

    }

    public boolean move() {                        // Speed (distance moved) increases by 1 after every movement action
        boolean moveResult = super.move();
        this.setSpeed(this.getSpeed() + 1);
        return moveResult;
    }

    public String toString() {
        return "" + TITAN_CODE;
    }
}
