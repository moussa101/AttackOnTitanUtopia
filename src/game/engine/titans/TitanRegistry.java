package game.engine.titans;

public class TitanRegistry{

   private final int code;
   private int baseHealth;
   private int  baseDamage;
   private int heightInMeters;
   private int speed;
   private int resourcesValue;
   private int dangerLevel;

    public TitanRegistry(int code, int baseHealth, int baseDamage, int heightInMeters, int speed, int resourcesValue, int dangerLevel) {
        this.code = code;
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.speed = speed;
        this.resourcesValue = resourcesValue;
        this.dangerLevel = dangerLevel;
    }

    public int getCode() {
        return code;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getHeightInMeters() {
        return heightInMeters;
    }

    public int getSpeed() {
        return speed;
    }

    public int getResourcesValue() {
        return resourcesValue;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public Titan spawnTitan(int distanceFromBase){
        Titan a;
        switch (getCode()){
            case 1: a = new PureTitan(getBaseHealth(),getBaseDamage(),getHeightInMeters(),distanceFromBase,getSpeed(),getResourcesValue(),getDangerLevel()); break;
            
            case 2: a = new AbnormalTitan(getBaseHealth(),getBaseDamage(),getHeightInMeters(),distanceFromBase,getSpeed(),getResourcesValue(),getDangerLevel()); break;
            
            case 3: a = new ArmoredTitan (getBaseHealth(),getBaseDamage(),getHeightInMeters(),distanceFromBase,getSpeed(),getResourcesValue(),getDangerLevel());break;
            
            case 4: a = new ColossalTitan(getBaseHealth(),getBaseDamage(),getHeightInMeters(),distanceFromBase,getSpeed(),getResourcesValue(),getDangerLevel());break;

            default:
                a =null;
        }
        return a;
    }
}
