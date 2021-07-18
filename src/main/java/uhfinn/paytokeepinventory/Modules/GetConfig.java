package uhfinn.paytokeepinventory.Modules;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import uhfinn.paytokeepinventory.Main;
import java.io.File;
import java.io.IOException;

public class GetConfig {

    private static FileConfiguration Config = YamlConfiguration.loadConfiguration(new File(Main.INSTANCE().getDataFolder() + "/Config.yml"));

    public static FileConfiguration getConfig() {
        return Config;
    }

    public static String Reload() {

        File Configur = new File(Main.INSTANCE().getDataFolder() + "/Config.yml");
        if(!Configur.exists()) {
            Main.getPlugin(Main.class).saveResource("Config.yml", false);
            System.out.println("[PayToKeepInventory] Generated Config.yml File");
        }

        Config = YamlConfiguration.loadConfiguration(new File(Main.INSTANCE().getDataFolder() + "/Config.yml"));

        return ChatColor.translateAlternateColorCodes('&', "&8[&2PayToKeepInventory&8] &fReloaded config.yml!");
    }
}
