package nl.ezrab.kitpvp;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Countdown extends BukkitRunnable {

    private KitPvP kitPvP;
    private Arena arena;
    private int i;
    private ArrayList<Integer> countingNums;

    public Countdown(KitPvP kitPvP, Arena arena, int start, int... cNums) {
        this.kitPvP = kitPvP;
        this.arena = arena;
        this.i = start;
        this.countingNums = new ArrayList<Integer>();

        for (int c : cNums) {
            countingNums.add(c);
        }
    }

    public void run() {
        if (this.i == 0) {
            for (Player p : arena.getPlayers()) {
                p.sendMessage(this.kitPvP.prefix + ChatColor.GREEN + "The game has begun!");
            }

            arena.start();

            cancel();
            return;
        }

        if (this.countingNums.contains(this.i)) {
            for (Player p : arena.getPlayers()) {
                p.sendMessage(this.kitPvP.prefix + ChatColor.GREEN + "The game will begin in " + this.i + " seconds!");
            }
        }
        this.i--;
    }
}
