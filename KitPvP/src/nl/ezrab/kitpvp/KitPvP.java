package nl.ezrab.kitpvp;

import nl.ezrab.kitpvp.command.CommandManager;
import nl.ezrab.kitpvp.events.InteractListener;
import nl.ezrab.kitpvp.events.InventoryListener;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class KitPvP extends JavaPlugin {

    private ArrayList<String> kits = new ArrayList<>();
    private Inventory inv;

    public static KitPvP plugin;
    public final String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.BLUE + ChatColor.BOLD + "KitPvP" + ChatColor.RESET + ChatColor.DARK_AQUA + "] " + ChatColor.RESET;

    public void onEnable() {
        plugin = this;
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        this.getCommand("kitpvp").setExecutor(new CommandManager(kits));

        this.getServer().getPluginManager().registerEvents(new InventoryListener(kits), this);
        this.getServer().getPluginManager().registerEvents(new InteractListener(kits), this);
    }

    @Override
    public void onDisable() {
        plugin = null;
    }
}
