package nightmarenetwork.factionfly;

import org.bukkit.plugin.java.JavaPlugin;
import nightmarenetwork.factionfly.Events.EventFlyTask;

import java.io.File;

public final class FactionFly extends JavaPlugin {

    @Override
    public void onEnable() {
        // Config.yml
        //

        File config = new File(getDataFolder() + File.separator + "config.yml");

        if (!config.exists())
            saveDefaultConfig();

        // Events
        //

        EventFlyTask eventFlyTask = new EventFlyTask(this);
        getServer().getPluginManager().registerEvents(eventFlyTask, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
