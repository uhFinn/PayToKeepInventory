package uhfinn.paytokeepinventory.Listener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uhfinn.paytokeepinventory.Main;
import org.bukkit.event.Listener;
import net.milkbowl.vault.economy.Economy;
import uhfinn.paytokeepinventory.Modules.CreateGUI;
import uhfinn.paytokeepinventory.Modules.GetConfig;
import uhfinn.paytokeepinventory.Modules.TempStorage;


import java.util.*;


public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Float xp = event.getEntity().getExp();
        Player p = event.getEntity().getPlayer();
        ArrayList<ItemStack> droppedItems = new ArrayList<>(event.getDrops());
        TempStorage.Store(p, xp, droppedItems, p.getLocation());
        event.getDrops().clear();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
            public void run() {
                CreateGUI.ChoicGUI(event.getPlayer());
            }
        }, 20L);
    }

    /*@EventHandler
    public void onMove(PlayerMoveEvent event){
        Economy economy = Main.getEconomy();
        Player p = event.getPlayer();
        //p.sendMessage("Balance: " + economy.getBalance(p));
    }*/

}


