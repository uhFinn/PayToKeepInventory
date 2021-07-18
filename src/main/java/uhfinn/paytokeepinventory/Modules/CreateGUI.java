package uhfinn.paytokeepinventory.Modules;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uhfinn.paytokeepinventory.Main;

import java.util.ArrayList;

public class CreateGUI {
    public static boolean ChoicGUI(Player p) {
        FileConfiguration config = GetConfig.getConfig();
        System.out.println(config.getString("MainGuiTitle"));
        System.out.println(config.getString("SecondaryGuiTitle"));
        System.out.println(config.getInt("Cost"));
        Inventory inven = Bukkit.getServer().createInventory(null, 27, config.getString("MainGuiTitle"));
        Boolean retrieve = config.getBoolean("AllowSelfRecovery");

        FileConfiguration configuration = GetConfig.getConfig();

        String retrieveString = ChatColor.translateAlternateColorCodes('&', PlaceHolderReplacement.replace(configuration.getString("LoseInventory.Title"), p));
        String retrieveCaption = ChatColor.translateAlternateColorCodes('&', PlaceHolderReplacement.replace(configuration.getString("LoseInventory.SubText"), p));
        if(retrieve){
            retrieveString = ChatColor.translateAlternateColorCodes('&', PlaceHolderReplacement.replace(configuration.getString("RetrieveInventory.Title"), p));
            retrieveCaption = ChatColor.translateAlternateColorCodes('&', PlaceHolderReplacement.replace(configuration.getString("RetrieveInventory.SubText"), p));
        }

        p.openInventory(inven);

        for(int i = 0; i < 27; i++) {
            int[] KeepInv = {1,2,3,10,11,12,19,20,21};
            int[] LoseRetrieveInv = {5,6,7,14,15,16,23,24,25};
            for(int e = 0; e < KeepInv.length; e++) {
                if(i == KeepInv[e]) {
                    inven.setItem(i, new ItemStack(Material.EMERALD_BLOCK));
                    ItemMeta itemMeta = inven.getItem(i).getItemMeta();
                    itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', PlaceHolderReplacement.replace(configuration.getString("SaveInventory.Title"), p)));
                    ArrayList lore = new ArrayList();
                    lore.add(ChatColor.translateAlternateColorCodes('&', PlaceHolderReplacement.replace(configuration.getString("SaveInventory.SubText"), p)));
                    itemMeta.setLore(lore);
                    inven.getItem(i).setItemMeta(itemMeta);
                }
            }
            for(int e = 0; e < LoseRetrieveInv.length; e++) {
                if(i == LoseRetrieveInv[e]) {
                    inven.setItem(i, new ItemStack(Material.REDSTONE_BLOCK));
                    ItemMeta itemMeta = inven.getItem(i).getItemMeta();
                    itemMeta.setDisplayName(retrieveString);
                    ArrayList lore = new ArrayList();
                    lore.add(retrieveCaption);
                    itemMeta.setLore(lore);
                    inven.getItem(i).setItemMeta(itemMeta);
                }
            }
        }
        return false;
    }
}
