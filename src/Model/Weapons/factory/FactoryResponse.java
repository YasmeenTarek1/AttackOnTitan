package Model.Weapons.factory;

import Model.Weapons.Weapon;

public class FactoryResponse implements Cloneable{        // weapon bought with the remaining resources

    private Weapon weapon;
    private final int remainingResources;

    public FactoryResponse(Weapon weapon, int remainingResources) {
        super();
        this.weapon = weapon;
        this.remainingResources = remainingResources;
    }
    @Override
    public FactoryResponse clone() {
        try {
            FactoryResponse clonedResponse = (FactoryResponse) super.clone();
            clonedResponse.weapon = this.weapon.clone();
            return clonedResponse;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getRemainingResources() {
        return remainingResources;
    }

}
