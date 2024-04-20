package game.engine.titans;

import game.engine.interfaces.Mobil;

public class ColossalTitan extends Titan {
    public final static int TITAN_CODE = 4;


    public ColossalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
        super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
    }

    @Override
    public void setDistance(int distance) {
        super.setDistance(distance);
        super.setSpeed(getSpeed()+1);
    }

    @Override
    public boolean move() {
        return super.move();
    }
}
