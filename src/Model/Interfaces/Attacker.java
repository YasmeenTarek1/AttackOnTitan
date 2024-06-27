package Model.Interfaces;

public interface Attacker {        // Interface containing the methods available to all ATTACKERS
    // Weapon , Titan implements Attacker interface
    int getDamage();            // Damage value the attacker does

    default int attack(Attackee target) {
        int Resources = target.takeDamage(getDamage());
        return Resources;
    }
}
