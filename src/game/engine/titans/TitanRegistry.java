package game.engine.titans;

public class TitanRegistry {

   private final int code;
   private final int baseHealth;
   private final int  baseDamage;
   private final int heightInMeters;
   private final int speed;
   private final int resourcesValue;
   private final int dangerLevel;

    public TitanRegistry(int code, int baseHealth, int baseDamage, int heightInMeters, int speed, int resourcesValue, int dangerLevel) {
        this.code = code;
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.speed = speed;
        this.resourcesValue = resourcesValue;
        this.dangerLevel = dangerLevel;
    }
}
