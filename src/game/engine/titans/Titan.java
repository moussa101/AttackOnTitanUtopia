package game.engine.titans;

import game.engine.interfaces.*;

public abstract class Titan implements Comparable<Titan>, Attacker, Attackee, Mobil {

    private final int baseDamage;
    private final int heightInMeters;
    private final int resourcesValue;
    private final int dangerLevel;
    private final int baseHealth;
    private int currentHealth;
    private int distanceFromBase;
    private int speed;

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        if (currentHealth < 0)
            this.currentHealth = 0;
        else
            this.currentHealth = currentHealth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getHeightInMeters() {
        return heightInMeters;
    }

    public int getResourcesValue() {
        return resourcesValue;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public int getDamage() {
        return baseDamage;
    }

    @Override
    public int getDistance() {
        return distanceFromBase;
    }

    @Override
    public void setDistance(int distance) {
        if (distance < 0)
            this.distanceFromBase = 0;
        else
            this.distanceFromBase = distance;
    }

    public Titan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
                 int resourcesValue, int dangerLevel) {
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.distanceFromBase = distanceFromBase;
        this.speed = speed;
        this.resourcesValue = resourcesValue;
        this.dangerLevel = dangerLevel;
        this.currentHealth = baseHealth;
    }

    public int compareTo(Titan arg0) {

        return (this.distanceFromBase - arg0.distanceFromBase);

    };
}


