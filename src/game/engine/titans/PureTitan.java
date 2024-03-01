package game.engine.titans;

public class PureTitan extends Titan{
    private final int TITAN_CODE; // Constant corresponds to a specific titan type

    public PureTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
        TITAN_CODE = 1;
    }
    public int getTITAN_CODE(){
        return TITAN_CODE;
    }
  
    
    
    
}