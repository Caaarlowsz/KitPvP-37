package nl.ezrab.kitpvp.kit;

import nl.ezrab.kitpvp.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitCreator {

    private final Inventory inv;
    private String name;
    private Material material;

    public KitCreator(String name, Material material) {
        this.name = name;
        this.material = material;

        inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', this.name));
    }

    public void createKit(Player p) {
        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.name));
        itemStack.setItemMeta(itemMeta);
        p.getInventory().addItem(itemStack);

        if (!(KitPvP.plugin.getConfig().contains("kits." + this.name))) {
            KitPvP.plugin.getConfig().set("kits." + this.name, this.material.toString());
            KitPvP.plugin.saveConfig();
        } else {
            p.sendMessage(KitPvP.plugin.prefix + ChatColor.RED + ChatColor.translateAlternateColorCodes('&', this.name) + ChatColor.RED + " already exists in the config.");
            return;
        }
        p.sendMessage(KitPvP.plugin.prefix + ChatColor.GREEN + "Received kit: " + ChatColor.translateAlternateColorCodes('&', this.name));
    }

    public Inventory getInv() {
        return inv;
    }

    public String getName() {
        return name;
    }
}
