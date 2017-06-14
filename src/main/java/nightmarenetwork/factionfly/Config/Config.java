package nightmarenetwork.factionfly.Config;

import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Created by matias on 13-06-17.
 */
public class Config {
    private Plugin plugin;

    private List<String> worlds;

    private boolean isEnemyNearEnable;
    private int enemyNearBlocks;

    private int flyCheck;

    // Messages
    private String notFliying;
    private String notFliyingEnemyNear;
    private String fliyingOwn;
    private String fliyingTruce;
    private String fliyingAlly;

    public Config(Plugin plugin) {
        this.plugin = plugin;

        worlds = this.plugin.getConfig().getStringList("worlds");

        isEnemyNearEnable = plugin.getConfig().getBoolean("enemy-near.enable");
        enemyNearBlocks = plugin.getConfig().getInt("enemy-near.blocks");

        flyCheck = plugin.getConfig().getInt("fly-check");

        notFliying = plugin.getConfig().getString("messages.not-flying");
        notFliyingEnemyNear = plugin.getConfig().getString("messages.not-flying-enemy-near");
        fliyingOwn = plugin.getConfig().getString("messages.flying-own");
        fliyingTruce = plugin.getConfig().getString("messages.flying-truce");
        fliyingAlly = plugin.getConfig().getString("messages.flying-ally");
    }

    public List<String> getWorlds() {
        return worlds;
    }

    public boolean isEnemyNearEnable() {
        return isEnemyNearEnable;
    }

    public int getEnemyNearBlocks() {
        return enemyNearBlocks;
    }

    public int getFlyCheck() {
        return flyCheck;
    }

    // Messages
    //

    public String getNotFliying() {
        return notFliying;
    }

    public String getNotFliyingEnemyNear() {
        return notFliyingEnemyNear;
    }

    public String getFliyingOwn() {
        return fliyingOwn;
    }

    public String getFliyingTruce() {
        return fliyingTruce;
    }

    public String getFliyingAlly() {
        return fliyingAlly;
    }
}
