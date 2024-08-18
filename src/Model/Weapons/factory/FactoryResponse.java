package Model.Weapons.factory;

import Model.Weapons.Weapon;

import java.io.Serial;
import java.io.Serializable;

public class FactoryResponse implements Serializable {        // weapon bought with the remaining resources

    @Serial
    private static final long serialVersionUID = 6L;

    private Weapon weapon;
    private final int remainingResources;

    public FactoryResponse(Weapon weapon, int remainingResources) {
        super();
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
