package game.engine.weapons;

public class VolleySpreadCannon extends Weapon{
    private final int WEAPON_CODE= VOLLEY_SPREAD_WEAPON_CODE;
   private final int minRange;
   private final int maxRange;

    public VolleySpreadCannon(int baseDamage, int minRange, int maxRange) {
        super(baseDamage);
        this.minRange = minRange;
        this.maxRange = maxRange;
    }
}
