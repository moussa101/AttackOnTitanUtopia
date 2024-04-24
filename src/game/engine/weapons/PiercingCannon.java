package game.engine.weapons;

import game.engine.interfaces.Attackee;
import game.engine.titans.Titan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PiercingCannon extends Weapon {
    public final static int WEAPON_CODE= PIERCING_CANON_WEAPON_CODE;
    public PiercingCannon(int baseDamage) {
        super(baseDamage);
    }

    @Override
    public int turnAttack(PriorityQueue<Titan> laneTitans) {
        int resourcesGained = 0;
        ArrayList<Titan> remainingTitans = new ArrayList<>(5);

        for (int i = 0; i < 5 && !laneTitans.isEmpty(); i++) {
            Titan titan = laneTitans.poll();


            if (titan != null) {
                int resources = titan.takeDamage(getDamage());

                if (resources > 0) {
                    resourcesGained += resources;
                }

                if (!titan.isDefeated())
                    remainingTitans.add(titan);
            }
        }
        laneTitans.addAll(remainingTitans);
        return resourcesGained;
    }

}

