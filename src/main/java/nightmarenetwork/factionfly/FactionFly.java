package nightmarenetwork.factionfly;

import nightmarenetwork.factionfly.Config.Config;
import org.bukkit.plugin.java.JavaPlugin;
import nightmarenetwork.factionfly.Events.EventFlyTask;

import java.io.File;

public final class FactionFly extends JavaPlugin {

    @Override
    public void onEnable() {
        // Config.yml
        //

        File fileConfig = new File(getDataFolder() + File.separator + "config.yml");

        if (!fileConfig.exists())
            saveDefaultConfig();

        // Config.java
        //

        Config config = new Config(this);

        // Events
        //

        EventFlyTask eventFlyTask = new EventFlyTask(this, config);
        getServer().getPluginManager().registerEvents(eventFlyTask, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
