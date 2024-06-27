package Model.Engine;

import Model.Dataloader.DataLoader;
import Model.Exceptions.InsufficientResourcesException;
import Model.Exceptions.InvalidLaneException;
import Model.Lanes.Lane;
import Model.Titans.Titan;
import Model.Titans.TitanRegistry;
import Model.Wall.Wall;
import Model.Weapons.Weapon;
import Model.Weapons.factory.FactoryResponse;
import Model.Weapons.factory.WeaponFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import static Model.Engine.BattlePhase.EARLY;

public class Battle implements Cloneable{                    // The Game itself (The main Model.engine that manages the flow of the game)

    private static final int[][] PHASES_APPROACHING_TITANS = {            // A 2D array containing titans’ codes, representing the refilling order of titans during each phase.
            {1, 1, 1, 2, 1, 3, 4},
            {2, 2, 2, 1, 3, 3, 4},
            {4, 4, 4, 4, 4, 4, 4}
    };
    private static final int WALL_BASE_HEALTH = 10000;                    // Original health of any wall created throughout the game.
    private int numberOfTurns;                                            // Number of elapsed turns
    private int resourcesGathered;                                        // Money earned by defeating titans and used in buying weapons
    private BattlePhase battlePhase;                                      // An enum representing the current phase.
    private int numberOfTitansPerTurn;                                    // initially equals to 1
    private int score;                                                    // Player's score
    private int titanSpawnDistance;                                       // Distance a titan would be spawned at.
    private WeaponFactory weaponFactory;                                  // A WeaponFactory object to store the available weapons to be bought.
    private HashMap<Integer, TitanRegistry> titansArchives;               // A Hashmap containing an archive of titans based on their codes. Initially has the data read from the dataloader.
    private ArrayList<Titan> approachingTitans;                           // Coming titans during a turn. Treated as a queue
    private PriorityQueue<Lane> lanes = new PriorityQueue<>();            // Active lanes based on their danger level. Least dangerous lanes will have the highest priority.
    private ArrayList<Lane> originalLanes = new ArrayList<>();            // All lanes from the start of the game.
    private ArrayList<Titan> addedTitansToTheLane;
    private Lane added;

    public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes, int initialResourcesPerLane) throws IOException {
        super();
        this.numberOfTurns = numberOfTurns;
        this.battlePhase = EARLY;
        this.numberOfTitansPerTurn = 1;
        this.score = score;
        this.titanSpawnDistance = titanSpawnDistance;
        this.resourcesGathered = initialResourcesPerLane * initialNumOfLanes;
        this.weaponFactory = new WeaponFactory();
        this.titansArchives = DataLoader.readTitanRegistry();
        this.approachingTitans = new ArrayList<>();
        this.initializeLanes(initialNumOfLanes);
        this.addedTitansToTheLane = new ArrayList<>();
    }

    @Override
    public Battle clone() {
        try {
            Battle clonedBattle = (Battle) super.clone();
            clonedBattle.weaponFactory = this.weaponFactory;
            clonedBattle.titansArchives = this.titansArchives;
            clonedBattle.approachingTitans = new ArrayList<>();
            for (Titan titan : this.approachingTitans) {
                clonedBattle.approachingTitans.add(titan.clone());
            }
            clonedBattle.lanes = new PriorityQueue<>();
            clonedBattle.originalLanes = new ArrayList<>();
            for (Lane lane : this.originalLanes) {
                Lane l = lane.clone();
                clonedBattle.originalLanes.add(l);
                clonedBattle.lanes.add(l);
            }
            return clonedBattle;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen since we implement Cloneable
        }
    }

        public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public int getResourcesGathered() {
        return resourcesGathered;
    }

    public void setResourcesGathered(int resourcesGathered) {
        this.resourcesGathered = resourcesGathered;
    }

    public BattlePhase getBattlePhase() {
        return battlePhase;
    }

    public void setBattlePhase(BattlePhase battlePhase) {
        this.battlePhase = battlePhase;
    }

    public int getNumberOfTitansPerTurn() {
        return numberOfTitansPerTurn;
    }

    public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
        this.numberOfTitansPerTurn = numberOfTitansPerTurn;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTitanSpawnDistance() {
        return titanSpawnDistance;
    }

    public void setTitanSpawnDistance(int titanSpawnDistance) {
        this.titanSpawnDistance = titanSpawnDistance;
    }

    public WeaponFactory getWeaponFactory() {
        return weaponFactory;
    }

    public HashMap<Integer, TitanRegistry> getTitansArchives() {
        return titansArchives;
    }

    public ArrayList<Titan> getApproachingTitans() {
        return approachingTitans;
    }

    public PriorityQueue<Lane> getLanes() {
        return lanes;
    }

    public ArrayList<Lane> getOriginalLanes() {
        return originalLanes;
    }

    public ArrayList<Titan> getAddedTitansToTheLane() {
        return addedTitansToTheLane;
    }

    public Lane getAddedLane() {
        return added;
    }

    private void initializeLanes(int numOfLanes) {
        for (int i = 0; i < numOfLanes; i++) {
            Wall w = new Wall(WALL_BASE_HEALTH);
            Lane l = new Lane(w);
            this.getOriginalLanes().add(l);
            this.getLanes().add(l);
        }
    }


    public void refillApproachingTitans() // spawns titans of the specified code to lanes based on the current phase
    {
        int[] phaseApproachingTitans;

        switch (this.getBattlePhase())
        {
            case EARLY:
                phaseApproachingTitans = PHASES_APPROACHING_TITANS[0];
                break;
            case INTENSE:
                phaseApproachingTitans = PHASES_APPROACHING_TITANS[1];
                break;
            case GRUMBLING:
                phaseApproachingTitans = PHASES_APPROACHING_TITANS[2];
                break;
            default:
                phaseApproachingTitans = new int[0];
        }

        for (int code : phaseApproachingTitans)
        {
            Titan spawnedTitan = this.getTitansArchives().get(code).spawnTitan(this.getTitanSpawnDistance());
            this.getApproachingTitans().add(spawnedTitan);
        }
    }

    public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException, InvalidLaneException {
        if (!this.getLanes().contains(lane)) {
            throw new InvalidLaneException("Weapon purchase failed");
        }

        // Get the factory response from the weapon factory
        FactoryResponse factoryResponse = this.getWeaponFactory().buyWeapon(getResourcesGathered(), weaponCode);

        if (factoryResponse != null && factoryResponse.getWeapon() != null) {
            // Clone the factory response to get a deep copy
            FactoryResponse factoryResponseCLONED = factoryResponse.clone();
            Weapon purchasedWeapon = factoryResponseCLONED.getWeapon();
            lane.addWeapon(purchasedWeapon);
            this.setResourcesGathered(factoryResponseCLONED.getRemainingResources());
            performTurn();
        }
    }



