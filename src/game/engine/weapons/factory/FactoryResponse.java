package game.engine.weapons.factory;

import game.engine.weapons.Weapon;

public class FactoryResponse {

    // used to store the weapon that was bought with the remaining resources

    private final Weapon weapon;
    private final int remainingResources;

    public FactoryResponse(Weapon weapon, int remainingResources) {
        this.weapon = weapon;
        this.remainingResources = remainingResources;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getRemainingResources() {
        return remainingResources;
    }
}
