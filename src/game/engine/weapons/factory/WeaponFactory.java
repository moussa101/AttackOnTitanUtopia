package game.engine.weapons.factory;

import game.engine.weapons.WeaponRegistry;

import java.io.IOException;
import java.util.HashMap;

public class WeaponFactory {
    private final HashMap<Integer, WeaponRegistry> weaponShop;

    public WeaponFactory(HashMap<Integer, WeaponRegistry> weaponShop) {
        this.weaponShop = weaponShop;
    }

}
