package nightmarenetwork.factionfly.Utilities;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matias on 02-06-17.
 */
public class PlayerUtilities {
    private Plugin plugin;
    private Player player;

    public PlayerUtilities (Plugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public List<Player> getPlayersWithin(Player player, int distance) {
        List<Player> res = new ArrayList<Player>();
        int d2 = distance * distance;
        for (Player p : plugin.getServer().getOnlinePlayers()) {
            if (p.getWorld() == player.getWorld() && p.getLocation().distanceSquared(player.getLocation()) <= d2) {
                res.add(p);
            }
        }
        return res;
    }

    public boolean isEnemyNearby(int distance) {
        int d2 = distance * distance;

        for (Player p : plugin.getServer().getOnlinePlayers()) {
            if (p.getWorld() == player.getWorld() && p.getLocation().distanceSquared(player.getLocation()) <= d2) {
                MPlayer playerNear = MPlayer.get(p);
                MPlayer observerPlayer = MPlayer.get(player);

                // player es truce o ally de m ?
                Faction factionPlayerNear = playerNear.getFaction();
                Faction factionObserverPlayer = observerPlayer.getFaction();

                Rel rel = factionPlayerNear.getRelationWish(factionObserverPlayer);
                Rel rel2 = factionObserverPlayer.getRelationWish(factionPlayerNear);

                //player.sendMessage("1) rel.getName(): " + rel.getName());

                if (rel.getName().equalsIgnoreCase("enemy") || rel2.getName().equalsIgnoreCase("enemy")){
                    return true;
                }
            }
        }

        return false;
    }
}
