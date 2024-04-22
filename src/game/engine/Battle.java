package game.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.GameActionException;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.*;
import game.engine.weapons.Weapon;
import game.engine.weapons.WeaponRegistry;
import game.engine.weapons.factory.FactoryResponse;
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
        if (approachingTitans.isEmpty()) {

            int[][] a = getPHASES_APPROACHING_TITANS();
            if (getBattlePhase().equals(BattlePhase.EARLY)) {
                for (int i = 0; i < a[0].length; i++) {
                    TitanRegistry b = getTitansArchives().get(a[0][i]);
                    approachingTitans.add(b.spawnTitan(getTitanSpawnDistance()));
                }

            } else if (getBattlePhase().equals(BattlePhase.INTENSE)) {
                for (int i = 0; i < a[1].length; i++) {
                    TitanRegistry b = getTitansArchives().get(a[1][i]);
                    approachingTitans.add(b.spawnTitan(getTitanSpawnDistance()));

                }

            } else if (getBattlePhase().equals(BattlePhase.GRUMBLING)) {
                for (int i = 0; i < a[2].length; i++) {
                    TitanRegistry b = getTitansArchives().get(a[2][i]);
                    approachingTitans.add(b.spawnTitan(getTitanSpawnDistance()));

                }

            }

        }
    }


    public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException, InvalidLaneException {
        if (lane.isLaneLost()||!lanes.contains(lane)|| lane==null) {
               throw new InvalidLaneException("Weapons can only be added to active lanes");
        }
        else {
                WeaponFactory a = getWeaponFactory();
                    FactoryResponse b = a.buyWeapon(resourcesGathered,weaponCode);
                    Weapon c = b.getWeapon();
                    setResourcesGathered(b.getRemainingResources());
                    lane.addWeapon(c);
                    passTurn();

        }

    }
    public void passTurn(){
        performTurn();
    }
    public Lane LeastDangerousLane(PriorityQueue<Lane> a) {
        for (int i = 0; i <lanes.size() ; i++) {
            if (lanes.peek().isLaneLost()){
                lanes.remove();
            }
        }
        return lanes.peek();
    }

    private void addTurnTitansToLane(){
        if (approachingTitans.isEmpty()){
            refillApproachingTitans();
        }

        for (int i = 0; i < numberOfTitansPerTurn; i++) {
            if (approachingTitans.isEmpty()){
                refillApproachingTitans();
            }
            Lane leastDanger = LeastDangerousLane(lanes);
            leastDanger.addTitan(approachingTitans.get(0));
            approachingTitans.remove(0);

        }
    }

    private void moveTitans() {
        for (Lane lane : lanes) {
            lane.moveLaneTitans();
            }
        }
    private int performWeaponsAttacks() {
        int sum = 0;
        lanes.removeIf(Lane::isLaneLost);
        for (Lane lane : lanes) {
                sum += lane.performLaneWeaponsAttacks();

        }
        lanes.removeIf(Lane::isLaneLost);
        setResourcesGathered(resourcesGathered+sum);
        score+=sum;
        return sum;
    }
    private int performTitansAttacks() {
        int resources = 0;
        ArrayList<Lane> Lanes = new ArrayList<>();
        ArrayList<Lane> lanesToRemove = new ArrayList<>();

        for (Lane lane : lanes) {
            if (!lane.isLaneLost()) {
                int initialWallHealth = WALL_BASE_HEALTH;
                int resourcesGained = lane.performLaneTitansAttacks();
                if (lane.isLaneLost()) {
                    lanesToRemove.add(lane);
                    resourcesGained += initialWallHealth;
                }
                resources += resourcesGained;
            }
        }

        // Remove lanes marked for removal
        lanes.removeAll(lanesToRemove);
        // Add remaining lanes back to original list
        lanes.addAll(Lanes);
        return resources;
    }

    private void updateLanesDangerLevels() {
        ArrayList<Lane> remainingLanes = new ArrayList<>(lanes.size());

        for (Lane lane : lanes) {
            if (!lane.isLaneLost()) {
                lane.updateLaneDangerLevel();
                remainingLanes.add(lane);
            }
        }

        lanes.removeAll(lanes);// Add the remaining lanes back to the original list without clearing it
        lanes.addAll(remainingLanes);
    }
    private void finalizeTurns(){
       setNumberOfTurns(getNumberOfTurns()+1);
        if (numberOfTurns<15){
           setBattlePhase(BattlePhase.EARLY);
        }
        else if (numberOfTurns<30) {
            setBattlePhase(BattlePhase.INTENSE);
        }
        else if (numberOfTurns>=30){
            setBattlePhase(BattlePhase.GRUMBLING);
            if(numberOfTurns%5==0&&numberOfTurns>30){
                setNumberOfTitansPerTurn(getNumberOfTitansPerTurn()*2);
            }
        }

    }



    private void performTurn(){
        if (!(isGameOver())){
            moveTitans();
            getBattlePhase();
            performWeaponsAttacks();
            performTitansAttacks();
            addTurnTitansToLane();
            updateLanesDangerLevels();
            finalizeTurns();
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
