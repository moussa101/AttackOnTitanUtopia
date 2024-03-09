package game.engine.titans;

public class ArmoredTitan extends Titan{
    public final static int TITAN_CODE = ARMORED_TITAN_CODE;

    public ArmoredTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
    }
}
