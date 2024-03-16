package game.engine.dataloader;

import game.engine.titans.TitanRegistry;
import game.engine.weapons.Weapon;
import game.engine.weapons.WeaponRegistry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DataLoader {
    private final static String TITANS_FILE_NAME ="titans.csv";
    private final static String  WEAPONS_FILE_NAME = "weapons.csv";

    public static HashMap<Integer, TitanRegistry> readTitanRegistry() throws IOException{
        HashMap<Integer, TitanRegistry> hash = new HashMap<Integer, TitanRegistry>();
        FileReader b = new FileReader(TITANS_FILE_NAME);
        BufferedReader a = new BufferedReader(b);
        String c = a.readLine();
        String [] line;
        while(c != null) {
            line = c.split(",");
            int code = Integer.parseInt(line[0]);
            int baseHealth = Integer.parseInt(line[1]);
            int baseDamage = Integer.parseInt(line[2]);
            int heigthMeters = Integer.parseInt(line[3]);
            int speed = Integer.parseInt(line[4]);
            int resourceValue = Integer.parseInt(line[5]);
            int dangerLevel = Integer.parseInt(line[6]);
            TitanRegistry d = new TitanRegistry(code, baseHealth, baseDamage, heigthMeters, speed, resourceValue, dangerLevel);
            hash.put(code, d);
            c = a.readLine();
        }
        a.close();
        return hash;
    }
    public static HashMap<Integer, WeaponRegistry> readWeaponRegistry() throws IOException{
        HashMap<Integer, WeaponRegistry> hash = new HashMap<Integer, WeaponRegistry>();
        FileReader b = new FileReader(WEAPONS_FILE_NAME);
        BufferedReader a = new BufferedReader(b);
        String c = a.readLine();
        String [] line;
        while(c != null) {
            WeaponRegistry d;
            line = c.split(",");
            int code = Integer.parseInt(line[0]);
            int price = Integer.parseInt(line[1]);
            int damage = Integer.parseInt(line[2]);
            String name = (line[3]);
            if (line.length == 6) {
                int minRange = Integer.parseInt(line[4]);
                int maxRange = Integer.parseInt(line[5]);
                d = new WeaponRegistry(code, price, damage, name, minRange, maxRange);
            } else {
                d = new WeaponRegistry(code, price, damage, name);
            }
            hash.put(code,d);
            c = a.readLine();
        }

        a.close();
        return hash;

    }


}
