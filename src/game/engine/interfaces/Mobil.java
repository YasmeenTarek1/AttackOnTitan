package game.engine.interfaces;

// Interface containing the methods available to all objects that has MOBILITY (can move)

public interface Mobil {
	int getDistance(); // retrieves the Mobil’s distance from its target.
	void setDistance(int distance); // changes the Mobil’s distance from its target.
	int getSpeed(); // retrieves the Mobil’s movement speed.
	void setSpeed(int speed); //  changes the Mobil’s movement speed to the input value.


}
