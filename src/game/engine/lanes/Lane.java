package game.engine.lanes;

import game.engine.base.Wall;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Lane implements Comparable<Lane>{
   private final Wall laneWall;
   private int dangerLevel;
   private final PriorityQueue<Titan> titans;
   private final ArrayList<Weapon> weapons;

    public Lane(Wall laneWall) {
        dangerLevel =0;
        this.laneWall = laneWall;
        this.titans = new PriorityQueue<Titan>();
        this.weapons = new ArrayList<Weapon>();
    }


    @Override
    public int compareTo(Lane o) {
        return this.dangerLevel- o.dangerLevel;
    }

    public Wall getLaneWall() {
        return laneWall;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public PriorityQueue<Titan> getTitans() {
        return titans;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }
    public boolean isLaneLost(){
        if (this.laneWall.getCurrentHealth()==0){
            return true;
        }
        else {
            return false;
        }
    }
}
