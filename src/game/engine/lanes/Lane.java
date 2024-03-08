package game.engine.lanes;

import game.engine.base.Wall;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Lane implements Comparable<Lane>{
   private final Wall laneWall;
   private int dangerLevel =0;
   private final PriorityQueue<Titan> titans = new PriorityQueue<Titan>();
   private final ArrayList<Weapon> weapons = new ArrayList<Weapon>();

    public Lane(Wall laneWall) {
        this.laneWall = laneWall;
    }


    @Override
    public int compareTo(@NotNull Lane o) {
        return this.dangerLevel- o.dangerLevel;
    }
}
