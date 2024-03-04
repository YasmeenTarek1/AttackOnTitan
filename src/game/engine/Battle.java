package game.engine;

import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.factory.WeaponFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Battle {

    // A class representing the Game itself. This class will represent the main engine that manages the flow of the game.

    private final static int[][] PHASES_APPROACHING_TITANS;         // A 2D array containing titans’ codes, representing the order of titans during each phase.
    private final static int WALL_BASE_HEALTH = 10000;              // Original health of any walls created throughout the game.
    private int numberOfTurns;                                      // Number of turns during game play.
    private int resourcesGathered;                                  // Number of available resources throughout the game.
    private BattlePhase battlePhase;                                // An enum representing the current phase.
    private int numberOfTitansPerTurn;                              // Number of titans in a turn throughout the game.
    private int score;                                              // accumulated score throughout the game
    private int titanSpawnDistance;                                 // An integer representing the distance a titan would be spawned at.
    private final WeaponFactory weaponFactory;                      // A WeaponFactory object to store the available weapons to be bought.
    private final HashMap<Integer, TitanRegistry> titansArchives;   // A Hashmap containing an archive of titans based on their codes. Initially has the data read from the dataloader.
    private final ArrayList<Titan> approachingTitans;               // An arraylist representing the coming titans during a turn. Will be treated as a queue (First in First out).
    private final PriorityQueue<Lane> lanes;                        // A queue containing the active lanes based on their danger level. Least dangerous lanes will have the highest priority.
    private final ArrayList<Lane> originalLanes;                    // An arraylist containing all the lanes from the start of the game.

    public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes, int initialResourcesPerLane) throws IOException{
        this.numberOfTurns = numberOfTurns;
        this.score = score;
        this.titanSpawnDistance = titanSpawnDistance;
        this.initialNumOfLanes = initialNumOfLanes;
        this.initialResourcesPerLane = initialResourcesPerLane;

    }
    private void initializeLanes(int numOfLanes){

    }

    public Battle(int numberOfTurns, int resourcesGathered, BattlePhase battlePhase, int score) {
        this.numberOfTurns = numberOfTurns;
        this.resourcesGathered = //initial resources per lane * initial number of lanes.;
        this.battlePhase = EARLY;
        this.numberOfTitansPerTurn = 1;
        this.score = score;
    }
}
