package game.engine.weapons;

public class SniperCannon extends Weapon {
    private final int WEAPON_CODE = 2;           //  Constant corresponds to a specific weapon type

    public SniperCannon(int baseDamage) {
        super(baseDamage);
    }
    public int getWEAPON_CODE(){
        return WEAPON_CODE;
    }

}
