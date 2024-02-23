package game.engine.weapons;

public class VolleySpreadCannon extends Weapon{
    private final int WEAPON_CODE;
    private final int minRange;
    private final int maxRange;

    
    public VolleySpreadCannon(int baseDamage ,int min , int max) {
        super(baseDamage);
        minRange=min;
        maxRange=max;
        WEAPON_CODE=3;
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
