package game.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.*;
import game.engine.weapons.Weapon;
import game.engine.weapons.WeaponRegistry;
import game.engine.weapons.factory.WeaponFactory;

public class Battle {

    private final static int[][] PHASES_APPROACHING_TITANS =
            {{1, 1, 1, 2, 1, 3, 4}, {2, 2, 2, 1, 3, 3, 4}, {4, 4, 4, 4, 4, 4, 4}};
    private final static int WALL_BASE_HEALTH = 10000;
    private final WeaponFactory weaponFactory;
    private final HashMap<Integer, TitanRegistry> titansArchives;
    private final ArrayList<Titan> approachingTitans;
    private final PriorityQueue<Lane> lanes;
    private final ArrayList<Lane> originalLanes;
    private int numberOfTurns;
    private int resourcesGathered;
    private BattlePhase battlePhase;
    private int numberOfTitansPerTurn;
    private int score;
    private int titanSpawnDistance;


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

    public int[][] getPHASES_APPROACHING_TITANS() {
        return PHASES_APPROACHING_TITANS;
    }

    public int getWALL_BASE_HEALTH() {
        return WALL_BASE_HEALTH;
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


    public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes, int initialResourcesPerLane) throws IOException {
        this.numberOfTurns = numberOfTurns;
        this.score = score;
        this.titanSpawnDistance = titanSpawnDistance;
        this.battlePhase = BattlePhase.EARLY;
        this.numberOfTitansPerTurn = 1;
        resourcesGathered = initialNumOfLanes * initialResourcesPerLane;
        titansArchives = DataLoader.readTitanRegistry();
        approachingTitans = new ArrayList<Titan>();
        lanes = new PriorityQueue<Lane>();
        originalLanes = new ArrayList<Lane>();
        weaponFactory = new WeaponFactory();
        initializeLanes(initialNumOfLanes);

    }

    private void initializeLanes(int numOfLanes) {
        for (int i = 0; i < numOfLanes; i++) {
            Lane l = new Lane(new Wall(WALL_BASE_HEALTH));
            originalLanes.add(l);
            lanes.add(l);

        }
    }

    public void refillApproachingTitans() {
        int[][] a = getPHASES_APPROACHING_TITANS();
        if (getBattlePhase().equals(BattlePhase.EARLY)){
            for (int i = 0; i <a[1].length ; i++) {

            }

        }
        else if (getBattlePhase().equals(BattlePhase.INTENSE)) {
            for (int i = 0; i < a[2].length; i++) {

            }

        } else if (getBattlePhase().equals(BattlePhase.GRUMBLING)) {
            for (int i = 0; i < a[3].length; i++) {

            }

        }

    }


    public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException, InvalidLaneException {
        if (lane.isLaneLost()) {
               throw new InvalidLaneException("Weapons can only be added to active lanes");
        }
        else {


        }

    }

    private void moveTitans() {
        for (Lane lane : lanes) {
            if (!(lane.isLaneLost())) {
                for (Titan titan : lane.getTitans()) {
                    titan.move();
                }
            }
        }
    }
    private int performWeaponsAttacks(){
        int sum =0;
        for (Lane lane : lanes) {
            if (!(lane.isLaneLost())) {
                sum+= lane.getLaneWall().takeDamage(performWeaponsAttacks());
                }
            }
         return sum;
    }
    private void finalizeTurns(){
        numberOfTurns++;

        if (numberOfTurns<15){
           setBattlePhase(BattlePhase.EARLY);
        }
        else if (numberOfTurns<30) {
            setBattlePhase(BattlePhase.INTENSE);
        }
        else if (numberOfTurns>=30){
            setBattlePhase(BattlePhase.GRUMBLING);
            if(numberOfTurns%5==0){
                setNumberOfTitansPerTurn(getNumberOfTitansPerTurn()*2);
            }
        }

    }
    public boolean isGameOver(){
        for (Lane lane : lanes) {
            if (!(lane.isLaneLost())) {
                return false;
                }
            }
        return true;
    }
    }
