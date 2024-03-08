package game.engine.base;

public class Wall {
  private final int baseHealth;
  private int currentHealth;

    public Wall(int baseHealth) {
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
}
