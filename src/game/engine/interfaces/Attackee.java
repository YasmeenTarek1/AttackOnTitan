package game.engine.interfaces;

// Interface containing the methods available to all objects that GETS ATTACKED
public interface Attackee {
	int getCurrentHealth();
	void setCurrentHealth(int health);
	int getResourcesValue();
	
}
