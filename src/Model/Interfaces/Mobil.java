package Model.Interfaces;

public interface Mobil {        // Interface containing the methods available to all objects that has MOBILITY (can move)
    // Titan implements Mobil interface
    int getDistance();                    // Mobil’s distance from its target.

    void setDistance(int distance);    // changes the Mobil’s distance from its target.

    int getSpeed();                    // the Mobil’s movement speed.

    void setSpeed(int speed);            //  changes the Mobil’s movement speed to the input value.

    default boolean hasReachedTarget() {
        return getDistance() <= 0;
    }

    default boolean move() {
        int newDistance = getDistance() - getSpeed();
        setDistance(newDistance);
        return hasReachedTarget();
    }
}
