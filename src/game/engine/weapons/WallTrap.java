package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class WallTrap extends Weapon {
   public final static int WEAPON_CODE= WALL_TRAP_WEAPON_CODE;
    public WallTrap(int baseDamage) {
        super(baseDamage);
    }

    @Override
    int turnAttack(PriorityQueue<Titan> laneTitans) {
        int totalResources =0;
        totalResources = laneTitans.peek().takeDamage(getDamage());
        laneTitans.removeIf(Titan::isDefeated);
        return totalResources;
    }
}
