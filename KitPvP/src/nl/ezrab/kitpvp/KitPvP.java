package nl.ezrab.kitpvp;

import nl.ezrab.kitpvp.command.CommandManager;
import nl.ezrab.kitpvp.events.InteractListener;
import nl.ezrab.kitpvp.events.InventoryListener;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class KitPvP extends JavaPlugin {

    public final String prefix =
            ChatColor.DARK_AQUA + "[" + ChatColor.BLUE + ChatColor.BOLD + "KitPvP" + ChatColor.RESET + ChatColor.DARK_AQUA + "] " + ChatColor.RESET;
    private ArrayList<Inventory> inventories = new ArrayList<>();

    public void onEnable() {
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        this.getCommand("kitpvp").setExecutor(new CommandManager(this));

        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new InventoryListener(this), this);
        pluginManager.registerEvents(new InteractListener(this, this.inventories), this);
    }
}
