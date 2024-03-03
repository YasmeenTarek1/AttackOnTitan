package game.engine.weapons;

public class VolleySpreadCannon extends Weapon{
    private final int WEAPON_CODE = 3;           //  Constant corresponds to a specific weapon type
    private final int minRange;                  //  A range of distance to inflict damage on titans
    private final int maxRange;                  //  (nothing happens to a titan outside this range)

    public VolleySpreadCannon(int baseDamage ,int minRange , int maxRange) {
        super(baseDamage);
        this.minRange = minRange;
        this.maxRange = maxRange;
    }
    public int getWEAPON_CODE(){
        return WEAPON_CODE;
    }
	public int getMinRange() {
		return minRange;
	}
	public int getMaxRange() {
		return maxRange;
	}
    
}
