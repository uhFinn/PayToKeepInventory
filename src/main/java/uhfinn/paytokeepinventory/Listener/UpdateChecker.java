package uhfinn.paytokeepinventory.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import uhfinn.paytokeepinventory.Main;
import uhfinn.paytokeepinventory.Modules.GetConfig;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker implements Listener {
    private String spigotVersion = "0.0";
    private String PluginVersion = ((Main)Main.getPlugin(Main.class)).getDescription().getVersion();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(p.isOp()) {
            if(GetConfig.getConfig().getBoolean("Update Notifications")) {
                int ID = 89515;
                try {
                    final HttpsURLConnection con = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + ID).openConnection();
                    con.setRequestMethod("GET");
                    spigotVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
                } catch (final IOException error) {
                    error.printStackTrace();
                }

                if(spigotVersion != PluginVersion) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
                        public void run() {
                            try {
                                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
                            } catch (NoSuchFieldError e) {
                                //Old versions
                            }
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&2PayToKeepInventory&8] &fVersion " + spigotVersion + " has been released!\nGet it now at: &nhttps://www.spigotmc.org/resources/" + ID));
                        }
                    }, 400L);
                }
            }
        }
    }
}
