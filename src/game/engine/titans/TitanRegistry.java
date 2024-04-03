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
            case 1: a = new PureTitan(1,100,15,distanceFromBase,10,10,1); break;
            
            case 2: a = new AbnormalTitan(2,100,20,distanceFromBase,15,15,2); break;
            
            case 3: a = new ArmoredTitan(3,200,85,distanceFromBase,10,30,3);break;
            
            case 4: a = new ColossalTitan(4,1000,100,distanceFromBase,5,60,4);break;

            default:
                throw new IllegalStateException("Unexpected value: " + getCode());
        }
        return a;
    }
}
