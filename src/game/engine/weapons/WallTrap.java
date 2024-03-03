package game.engine.weapons;

public class WallTrap extends Weapon {
    private final int WEAPON_CODE = 4;           //  Constant corresponds to a specific weapon type

    public WallTrap(int baseDamage) {
        super(baseDamage);
    }
    public int getWEAPON_CODE(){
        return WEAPON_CODE;
    }
    
}