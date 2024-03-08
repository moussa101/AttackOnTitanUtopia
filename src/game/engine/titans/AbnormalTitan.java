package game.engine.titans;

public class AbnormalTitan extends Titan{

    final int TITAN_CODE = ABNORMAL_TITAN_CODE;

    public AbnormalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
    }
}

