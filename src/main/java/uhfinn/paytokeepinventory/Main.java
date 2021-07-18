package uhfinn.paytokeepinventory;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import uhfinn.paytokeepinventory.Listener.*;
import uhfinn.paytokeepinventory.Modules.GetConfig;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy economy = null;

    private static Main _instance;
    public static Main INSTANCE()
    {
        return _instance;
    }

    @Override
    public void onEnable() {

        _instance=this;
        addListener(new Death());
        addListener(new InventoryEvents());
        addListener(new UpdateChecker());

        // Plugin startup logic

        System.out.println("[PayToKeepInventory] Loading Plugin...");
        System.out.println("[PayToKeepInventory] Checking For Vault");
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            System.out.println("[PayToKeepInventory] Vault Not Found!");
            System.out.println("[PayToKeepInventory] Please Install Vault (https://www.spigotmc.org/resources/vault.34315/)");
            System.out.println("[PayToKeepInventory] For Usability Purposes, We Also Recommend Installing Essentials. It Is However Not Required For The Plugin SO We Did Not Check For It");
            System.out.println("[PayToKeepInventory] This Plugin Can Not Run Without Vault... Shutting Down");
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            System.out.println("[PayToKeepInventory] Vault Found, Continuing With Setup!");

            GetConfig.Reload();

            System.out.println("[PayToKeepInventory] Loaded Plugin!");

            //setupEconomy();
            //System.out.println(economy);

            if (!setupEconomy() ) {
                System.out.println("Disabled due to no Vault dependency found!");
                getServer().getPluginManager().disablePlugin(this);
                return;
            }
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
                sender.sendMessage(GetConfig.Reload());
            }
        }
        return false;
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void addListener(Listener listener)
    {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
}
