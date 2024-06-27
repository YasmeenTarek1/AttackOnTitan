package Model.Weapons;

public class WeaponRegistry {
    private final int code;    // The type of weapon.
    private int price;        // price of the weapon.
    private int damage;        // Amount of damage a weapon can cause
    private String name;    // Weapon’s name.
    private int minRange;    // The lower bound of a weapon’s damage range.
    private int maxRange;    // The upper bound of a weapon’s damage range.

    public WeaponRegistry(int code, int price) {
        super();
        this.code = code;
        this.price = price;
    }

    public WeaponRegistry(int code, int price, int damage, String name) {
        super();
        this.code = code;
        this.price = price;
        this.damage = damage;
        this.name = name;
    }

    public Weapon buildWeapon() {
        switch (this.getCode()) {
            case PiercingCannon.WEAPON_CODE:
                return new PiercingCannon(this.getDamage());
            case SniperCannon.WEAPON_CODE:
                return new SniperCannon(this.getDamage());
            case VolleySpreadCannon.WEAPON_CODE:
                return new VolleySpreadCannon(this.getDamage(), this.getMinRange(), this.getMaxRange());
            case WallTrap.WEAPON_CODE:
                return new WallTrap(this.getDamage());
            default:
                return null;
        }
    }

    public WeaponRegistry(int code, int price, int damage, String name, int minRange, int maxRange) {
        super();
        this.code = code;
        this.price = price;
        this.damage = damage;
        this.name = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
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
