package nl.ezrab.kitpvp;

import nl.ezrab.kitpvp.command.CommandManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class KitPvP extends JavaPlugin {

    public static KitPvP plugin;
    public final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.BLUE + ChatColor.BOLD + "KitPvP" + ChatColor.RESET + ChatColor.DARK_AQUA + "] " + ChatColor.RESET;

    public void onEnable() {
        plugin = this;
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        this.getCommand("kitpvp").setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        plugin = null;
    }
}
