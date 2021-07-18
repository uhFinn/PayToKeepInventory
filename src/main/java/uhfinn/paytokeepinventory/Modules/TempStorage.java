package uhfinn.paytokeepinventory.Modules;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TempStorage {
    private static HashMap<Player, Float> experience = new HashMap<Player, Float>();
    private static HashMap<Player, ArrayList<ItemStack>> droppedItems = new HashMap<Player, ArrayList<ItemStack>>();
    private static HashMap<Player, Location> deathLoc = new HashMap<Player, Location>();
    public static void Store(Player p, Float xp, ArrayList<ItemStack> DroppedItems, Location loc) {
        experience.put(p, xp);
        droppedItems.put(p, DroppedItems);
        deathLoc.put(p, loc);
    }

    public static Float getExperience(Player p) {
        Float flot = experience.get(p);
        return flot;
    };
    public static List<ItemStack> getDrops(Player p) {
        List<ItemStack> list = droppedItems.get(p);
        return list;
    }
    public static Location getLoc(Player p) {
        Location loc = deathLoc.get(p);
        return loc;
    }

    public static boolean checkPresence(Player p) {
        if(experience.containsValue(p) && droppedItems.containsValue(p) && deathLoc.containsValue(p)) return true;
        return false;
    }

    public static void Remove(Player p) {
        if(experience.containsValue(p)) experience.remove(p);
        if(droppedItems.containsValue(p)) droppedItems.remove(p);
        if(deathLoc.containsValue(p)) deathLoc.remove(p);
    }
}
