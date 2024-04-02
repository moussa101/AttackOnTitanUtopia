package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.PriorityQueue;

abstract public class Weapon {
    public static final int PIERCING_CANON_WEAPON_CODE= 1;
    public static final int SNIPER_CANNON_WEAPON_CODE= 2;
    public static final int VOLLEY_SPREAD_WEAPON_CODE= 3;
    public static final int WALL_TRAP_WEAPON_CODE= 4;
   private final int baseDamage;

    public Weapon(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getDamage() {
        return baseDamage;
    }
    abstract int turnAttack(PriorityQueue<Titan> laneTitans);

}
