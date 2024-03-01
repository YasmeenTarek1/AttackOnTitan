package game.engine.weapons;

public class VolleySpreadCannon extends Weapon{
    private final int minRange;  //  A range of distance to inflict damage on titans
    private final int maxRange;  //  (nothing happens to a titan outside this range)
    public VolleySpreadCannon(int baseDamage ,int minRange , int maxRange) {
        super(baseDamage , 3);
        this.minRange = minRange;
        this.maxRange = maxRange;
    }
	public int getMinRange() {
		return minRange;
	}
	public int getMaxRange() {
		return maxRange;
	}
    
}
