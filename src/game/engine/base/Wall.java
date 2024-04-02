package game.engine.base;

import game.engine.interfaces.Attackee;

public class Wall implements Attackee {
  private final int baseHealth;
  private int currentHealth;
  private int ResourcesValue =-1;

    public Wall(int baseHealth) {

        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void setCurrentHealth(int health) {
        if (health < 0)
            this.currentHealth = 0;
        else
            this.currentHealth = health;
    }

    @Override
    public int getResourcesValue() {
        return -1;
    }
    }


