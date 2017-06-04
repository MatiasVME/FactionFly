package nightmarenetwork.factionfly.Tasks;

import nightmarenetwork.factionfly.PlayerTerritory;
import nightmarenetwork.factionfly.Utilities.PlayerUtilities;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by matias on 31-05-17.
 */
public class TaskFly extends BukkitRunnable {
    private Plugin plugin;
    private Player players[];

    public TaskFly(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        players = plugin.getServer().getOnlinePlayers().toArray(new Player[0]);

        for (Player player : players) {
            if (player.isOp())
                continue;

            PlayerUtilities playerUtilities = new PlayerUtilities(plugin, player);
            PlayerTerritory playerTerritory = new PlayerTerritory(plugin, player);

            boolean isAllowFlying = player.getAllowFlight();

            // boolean isDisableEnemyNear = plugin.getConfig().getBoolean("disable-enemy-near.enable");

            // Esta en cerca de un enemigo?
            boolean isEnemyNearby = playerUtilities.isEnemyNearby(
                    plugin.getConfig().getInt("disable-enemy-near.blocks")
            );

            if (isEnemyNearby) {
                if (isAllowFlying) {
                    player.setAllowFlight(false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("messages.not-flying-enemy-near")));
                }
            }


            // Esta en su territorio?
            else if (player.hasPermission("ffly.own") && playerTerritory.isInOwnTerritory()) {
                if (!isAllowFlying) {
                    player.setAllowFlight(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("messages.flying-own")));
                }
            }

            // Esta en territorio truce?
            else if (player.hasPermission("ffly.truce") && playerTerritory.isInTruceTerritory()) {
                if (!isAllowFlying) {
                    player.setAllowFlight(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("messages.flying-truce")));
                }
            }

            // Esta en territorio aliado?
            else if (player.hasPermission("ffly.ally") && playerTerritory.isInAllyTerritory()) {
                if (!isAllowFlying) {
                    player.setAllowFlight(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("messages.flying-ally")));
                }
            }

            // TODO: Esta en combate?

            else {
                if (isAllowFlying) {
                    player.setAllowFlight(false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("messages.not-flying")));
                }
            }
        }
    }
}
