package nl.ezrab.kitpvp;

import nl.ezrab.kitpvp.config.SettingsManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ArenaManager {

    private ArrayList<Arena> arenas;
    private KitPvP kitPvP;

    public ArenaManager(KitPvP kitPvP) {
        this.arenas = new ArrayList<Arena>();

        this.kitPvP = kitPvP;
    }

    public void setup() {
        this.arenas.clear();

        SettingsManager settingsManager = new SettingsManager(this.kitPvP, "arenas");
        for (String arenaID : settingsManager.getKeys()) {
            this.arenas.add(new Arena(this.kitPvP, arenaID));
        }
    }

    public Arena getArena(String id) {
        for (Arena arena : this.arenas) {
            if (arena.getID().equals(id)) {
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(Player p) {
        for (Arena arena : this.arenas) {
            if (arena.hasPlayer(p)) {
                return arena;
            }
        }
        return null;
    }

    public ArrayList<Arena> getArenas() {
        return this.arenas;
    }
}
