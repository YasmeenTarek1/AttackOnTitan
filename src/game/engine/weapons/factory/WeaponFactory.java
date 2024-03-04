package game.engine.weapons.factory;

import game.engine.weapons.WeaponRegistry;
import game.engine.dataloader.DataLoader;
import java.io.IOException;
import java.util.HashMap;

public class WeaponFactory {

    // Used to store the available weapons that can be purchased during each turn.

    private final HashMap<Integer, WeaponRegistry> weaponShop;

    public WeaponFactory() throws IOException {
        this.weaponShop = DataLoader.readWeaponRegistry();
    }
}
