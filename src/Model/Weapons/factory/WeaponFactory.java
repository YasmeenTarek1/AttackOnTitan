package Model.Weapons.factory;

import Model.Dataloader.DataLoader;
import Model.Exceptions.InsufficientResourcesException;
import Model.Weapons.Weapon;
import Model.Weapons.WeaponRegistry;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public class WeaponFactory implements Serializable {    // (SHOP) Available weapons can be purchased during each turn.

    @Serial
    private static final long serialVersionUID = 7L;

    private final HashMap<Integer, WeaponRegistry> weaponShop;

    public WeaponFactory() throws IOException {
        super();
        weaponShop = DataLoader.readWeaponRegistry();
    }

    public HashMap<Integer, WeaponRegistry> getWeaponShop() {
        return weaponShop;
    }

    public FactoryResponse buyWeapon(int resources, int weaponCode) throws InsufficientResourcesException {
        WeaponRegistry weaponRegistry = weaponShop.get(weaponCode);
        int price = weaponRegistry.getPrice();
        if (price > resources)
            throw new InsufficientResourcesException(resources);
        int remResources = resources - price;
        Weapon weapon = weaponRegistry.buildWeapon();
        return new FactoryResponse(weapon, remResources);
    }

    public void addWeaponToShop(int code, int price) {
        WeaponRegistry W = new WeaponRegistry(code, price);
        weaponShop.put(code, W);
    }

    public void addWeaponToShop(int code, int price, int damage, String name) {
        WeaponRegistry W = new WeaponRegistry(code, price, damage, name);
        weaponShop.put(code, W);
    }

    public void addWeaponToShop(int code, int price, int damage, String name, int minRange, int maxRange) {
        WeaponRegistry W = new WeaponRegistry(code, price, damage, name, minRange, maxRange);
        weaponShop.put(code, W);
    }
}

