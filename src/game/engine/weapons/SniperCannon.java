package game.engine.weapons;

public class SniperCannon extends Weapon {
    private final int WEAPON_CODE;
    
    public int getWEAPON_CODE(){
        return WEAPON_CODE;
    }

    public SniperCannon(int baseDamage) {
        super(baseDamage);
        WEAPON_CODE=2;
    }
    
}
