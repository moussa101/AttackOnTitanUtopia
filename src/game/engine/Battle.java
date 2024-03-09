package game.engine;

import game.engine.base.Wall;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.factory.WeaponFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Battle {
    private final static int[][] PHASES_APPROACHING_TITANS = new int[1][];
    private final int WALL_BASE_HEALTH = 10000;
    private int numberOfTurns;
    private int resourcesGathered;
    private BattlePhase battlePhase;
    private int numberOfTitansPerTurn;
    private int score;
    private int titanSpawnDistance;
    private final WeaponFactory weaponFactory;
    private final HashMap<Integer, TitanRegistry> titansArchives;
    private final ArrayList<Titan> approachingTitans;
    private final PriorityQueue<Lane> lanes;
    private final ArrayList<Lane> originalLanes;


    public Battle(int[][] phasesApproachingTitans, WeaponFactory weaponFactory, HashMap<Integer, TitanRegistry> titansArchives, ArrayList<Titan> approachingTitans, PriorityQueue<Lane> lanes, ArrayList<Lane> originalLanes) {
        this.weaponFactory = weaponFactory;
        this.titansArchives = titansArchives;
        this.approachingTitans = approachingTitans;
        this.lanes = lanes;
        this.originalLanes = originalLanes;
    }

    private void initializeLanes(int numOfLanes){
        for (int i = 0; i < numOfLanes; i++) {
            Wall b = new Wall(WALL_BASE_HEALTH);
            Lane a = new Lane(b);
            originalLanes.add(a);
            lanes.add(a);
        }
    }

    public int[][] getPHASES_APPROACHING_TITANS() {
        return PHASES_APPROACHING_TITANS;
    }

    public int getWALL_BASE_HEALTH() {
        return WALL_BASE_HEALTH;
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

}
