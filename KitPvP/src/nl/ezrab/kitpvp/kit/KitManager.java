package nl.ezrab.kitpvp.kit;

import nl.ezrab.kitpvp.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class KitManager {

    private final Inventory inv;
    private ArrayList<Inventory> inventories;
    private KitPvP kitPvP;
    private String name;
    private Material material;

    public KitManager(KitPvP kitPvP, String name, Material material, ArrayList<Inventory> inventories) {
        this.kitPvP = kitPvP;
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        this.material = material;
        this.inventories = inventories;

        this.inv = Bukkit.createInventory(null, 9, this.name);
    }

    public void createKit(Player p) {
        if (!(this.kitPvP.getConfig().contains("kits." + this.name))) {
            this.kitPvP.getConfig().set("kits." + this.name, this.material.toString());
            this.kitPvP.saveConfig();
        } else {
            p.sendMessage(this.kitPvP.prefix + ChatColor.RED + this.name + ChatColor.RED + " already exists in the config.");
            return;
        }
        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.name);
        itemStack.setItemMeta(itemMeta);
        p.getInventory().addItem(itemStack);

        inventories.add(this.inv);

        p.openInventory(this.inv);
    }

    public String getName() {
        return name;
    }

    public KitPvP getKitPvP() {
        return kitPvP;
    }

    public Material getMaterial() {
        return material;
    }
}
