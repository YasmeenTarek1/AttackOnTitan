package game.engine.titans;

public class AbnormalTitan extends Titan{
    private final int TITAN_CODE;

    public AbnormalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
        TITAN_CODE = 2;
    }
    public int getTITAN_CODE(){
        return TITAN_CODE;
    }
    
    
}