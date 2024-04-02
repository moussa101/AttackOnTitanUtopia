package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class SniperCannon extends Weapon {
    public final static int WEAPON_CODE= SNIPER_CANNON_WEAPON_CODE;
    public SniperCannon(int baseDamage) {
        super(baseDamage);
    }

    @Override
    public int turnAttack(PriorityQueue<Titan> laneTitans) {
        int totalResources = laneTitans.peek().takeDamage(getDamage());
        laneTitans.removeIf(Titan::isDefeated);
        return totalResources;
    }
}
