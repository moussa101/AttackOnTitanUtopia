package game.engine.titans;

import org.jetbrains.annotations.NotNull;

abstract public class Titan implements Comparable<Titan>{
    public static final int PURE_TITAN_CODE = 1;
    public static final int ABNORMAL_TITAN_CODE = 2;
    public static final int ARMORED_TITAN_CODE = 3;
    public static final int COLOSSAL_TITAN_CODE = 4;
    private int baseHealth;
    private int currentHealth;
    private final int baseDamage;
    private final int heightInMeters;
    private int distanceFromBase;
    private int speed;
    private final int resourcesValue;
    private final int dangerLevel;

    public Titan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.distanceFromBase = distanceFromBase;
        this.speed = speed;
        this.resourcesValue = resourcesValue;
        this.dangerLevel = dangerLevel;
    }




    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getHeightInMeters() {
        return heightInMeters;
    }

    public int getDistanceFromBase() {
        return distanceFromBase;
    }

    public void setDistanceFromBase(int distanceFromBase) {
        this.distanceFromBase = distanceFromBase;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getResourcesValue() {
        return resourcesValue;
    }



    public int getDangerLevel() {
        return dangerLevel;
    }





    @Override
    public int compareTo(@NotNull Titan o) {
        return this.distanceFromBase- o.distanceFromBase;
    }


}


