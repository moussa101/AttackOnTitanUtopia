package game.engine.base;

import game.engine.interfaces.Attackee;

public class Wall implements Attackee {
  private final int baseHealth;
  private int currentHealth;

    public Wall(int baseHealth) {
        if (baseHealth<0)
            baseHealth =0;
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    @Override
    public int getResourcesValue() {
        return 0;
    }
}
