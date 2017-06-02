package nightmarenetwork.factionfly.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import nightmarenetwork.factionfly.Tasks.TaskFly;

/**
 * Created by matias on 31-05-17.
 */

// Activa o desactiva la tarea de fly

public class EventFlyTask implements Listener {
    private Plugin plugin;

    private TaskFly taskFly;

    public EventFlyTask(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void OnJoinEvent(PlayerJoinEvent event) {
        taskFly = new TaskFly(plugin, event.getPlayer());
        taskFly.runTaskTimer(plugin, 0, 20 * 5);
    }

    @EventHandler
    public  void OnQuitEvent(PlayerQuitEvent event) {
        taskFly.cancel();
    }
}
