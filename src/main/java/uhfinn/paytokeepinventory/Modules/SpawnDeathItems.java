package uhfinn.paytokeepinventory.Modules;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SpawnDeathItems {
    public static void SpawnItems(Location loc, List<ItemStack> items) {
        for(int i = 0; i < items.size(); i++) {
            loc.getWorld().dropItemNaturally(loc, items.get(i));
        }
    }
}
