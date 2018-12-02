package nl.ezrab.kitpvp.command.cmds;

import nl.ezrab.kitpvp.ArenaManager;
import nl.ezrab.kitpvp.KitPvP;
import nl.ezrab.kitpvp.command.CommandInfo;
import nl.ezrab.kitpvp.command.GameCommand;
import nl.ezrab.kitpvp.config.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandInfo(description = "Create an arena.", usage = "<name>", aliases = {"createarena", "ca"}, op = true)
public class CreateArena extends GameCommand {

    private KitPvP kitPvP;

    public CreateArena(KitPvP kitPvP) {
        this.kitPvP = kitPvP;
    }

    public void onCommand(Player p, String[] args) {
        if (args.length == 0) {
            p.sendMessage(this.kitPvP.prefix + ChatColor.RED + "You must specify a name for the arena.");
            return;
        }

        String arenaName = args[0];

        ArenaManager arenaManager = new ArenaManager(this.kitPvP);

        if (arenaManager.getArena(arenaName) != null) {
            p.sendMessage(this.kitPvP.prefix + ChatColor.RED + "An arena with that name already exists.");
            return;
        }

        SettingsManager settingsManager = new SettingsManager(this.kitPvP, "arenas");
        settingsManager.set(arenaName + ".world", p.getWorld().getName());
        settingsManager.save();

        arenaManager.setup();
        p.sendMessage(this.kitPvP.prefix + ChatColor.GREEN + "Created arena " + ChatColor.GOLD + arenaName +
                ChatColor.GREEN + ". Now you must setup the spawns.");
    }
}
