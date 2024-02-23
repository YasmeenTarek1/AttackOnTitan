package game.engine.weapons;

public class WallTrap extends Weapon {
    private final int WEAPON_CODE;
    public int getWEAPON_CODE(){
        return WEAPON_CODE;
    }
    
    public WallTrap(int baseDamage) {
        super(baseDamage);
        WEAPON_CODE=4;
    }
    
}