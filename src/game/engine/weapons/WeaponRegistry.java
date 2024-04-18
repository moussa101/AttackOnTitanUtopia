package game.engine.weapons;

import game.engine.weapons.factory.WeaponFactory;

public class WeaponRegistry {
    private final int code;
    private final int price;
    private int damage;
    private String name;
    private int minRange;
    private int maxRange;

    public WeaponRegistry(int code, int price) {
        this.code = code;
        this.price = price;
    }

    public WeaponRegistry(int code, int price, int damage, String name) {
        this.code = code;
        this.price = price;
        this.damage = damage;
        this.name = name;
    }

    public WeaponRegistry(int code, int price, int damage, String name, int minRange, int maxRange) {
        this.code = code;
        this.price = price;
        this.damage = damage;
        this.name = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public int getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }


    public String getName() {
        return name;
    }

    public int getMinRange() {
        return minRange;
    }


    public int getMaxRange() {
        return maxRange;
    }

    public Weapon buildWeapon(){
        Weapon a;
        switch (getCode()){
            case 1 : a = new PiercingCannon(getDamage());

            case 2 : a = new SniperCannon(getDamage());

            case 3 : a = new VolleySpreadCannon(getDamage(),getMinRange(),getMaxRange());

            case 4 : a = new WallTrap(getDamage());

            default: a = null;
        }
      return a;
    }


}
