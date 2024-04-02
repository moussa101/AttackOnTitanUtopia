package game.engine.dataloader;

import java.io.*;
import java.io.IOException;
import java.util.HashMap;

import game.engine.titans.TitanRegistry;
import game.engine.weapons.WeaponRegistry;

public class DataLoader {
    private final static String TITANS_FILE_NAME = "titans.csv";
    private final static String WEAPONS_FILE_NAME = "weapons.csv";

    public static HashMap<Integer, TitanRegistry> readTitanRegistry() throws IOException {
        // code, baseHealth, baseDamage, heightInMeters, speed, resourcesValue,
        // dangerLevel.
        BufferedReader br = new BufferedReader(new FileReader(TITANS_FILE_NAME));
        HashMap<Integer, TitanRegistry> AllTitans = new HashMap<Integer, TitanRegistry>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            int code = Integer.parseInt(data[0]);
            int baseHealth = Integer.parseInt(data[1]);
            int baseDamage = Integer.parseInt(data[2]);
            int heightInMeters = Integer.parseInt(data[3]);
            int speed = Integer.parseInt(data[4]);
            int resourcesValue = Integer.parseInt(data[5]);
            int dangerLevel = Integer.parseInt(data[6]);
            TitanRegistry tr = new TitanRegistry(code, baseHealth, baseDamage, heightInMeters, speed, resourcesValue,
                    dangerLevel);
            AllTitans.put(code, tr);
        }
        return AllTitans;
    }

    public static HashMap<Integer, WeaponRegistry> readWeaponRegistry() throws IOException {
        // {code, price, damage, name} // or //{code,price, damage, name, minRange,
        // maxRange}
        BufferedReader br = new BufferedReader(new FileReader(WEAPONS_FILE_NAME));
        HashMap<Integer, WeaponRegistry> AllWeapons = new HashMap<Integer, WeaponRegistry>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            int code = Integer.parseInt(data[0]);
            int price = Integer.parseInt(data[1]);
            int damage = Integer.parseInt(data[2]);
            String name = data[3];
            if (data.length == 6) {
                int minRange = Integer.parseInt(data[4]);
                int maxRange = Integer.parseInt(data[5]);
                WeaponRegistry wr = new WeaponRegistry(code, price, damage, name, minRange, maxRange);
                AllWeapons.put(code, wr);
            } else {
                WeaponRegistry wr = new WeaponRegistry(code, price, damage, name);
                AllWeapons.put(code, wr);
            }

        }
        return AllWeapons;
    }

}