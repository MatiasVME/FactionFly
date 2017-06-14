package nightmarenetwork.factionfly.Tasks;

import nightmarenetwork.factionfly.Config.Config;
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
    private Player player;
    private Config config;

    private PlayerTerritory playerTerritory;
    private PlayerUtilities playerUtilities;

    private boolean isEnableEnemyNear;
    private int enemyNearBlocks;

    public TaskFly(Plugin plugin, Player player, Config config) {
        this.plugin = plugin;
        this.player = player;
        this.config = config;

        playerTerritory = new PlayerTerritory(plugin, player, config);
        playerUtilities = new PlayerUtilities(plugin, player);

        isEnableEnemyNear = config.isEnemyNearEnable();
        enemyNearBlocks = config.getEnemyNearBlocks();
    }

    @Override
    public void run() {
        if (player.isOp())
            return;

        boolean isAllowFlying = player.getAllowFlight();
        boolean isEnemyNearby = false;

        // Esta en cerca de un enemigo?
        if (isEnableEnemyNear) {
            isEnemyNearby = playerUtilities.isEnemyNearby(enemyNearBlocks);

            if (isEnemyNearby) {
                if (isAllowFlying) {
                    player.setAllowFlight(false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            config.getNotFliyingEnemyNear()));
                }
            }
        }


        // Esta en su territorio?
        if (player.hasPermission("ffly.own") && playerTerritory.isInOwnTerritory()
                && !isEnemyNearby) {
            if (!isAllowFlying) {
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        config.getFliyingOwn()));
            }
        }

        // Esta en territorio truce?
        else if (player.hasPermission("ffly.truce") && playerTerritory.isInTruceTerritory()) {
            if (!isAllowFlying) {
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        config.getFliyingTruce()));
            }
        }

        // Esta en territorio aliado?
        else if (player.hasPermission("ffly.ally") && playerTerritory.isInAllyTerritory()) {
            if (!isAllowFlying) {
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        config.getFliyingAlly()));
            }
        }

        // TODO: Esta en combate?

        else {
            if (isAllowFlying && !isEnemyNearby) {
                player.setAllowFlight(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        config.getNotFliying()));
            }
        }
    }
}
