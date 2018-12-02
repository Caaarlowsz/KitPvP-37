package nl.ezrab.kitpvp;

import nl.ezrab.kitpvp.command.CommandManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class KitPvP extends JavaPlugin {

    public final String prefix = ChatColor.AQUA + "" + ChatColor.BOLD + "[" + ChatColor.BLUE +
            "" + ChatColor.BOLD + "KitPvP" + ChatColor.AQUA + "" + ChatColor.BOLD + "] " + ChatColor.RESET;

    @Override
    public void onEnable() {
        ArenaManager manager = new ArenaManager(this);
        manager.setup();
        this.getCommand("kitpvp").setExecutor(new CommandManager(this));
    }
}
