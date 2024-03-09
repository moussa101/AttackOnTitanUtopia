package game.engine.weapons;

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
}
