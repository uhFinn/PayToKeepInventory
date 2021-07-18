package uhfinn.paytokeepinventory.Listener;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import uhfinn.paytokeepinventory.Main;
import uhfinn.paytokeepinventory.Modules.GetConfig;
import uhfinn.paytokeepinventory.Modules.SpawnDeathItems;
import uhfinn.paytokeepinventory.Modules.TempStorage;

import java.util.List;
import java.util.UUID;

public class InventoryEvents implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){
        Economy economy = Main.getEconomy();
        UUID id = event.getWhoClicked().getUniqueId();
        Player p = Bukkit.getServer().getPlayer(id);
        double balance = economy.getBalance(p);
        //if(TempStorage.checkPresence(p)) {
            List<ItemStack> invItems = TempStorage.getDrops(p);
            Float xp = TempStorage.getExperience(p);
            Location deathLoc = TempStorage.getLoc(p);
            if (event.getCurrentItem() != null) {
                if (event.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                    if (event.getWhoClicked().getOpenInventory().getTitle().equals(GetConfig.getConfig().getString("MainGuiTitle"))) {
                        if (event.getInventory().getHolder() == null) {
                            if (p.hasPermission("KeepInv.free") || p.isOp() || balance > GetConfig.getConfig().getInt("Cost")) {
                                if(!p.hasPermission("KeepInv.free") && !p.isOp()) economy.withdrawPlayer(p, GetConfig.getConfig().getInt("Cost"));

                                Inventory restoret = Bukkit.getServer().createInventory(null, 54, GetConfig.getConfig().getString("SecondaryGuiTitle"));
                                for(int i = 0; i < invItems.size(); i++) {
                                    restoret.setItem(i, invItems.get(i));
                                }
                                TempStorage.Remove(p);
                                
                                event.getWhoClicked().closeInventory();
                                if(GetConfig.getConfig().getBoolean("KeepXP")) p.setExp(xp);
                                event.getWhoClicked().openInventory(restoret);
                            } else {
                                event.getWhoClicked().closeInventory();
                                event.getWhoClicked().sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Uhh, " + ChatColor.RESET + "" + ChatColor.RED + "Looks like you dont have enough money for that, it costs $" + GetConfig.getConfig().getInt("Cost") + " to get your inventory back on death");
                            }
                        } else {

                            event.getWhoClicked().closeInventory();
                            event.getWhoClicked().sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Oops, " + ChatColor.RESET + "" + ChatColor.RED + "There seems to have been an error (InventoryCacheLost), If your the owner or an admin please message the plugin developer on spigot!");
                        }
                    }
                }
                if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
                    if (event.getWhoClicked().getOpenInventory().getTitle().equals(GetConfig.getConfig().getString("MainGuiTitle"))) {
                        if (event.getInventory().getHolder() == null) {
                            event.getWhoClicked().closeInventory();
                            SpawnDeathItems.SpawnItems(deathLoc, invItems);
                            TempStorage.Remove(p);
                        }
                    }
                }
            }
        //}
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if(event.getInventory().getType() == InventoryType.CHEST) {
            if(event.getInventory().getHolder() == null) {
                if (event.getPlayer().getOpenInventory().getTitle().equals(GetConfig.getConfig().getString("SecondaryGuiTitle"))) {
                    ItemStack a[] = event.getInventory().getContents();
                    int length = a.length;

                    int i;
                    int invSlot = 0;
                    int actualNum = 0;
                    for (i = 0; i < length; i++) {
                        if (event.getPlayer().getInventory().getItem(invSlot) != null) {
                            length = length + 1;
                            invSlot = invSlot + 1;
                        } else {
                            event.getPlayer().getInventory().setItem(invSlot, a[actualNum]);
                            invSlot = invSlot + 1;
                            actualNum = actualNum + 1;
                        }
                    }
                } else if (event.getPlayer().getOpenInventory().getTitle().equals(GetConfig.getConfig().getString("MainGuiTitle"))) {
                    Player p = (Player) event.getPlayer();
                    if(p.getOpenInventory() == null) {
                        List<ItemStack> invItems = TempStorage.getDrops(p);
                        Location deathLoc = TempStorage.getLoc(p);
                        TempStorage.Remove(p);

                        SpawnDeathItems.SpawnItems(deathLoc, invItems);
                    }
                }
            }
        }
    }
}
