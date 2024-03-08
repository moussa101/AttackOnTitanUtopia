package game.engine.weapons.factory;

public class WeaponRegistry {
    private int code;
    private int price;
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
}
