package nightmarenetwork.factionfly.Tasks;

import nightmarenetwork.factionfly.PlayerTerritory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by matias on 31-05-17.
 */
public class TaskFly extends BukkitRunnable {
    private Plugin plugin;
    private Player player;
    private PlayerTerritory playerTerritory;

    public TaskFly(Plugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;

        playerTerritory = new PlayerTerritory(plugin, player);
    }

    @Override
    public void run() {
        // TODO: Esta en su territorio?
        if (playerTerritory.isInOwnTerritory()) {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage("volar es volar");
        }
        else {
            player.setFlying(false);
            player.sendMessage("nope");
        }

        // TODO: Esta en territorio truce?
        // TODO: Esta en territorio aliado?
        // TODO: Esta en serca de un enemigo?
        // TODO: Esta en combate?
    }
}
