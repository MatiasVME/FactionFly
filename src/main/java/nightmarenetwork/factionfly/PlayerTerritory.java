package nightmarenetwork.factionfly;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matias on 31-05-17.
 */
public class PlayerTerritory {
    private Player player;
    private Plugin plugin;

    private List<String> worlds;

    public PlayerTerritory (Plugin plugin, Player player) {
        this.player = player;
        this.plugin = plugin;

        this.worlds = new ArrayList<>();
        worlds = this.plugin.getConfig().getStringList("worlds");
    }

    public boolean isInOwnTerritory() {
        boolean isAPermittedWold = checkWold();

        if (isAPermittedWold) {
            MPlayer mplayer = MPlayer.get(player);
            Faction faction;

            Location location = player.getLocation();
            faction = BoardColl.get().getFactionAt(PS.valueOf(location));

            if (faction.getName().equals(mplayer.getFactionName())) {
                return true;
            }
        }

        return false;
    }

    public boolean isInTruceTerritory() {
        boolean isAPermittedWold = checkWold();

        if (isAPermittedWold) {
            MPlayer mplayer = MPlayer.get(player);
            Faction faction;

            Location location = player.getLocation();
            faction = BoardColl.get().getFactionAt(PS.valueOf(location));

            Rel rel = faction.getRelationWish(mplayer.getFaction());

            if (rel.getName().equalsIgnoreCase("truce"))
                return true;
        }

        return false;
    }

    public boolean isInAllyTerritory() {
        boolean isAPermittedWold = checkWold();

        if (isAPermittedWold) {
            MPlayer mplayer = MPlayer.get(player);
            Faction faction;

            Location location = player.getLocation();
            faction = BoardColl.get().getFactionAt(PS.valueOf(location));

            Rel rel = faction.getRelationWish(mplayer.getFaction());

            if (rel.getName().equalsIgnoreCase("ally"))
                return true;
        }

        return false;
    }

    public boolean checkWold() {
        String playerWorld = player.getWorld().getName();

        for (String world : worlds) {
            //player.sendMessage(world);
            if (playerWorld.equalsIgnoreCase(world)) return true;
        }

        return false;
    }
}
