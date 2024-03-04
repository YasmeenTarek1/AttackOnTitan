package game.engine.dataloader;

import game.engine.titans.TitanRegistry;
import game.engine.weapons.WeaponRegistry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class DataLoader {
    private static final String TITANS_FILE_NAME = "titans.csv";   // Name of the titan’s csv file
    private static final String WEAPONS_FILE_NAME = "weapons.csv"; // Name of the weapon’s csv file.

    public static HashMap<Integer, WeaponRegistry> readWeaponRegistry() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("weapons.csv"));
        String line = null;
        HashMap<Integer,WeaponRegistry> map = new HashMap();
        while( (line = br.readLine()) != null){
            String[] str = line.split(",");
            WeaponRegistry w;
            String name = str[3];
            if(name.equals("VolleySpreadCannon"))
                w = new WeaponRegistry(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), str[3], Integer.parseInt(str[4]), Integer.parseInt(str[5]));
            else
                w = new WeaponRegistry(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), str[3]);
            map.put(Integer.parseInt(str[0]) , w); // Mapping the Weapon Registry to its Weapon Type
        }
        return map;
    }
    public static HashMap<Integer, TitanRegistry> readTitanRegistry() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("titans.csv"));
        String line = null;
        HashMap<Integer, TitanRegistry> map = new HashMap();
        while ((line = br.readLine()) != null) {
            String[] str = line.split(",");
            TitanRegistry w = new TitanRegistry(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]), Integer.parseInt(str[5]), Integer.parseInt(str[5]));
            map.put(Integer.parseInt(str[0]), w);
        }
        return map;
    }

    public String getTITANS_FILE_NAME() {
        return TITANS_FILE_NAME;
    }
    public String getWEAPONS_FILE_NAME() {
        return WEAPONS_FILE_NAME;
    }
}