public void passTurn() {
        performTurn();
    }

    private void addTurnTitansToLane() {
        Lane lane = null;
        if(this.getLanes().peek() != null) {
            lane = this.getLanes().poll();
            added = lane;
            addedTitansToTheLane = new ArrayList<>();
            for (int i = 0; i < numberOfTitansPerTurn; i++) {
                if (approachingTitans.isEmpty())
                    refillApproachingTitans();
                lane.addTitan(approachingTitans.get(0));
                addedTitansToTheLane.add(approachingTitans.remove(0));
            }
            lanes.add(lane);
        }
    }

    private void moveTitans() {
        PriorityQueue<Lane> tempLanes = new PriorityQueue<>();

        while (!lanes.isEmpty()) {
            Lane l = lanes.poll();
            l.moveLaneTitans();
            tempLanes.add(l);
        }

        while (!tempLanes.isEmpty())
            lanes.add(tempLanes.poll());
    }

    private int performWeaponsAttacks() {
        int resourcesGathered = 0;

        for (Lane l : this.getLanes()) {
            resourcesGathered += l.performLaneWeaponsAttacks();
        }

        this.setResourcesGathered(this.getResourcesGathered() + resourcesGathered);
        this.setScore(this.getScore() + resourcesGathered);

        return resourcesGathered;
    }

    private int performTitansAttacks(){
        int resourcesGathered = 0;
        ArrayList<Lane> lostLanes = new ArrayList<>();

        for (Lane l : this.getLanes()) {
            resourcesGathered += l.performLaneTitansAttacks();
            if (l.isLaneLost()) {
                lostLanes.add(l);
            }
        }

        this.getLanes().removeAll(lostLanes);

        return resourcesGathered;
    }

    private void updateLanesDangerLevels() {
        ArrayList<Lane> tmp = new ArrayList<>();

        while(!this.getLanes().isEmpty()) {
            Lane l = this.getLanes().poll();
            l.updateLaneDangerLevel();
            tmp.add(l);
        }

        this.getLanes().addAll(tmp);
    }
    private void finalizeTurns() {
        this.setNumberOfTurns(this.getNumberOfTurns() + 1);

        if (this.getNumberOfTurns() == 15)
            this.setBattlePhase(BattlePhase.INTENSE);
        else if (this.getNumberOfTurns() == 30)
            this.setBattlePhase(BattlePhase.GRUMBLING);
        else if (this.getNumberOfTurns() > 30 && this.getNumberOfTurns() % 5 == 0)
            this.setNumberOfTitansPerTurn(this.getNumberOfTitansPerTurn()+1);
    }

    private void performTurn() {
        this.moveTitans();
        this.performWeaponsAttacks();
        this.performTitansAttacks();

        this.addTurnTitansToLane();
        this.updateLanesDangerLevels();

        this.finalizeTurns();
    }

    public boolean isGameOver(){ // checks if all lanes are destroyed
        return this.getLanes().isEmpty();
    }


}
  
  
  
  
  
  
  
  


