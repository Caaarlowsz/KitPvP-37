package nl.ezrab.kitpvp;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Arena {

    // Dependency Injection
    private KitPvP kitPvP;
    // String id for unique arenas
    private String id;
    // ArrayList of players in arenas
    private ArrayList<Player> players;
    // ItemStack for the different kits in the kitselector
//    private ItemStack kitSelector;
    private ArenaState state;

    public Arena(KitPvP kitPvP, String id) {
        this.kitPvP = kitPvP;
        this.id = id;
        this.state = ArenaState.WAITING;
        this.players = new ArrayList<Player>();
    }

    public String getID() {
        return this.id;
    }

    public ArenaState getState() {
        return this.state;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public boolean hasPlayer(Player p) {
        return this.players.contains(p);
    }

    public void addPlayer(Player p) {
        if (this.state == ArenaState.WAITING) {
            p.sendMessage(this.kitPvP.prefix + ChatColor.RED + "This arena has already started!");
            return;
        }

        this.players.add(p);
        p.getInventory().clear();
        p.sendMessage(this.kitPvP.prefix + ChatColor.GREEN + "You have joined arena " + id + ".");

        if (this.state == ArenaState.WAITING) {
            this.state = ArenaState.COUNTDOWN;
            new Countdown(this.kitPvP, this, 5, 5, 4, 3, 2, 1).runTaskTimerAsynchronously(this.kitPvP, 0, 20);
        }
    }

    public void removePlayer(Player p) {
        this.players.remove(p);
        p.teleport(this.kitPvP.getServer().getWorlds().get(0).getSpawnLocation()); // fixme temp teleport location

        if (this.players.size() <= 1) {
            if (this.players.size() == 1) {
                this.kitPvP.getServer().broadcastMessage(this.kitPvP.prefix + ChatColor.GOLD + this.players
                        .get(0).getName() + ChatColor.GREEN + " has won arena " + this.id + "!");
                this.players.remove(0);
                this.players.get(0).teleport(this.kitPvP.getServer().getWorlds().get(0).getSpawnLocation()); // fixme temp teleport location
            } else {
                this.kitPvP.getServer().broadcastMessage(this.kitPvP.prefix + ChatColor.GREEN + "Arena " + this.id + " has ended.");
            }
            this.players.clear();
            this.state = ArenaState.WAITING;
        }
    }

    public void start() {
        this.state = ArenaState.STARTED;

        for (Player p : this.players) {
            // todo check if p has kit
            p.setHealth(20.0D);
            p.setGameMode(GameMode.SURVIVAL);
        }
    }

    // GameStates for the arena
    private enum ArenaState {
        WAITING, COUNTDOWN, STARTED
    }
}