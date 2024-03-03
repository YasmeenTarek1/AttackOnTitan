package game.engine.weapons;

public class PiercingCannon extends Weapon {
    private final int WEAPON_CODE = 1;           //  Constant corresponds to a specific weapon type

    public PiercingCannon(int baseDamage) {
        super(baseDamage);
    }
    public int getWEAPON_CODE(){
        return WEAPON_CODE;
    }


}