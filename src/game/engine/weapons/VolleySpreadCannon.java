package game.engine.weapons;

import game.engine.interfaces.Attackee;
import game.engine.titans.Titan;

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
        int totalResources=0;
        for (Titan a:laneTitans){
            if (a.getDistance()>=minRange||a.getDistance()<=maxRange){
                totalResources+=a.takeDamage(getDamage());
                laneTitans.removeIf(Attackee::isDefeated);
            }
        }
        return totalResources;
    }
}
