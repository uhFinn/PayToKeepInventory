package paytokeepinventory;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import Listener.Death;
import java.util.logging.Logger;

public final class me extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy economy = null;

    @Override
    public void onEnable() {

        // Plugin startup logic

        System.out.println("[PayToKeepInventory] Loading Plugin...");
        System.out.println("[PayToKeepInventory] Checking For Vault");
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            System.out.println("[PayToKeepInventory] Vault Not Found!");
            System.out.println("[PayToKeepInventory] Please Install Vault (https://www.spigotmc.org/resources/vault.34315/)");
            System.out.println("[PayToKeepInventory] For Usability Purposes, We Also Recommend Installing Essentials. It Is However Not Required For The Plugin SO We Did Not Check For It");
            System.out.println("[PayToKeepInventory] This Plugin Can Not Run Without Vault... Shutting Down");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        System.out.println("[PayToKeepInventory] Vault Found, Continuing With Setup!");
        new Death(this);

        FileConfiguration config = getConfig();
        config.addDefault("Cost", 1000);
        config.addDefault("KeepXP", true);
        config.addDefault("InstantItemDespawn", true);
        config.options().copyDefaults(true);
        saveConfig();

        System.out.println("[PayToKeepInventory] Generated Config Files");


        System.out.println("[PayToKeepInventory] Loaded Plugin!");

        setupEconomy();
        System.out.println(economy);

        if (!setupEconomy() ) {
            System.out.println("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public static Economy getEconomy() {
        return economy;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("KeepInv-reload")){
            if(sender.hasPermission("KeepInv.reload") || sender.isOp()) {
                reloadConfig();
                saveConfig();
                sender.sendMessage("[PayToKeepInventory] Reloaded config.yml!");
            }
        }
        return false;
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
