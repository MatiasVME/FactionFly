package nightmarenetwork.factionfly;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import nightmarenetwork.factionfly.Config.Config;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Created by matias on 31-05-17.
 */
public class PlayerTerritory {
    private Player player;
    private Plugin plugin;
    private Config config;

    private List<String> worlds;

    public PlayerTerritory (Plugin plugin, Player player, Config config) {
        this.player = player;
        this.plugin = plugin;
        this.config = config;

        //this.worlds = new ArrayList<>();
        worlds = config.getWorlds();
    }

    public boolean isInOwnTerritory() {
        boolean isAPermittedWold = checkWold();

        if (isAPermittedWold) {
            MPlayer mplayer = MPlayer.get(player);


            Location location = player.getLocation();
            Faction fac1 = BoardColl.get().getFactionAt(PS.valueOf(location));

            if (fac1.getName().equals(mplayer.getFactionName())
                    && !fac1.getName().equalsIgnoreCase("safezone")
                    && !fac1.getName().equalsIgnoreCase("warzone")) {
                return true;
            }
        }

        return false;
    }

    public boolean isInTruceTerritory() {
        boolean isAPermittedWold = checkWold();

        if (isAPermittedWold) {
            MPlayer mplayer = MPlayer.get(player);

            Location location = player.getLocation();
            Faction fac1 = BoardColl.get().getFactionAt(PS.valueOf(location));
            Faction fac2 = mplayer.getFaction();

            Rel rel1 = fac1.getRelationWish(mplayer.getFaction());
            Rel rel2 = fac2.getRelationWish(fac1);

            if (rel1.getName().equalsIgnoreCase("truce") && rel2.getName().equalsIgnoreCase("truce")
                    && !fac1.getName().equalsIgnoreCase("safezone")
                    && !fac1.getName().equalsIgnoreCase("warzone"))
                return true;
        }

        return false;
    }

    public boolean isInAllyTerritory() {
        boolean isAPermittedWold = checkWold();

        if (isAPermittedWold) {
            MPlayer mplayer = MPlayer.get(player);

            Location location = player.getLocation();
            Faction fac1 = BoardColl.get().getFactionAt(PS.valueOf(location));
            Faction fac2 = mplayer.getFaction();

            Rel rel = fac1.getRelationWish(mplayer.getFaction());
            Rel rel2 = fac2.getRelationWish(fac1);

            if (rel.getName().equalsIgnoreCase("ally") && rel2.getName().equalsIgnoreCase("ally")
                    && !fac1.getName().equalsIgnoreCase("safezone")
                    && !fac1.getName().equalsIgnoreCase("warzone"))
                return true;
        }

        return false;
    }

    public boolean checkWold() {
        String playerWorld = player.getWorld().getName();

        for (String world : worlds) {
            if (playerWorld.equalsIgnoreCase(world)) return true;
        }

        return false;
    }
}
