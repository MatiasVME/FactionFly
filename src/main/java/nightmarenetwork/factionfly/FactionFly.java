package nightmarenetwork.factionfly;

import nightmarenetwork.factionfly.Tasks.TaskFly;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class FactionFly extends JavaPlugin {
    private TaskFly taskFly;

    @Override
    public void onEnable() {
        // Config.yml
        //

        File config = new File(getDataFolder() + File.separator + "config.yml");

        if (!config.exists())
            saveDefaultConfig();

        // Tasks
        //

        enableTask();
    }

    @Override
    public void onDisable() {
        taskFly.cancel();
    }

    private void enableTask() {
        taskFly = new TaskFly(this);
        int flyCheck = this.getConfig().getInt("fly-check");
        taskFly.runTaskTimer(this, 0, 20 * flyCheck);
    }
}
