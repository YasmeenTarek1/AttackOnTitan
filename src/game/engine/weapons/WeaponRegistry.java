package game.engine.weapons;

public class WeaponRegistry {
    private final int code;     // The type of weapon.
    private final int price;    // price of the weapon.
    private final int damage;   // Amount of damage a weapon can cause
    private final String name;  // Weapon’s name.
    private final int minRange; // The lower bound of a weapon’s damage range.
    private final int maxRange; // The upper bound of a weapon’s damage range.

    public WeaponRegistry(){
        this.code = 0;
        this.price = 0;
        this.damage = 0;
        this.name = null;
        this.minRange = 0;
        this.maxRange = 0;
    }
    public WeaponRegistry(int code, int price, int damage, String name, int minRange, int maxRange) {
        this.code = code;
        this.price = price;
        this.damage = damage;
        this.name = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }
    public WeaponRegistry(int code, int price, int damage, String name){
        this(code, price, damage, name , 0,0);
    }
    public WeaponRegistry(int code, int price){
        this(code, price, 0 , null , 0,0);
    }
    public int getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

}
