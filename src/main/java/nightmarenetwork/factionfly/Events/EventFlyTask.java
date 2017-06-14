package nightmarenetwork.factionfly.Events;

import nightmarenetwork.factionfly.Config.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import nightmarenetwork.factionfly.Tasks.TaskFly;

/**
 * Created by matias on 31-05-17.
 */

// Activa o desactiva la tarea de fly

public class EventFlyTask implements Listener {
    private Plugin plugin;
    private Config config;

    private TaskFly taskFly;

    public EventFlyTask(Plugin plugin, Config config) {
        this.plugin = plugin;
        this.config = config;
    }

    @EventHandler
    public void OnJoinEvent(PlayerJoinEvent event) {
        taskFly = new TaskFly(plugin, event.getPlayer(), config);
        taskFly.runTaskTimer(plugin, 0, 20 * config.getFlyCheck());
    }

    @EventHandler
    public  void OnQuitEvent(PlayerQuitEvent event) {
        if (taskFly != null)
            taskFly.cancel();
    }

    @EventHandler
    public void OnTeleportEvent(PlayerTeleportEvent event) {
        if (taskFly != null)
            taskFly.cancel();

        taskFly = new TaskFly(plugin, event.getPlayer(), config);
        taskFly.runTaskTimer(plugin, 0, 20 * config.getFlyCheck());
    }
}
