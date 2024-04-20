package game.engine.weapons;

import game.engine.interfaces.Attackee;
import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class PiercingCannon extends Weapon {
    public final static int WEAPON_CODE= PIERCING_CANON_WEAPON_CODE;
    public PiercingCannon(int baseDamage) {
        super(baseDamage);
    }

    @Override
    public int turnAttack(PriorityQueue<Titan> laneTitans) {
        int totalResources =0;
        int n = Math.min(laneTitans.size(),5);
        PriorityQueue<Titan> a = new PriorityQueue<Titan>();
            for (int i = 0; i <n ; i++) {
              totalResources+=laneTitans.peek().takeDamage(super.getDamage());
              if (laneTitans.peek().isDefeated()){
                  laneTitans.remove();
              }
              else {
                  a.add(laneTitans.poll());
              }

        }
            laneTitans = a;
        return totalResources;
    }
}
