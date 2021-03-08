package Listener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
import paytokeepinventory.me;
import org.bukkit.event.Listener;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Death implements Listener {
    List inv;
    String user;
    float xp;

    Inventory restoret = Bukkit.getServer().createInventory(null, 54, "Your Items");


    private final me plugin;

    public Death(me plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        inv = event.getDrops();
        if(restoret.getViewers().size() > 0) {
            restoret.getViewers().get(0).closeInventory();
        }
        restoret.clear();
        int i;
        for(i = 0; i < inv.size(); i++){
            restoret.setItem(i, (ItemStack) inv.get(i));
        }
        user = event.getEntity().getPlayer().getName();
        xp = event.getEntity().getExp();
        if(plugin.getConfig().getBoolean("InstantItemDespawn") == true) {
            event.getDrops().clear();
        }
    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                Player p = Bukkit.getPlayer(event.getPlayer().getUniqueId());
                int cost = plugin.getConfig().getInt("Cost");
                if(p.hasPermission("KeepInv.free") || p.isOp()){
                    cost = 0;
                }
                Inventory inven = Bukkit.getServer().createInventory(null, 27, "Keep Inventory?");
                p.openInventory(inven);
                inven.setItem(1, new ItemStack(Material.EMERALD_BLOCK));
                ItemMeta one = inven.getItem(1).getItemMeta();
                one.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Get Inventory");
                ArrayList lore1 = new ArrayList();
                lore1.add(ChatColor.WHITE + "Get your inventory back, This will cost: " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + cost);
                one.setLore(lore1);
                inven.getItem(1).setItemMeta(one);
                //
                inven.setItem(2, new ItemStack(Material.EMERALD_BLOCK));
                ItemMeta two = inven.getItem(2).getItemMeta();
                two.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Get Inventory");
                ArrayList lore2 = new ArrayList();
                lore2.add(ChatColor.WHITE + "Get your inventory back, This will cost: " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + cost);
                two.setLore(lore2);
                inven.getItem(2).setItemMeta(two);
                //
                inven.setItem(3, new ItemStack(Material.EMERALD_BLOCK));
                ItemMeta three = inven.getItem(3).getItemMeta();
                three.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Get Inventory");
                ArrayList lore3 = new ArrayList();
                lore3.add(ChatColor.WHITE + "Get your inventory back, This will cost: " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + cost);
                three.setLore(lore3);
                inven.getItem(3).setItemMeta(three);
                //
                inven.setItem(10, new ItemStack(Material.EMERALD_BLOCK));
                ItemMeta ten = inven.getItem(10).getItemMeta();
                ten.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Get Inventory");
                ArrayList lore10 = new ArrayList();
                lore10.add(ChatColor.WHITE + "Get your inventory back, This will cost: " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + cost);
                ten.setLore(lore10);
                inven.getItem(10).setItemMeta(ten);
                //
                inven.setItem(11, new ItemStack(Material.EMERALD_BLOCK));
                ItemMeta eleven = inven.getItem(11).getItemMeta();
                eleven.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Get Inventory");
                ArrayList lore11 = new ArrayList();
                lore11.add(ChatColor.WHITE + "Get your inventory back, This will cost: " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + cost);
                eleven.setLore(lore11);
                inven.getItem(11).setItemMeta(eleven);
                //
                inven.setItem(12, new ItemStack(Material.EMERALD_BLOCK));
                ItemMeta twelve = inven.getItem(12).getItemMeta();
                twelve.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Get Inventory");
                ArrayList lore12 = new ArrayList();
                lore12.add(ChatColor.WHITE + "Get your inventory back, This will cost: " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + cost);
                twelve.setLore(lore12);
                inven.getItem(12).setItemMeta(twelve);
                //
                inven.setItem(19, new ItemStack(Material.EMERALD_BLOCK));
                ItemMeta nineteen = inven.getItem(19).getItemMeta();
                nineteen.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Get Inventory");
                ArrayList lore19 = new ArrayList();
                lore19.add(ChatColor.WHITE + "Get your inventory back, This will cost: " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + cost);
                nineteen.setLore(lore19);
                inven.getItem(19).setItemMeta(nineteen);
                //
                inven.setItem(20, new ItemStack(Material.EMERALD_BLOCK));
                ItemMeta twenty = inven.getItem(20).getItemMeta();
                twenty.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Get Inventory");
                ArrayList lore20 = new ArrayList();
                lore20.add(ChatColor.WHITE + "Get your inventory back, This will cost: " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + cost);
                twenty.setLore(lore20);
                inven.getItem(20).setItemMeta(twenty);
                //
                inven.setItem(21, new ItemStack(Material.EMERALD_BLOCK));
                ItemMeta twentyone = inven.getItem(21).getItemMeta();
                twentyone.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Get Inventory");
                ArrayList lore21 = new ArrayList();
                lore21.add(ChatColor.WHITE + "Get your inventory back, This will cost: " + ChatColor.GREEN + "" + ChatColor.BOLD + "$" + cost);
                twentyone.setLore(lore21);
                inven.getItem(21).setItemMeta(twentyone);
                //
                //
                inven.setItem(5, new ItemStack(Material.REDSTONE_BLOCK));
                ItemMeta five = inven.getItem(5).getItemMeta();
                five.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Lose Inventory");
                ArrayList lore5 = new ArrayList();
                lore5.add(ChatColor.WHITE + "You will lose your items forever!");
                five.setLore(lore5);
                inven.getItem(5).setItemMeta(five);
                //
                inven.setItem(6, new ItemStack(Material.REDSTONE_BLOCK));
                ItemMeta six = inven.getItem(6).getItemMeta();
                six.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Lose Inventory");
                ArrayList lore6 = new ArrayList();
                lore6.add(ChatColor.WHITE + "You will lose your items forever!");
                six.setLore(lore6);
                inven.getItem(6).setItemMeta(six);
                //
                inven.setItem(7, new ItemStack(Material.REDSTONE_BLOCK));
                ItemMeta seven = inven.getItem(7).getItemMeta();
                seven.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Lose Inventory");
                ArrayList lore7 = new ArrayList();
                lore7.add(ChatColor.WHITE + "You will lose your items forever!");
                seven.setLore(lore7);
                inven.getItem(7).setItemMeta(seven);
                //
                inven.setItem(14, new ItemStack(Material.REDSTONE_BLOCK));
                ItemMeta fourteen = inven.getItem(14).getItemMeta();
                fourteen.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Lose Inventory");
                ArrayList lore14 = new ArrayList();
                lore14.add(ChatColor.WHITE + "You will lose your items forever!");
                fourteen.setLore(lore14);
                inven.getItem(14).setItemMeta(fourteen);
                //
                inven.setItem(15, new ItemStack(Material.REDSTONE_BLOCK));
                ItemMeta fifteen = inven.getItem(15).getItemMeta();
                fifteen.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Lose Inventory");
                ArrayList lore15 = new ArrayList();
                lore15.add(ChatColor.WHITE + "You will lose your items forever!");
                fifteen.setLore(lore15);
                inven.getItem(15).setItemMeta(fifteen);
                //
                inven.setItem(16, new ItemStack(Material.REDSTONE_BLOCK));
                ItemMeta sixteen = inven.getItem(16).getItemMeta();
                sixteen.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Lose Inventory");
                ArrayList lore16 = new ArrayList();
                lore16.add(ChatColor.WHITE + "You will lose your items forever!");
                sixteen.setLore(lore16);
                inven.getItem(16).setItemMeta(sixteen);
                //
                inven.setItem(23, new ItemStack(Material.REDSTONE_BLOCK));
                ItemMeta twentythree = inven.getItem(23).getItemMeta();
                twentythree.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Lose Inventory");
                ArrayList lore23 = new ArrayList();
                lore23.add(ChatColor.WHITE + "You will lose your items forever!");
                twentythree.setLore(lore23);
                inven.getItem(23).setItemMeta(twentythree);
                //
                inven.setItem(24, new ItemStack(Material.REDSTONE_BLOCK));
                ItemMeta twentyfour = inven.getItem(24).getItemMeta();
                twentyfour.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Lose Inventory");
                ArrayList lore24 = new ArrayList();
                lore24.add(ChatColor.WHITE + "You will lose your items forever!");
                twentyfour.setLore(lore24);
                inven.getItem(24).setItemMeta(twentyfour);
                //
                inven.setItem(25, new ItemStack(Material.REDSTONE_BLOCK));
                ItemMeta twentyfive = inven.getItem(25).getItemMeta();
                twentyfive.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Lose Inventory");
                ArrayList lore25 = new ArrayList();
                lore25.add(ChatColor.WHITE + "You will lose your items forever!");
                twentyfive.setLore(lore25);
                inven.getItem(25).setItemMeta(twentyfive);
                //
            }
        }, 20L);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Economy economy = me.getEconomy();
        Player p = event.getPlayer();
        //p.sendMessage("Balance: " + economy.getBalance(p));
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Economy economy = me.getEconomy();
        UUID id = event.getWhoClicked().getUniqueId();
        Player p = Bukkit.getServer().getPlayer(id);
        double balance = economy.getBalance(p);
            if(event.getWhoClicked().getName() == user) {
                if (event.getCurrentItem() != null) {
                    if (event.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                        if (event.getWhoClicked().getOpenInventory().getTitle() == "Keep Inventory?") {
                            if (event.getInventory().getHolder() == null) {
                                if (event.getWhoClicked().getName() == user) {
                                    if (p.hasPermission("KeepInv.free") || p.isOp() || balance > plugin.getConfig().getInt("Cost")) {
                                        if(p.hasPermission("KeepInv.free") || p.isOp()){

                                        } else {
                                            economy.withdrawPlayer(p, plugin.getConfig().getInt("Cost"));
                                        }
                                        event.getWhoClicked().closeInventory();
                                        event.getWhoClicked().openInventory(restoret);
                                        if(plugin.getConfig().getBoolean("KeepXP") == true){
                                            p.setExp(xp);
                                        }
                                        /*Inventory restore = Bukkit.getServer().createInventory(null, 54, "Your Items | Keep Inventory System");
                                        event.getWhoClicked().closeInventory();
                                        event.getWhoClicked().openInventory(restore);
                                        System.out.println(inv);
                                        int i;
                                        for(i = 0; i < inv.size(); i++){
                                            restore.setItem(i, (ItemStack) inv.get(i));
                                        }*/
                                    } else {
                                        event.getWhoClicked().closeInventory();
                                        event.getWhoClicked().sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Uhh, " + ChatColor.RESET + "" + ChatColor.RED + "Looks like you dont have enough money for that, it costs $" + plugin.getConfig().getInt("Cost") + " to get your inventory back on death");
                                    }
                                } else {

                                    event.getWhoClicked().closeInventory();
                                    event.getWhoClicked().sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Oops, " + ChatColor.RESET + "" + ChatColor.RED + "There seems to have been an error (InventoryCacheLost), If your the owner or an admin please message the plugin developer on spigot!");
                                }
                            }
                        }
                    }
                    if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
                        if (event.getWhoClicked().getOpenInventory().getTitle() == "Keep Inventory?") {
                            if (event.getInventory().getHolder() == null) {
                                event.getWhoClicked().closeInventory();
                            }
                        }
                    }
                }
            }
    }
    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if(event.getInventory().getType() == InventoryType.CHEST) {
            if (event.getPlayer().getOpenInventory().getTitle() == "Your Items") {
                ItemStack a[] = event.getInventory().getContents();
                int length = a.length;

                int i;
                int invSlot = 0;
                int actualNum = 0;
                for (i = 0; i < length; i++) {
                    if(event.getPlayer().getInventory().getItem(invSlot) != null){
                        length = length + 1;
                        invSlot = invSlot + 1;
                    } else {
                        event.getPlayer().getInventory().setItem(invSlot, a[actualNum]);
                        invSlot = invSlot + 1;
                        actualNum = actualNum + 1;
                    }
                }
            }
        }
    }
}


