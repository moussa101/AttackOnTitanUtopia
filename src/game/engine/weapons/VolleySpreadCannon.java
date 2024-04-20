package game.engine.weapons;

import game.engine.interfaces.Attackee;
import game.engine.titans.Titan;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class VolleySpreadCannon extends Weapon{
    public final static int WEAPON_CODE= VOLLEY_SPREAD_WEAPON_CODE;
   private final int minRange;
   private final int maxRange;

    public VolleySpreadCannon(int baseDamage, int minRange, int maxRange) {
        super(baseDamage);
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    @Override
    public int turnAttack(PriorityQueue<Titan> laneTitans) {
        int totalResources = 0;
        List<Titan> defeatedTitans = new ArrayList<>();
        for (Titan titan : laneTitans) {
            if (titan.getDistance() >= minRange && titan.getDistance() <= maxRange) {
                totalResources += titan.takeDamage(getDamage());
                if (titan.isDefeated()) {
                    defeatedTitans.add(titan);
                }
            }
        }
        laneTitans.removeAll(defeatedTitans);
        return totalResources;
    }
}
