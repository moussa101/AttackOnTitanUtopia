package game.engine.interfaces;

public interface Attacker {
    int getDamage();
    default int attack(Attackee target){
        return target.takeDamage(getDamage());
    }
}
