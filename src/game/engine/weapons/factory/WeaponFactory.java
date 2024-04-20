package game.engine.weapons.factory;

import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.weapons.WeaponRegistry;

import java.io.IOException;
import java.util.HashMap;

public class WeaponFactory {
    private final HashMap<Integer, WeaponRegistry> weaponShop;

    public WeaponFactory() throws IOException {
        this.weaponShop = DataLoader.readWeaponRegistry();
    }

    public HashMap<Integer, WeaponRegistry> getWeaponShop() {
        return weaponShop;
    }
   public FactoryResponse buyWeapon(int resources, int weaponCode) throws InsufficientResourcesException {

        int Weaponprice =  weaponShop.get(weaponCode).getPrice();
        if(resources < Weaponprice){
            throw new InsufficientResourcesException("Insufficent resources to purchase a weapon" , resources);
        }
        else{
            int remainingResources = resources - Weaponprice;
            WeaponRegistry a = weaponShop.get(weaponCode);

            return new FactoryResponse(a.buildWeapon(), remainingResources);
        }

}

}
