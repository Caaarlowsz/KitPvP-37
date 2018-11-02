package nl.ezrab.kitpvp.kit;

import nl.ezrab.kitpvp.KitPvP;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class KitCreator {

    private String name;
    private Material material;
    private ArrayList<String> kits;

    public KitCreator(String name, Material material, ArrayList<String> kits) {
        this.kits = kits;
        this.name = name;
        this.material = material;
    }

    public void createKit(Player p) {
        if (!(KitPvP.plugin.getConfig().contains("kits." + this.name))) {
            KitPvP.plugin.getConfig().set("kits." + this.name, this.material.toString());
            KitPvP.plugin.saveConfig();
        } else {
            p.sendMessage(KitPvP.plugin.prefix + ChatColor.RED + ChatColor.translateAlternateColorCodes('&', this.name) + ChatColor.RED + " already exists in the config.");
            return;
        }

        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.name));
        itemStack.setItemMeta(itemMeta);
        p.getInventory().addItem(itemStack);

        kits.add(this.name);
        p.sendMessage(KitPvP.plugin.prefix + ChatColor.GREEN + "Received kit: " + ChatColor.translateAlternateColorCodes('&', this.name));
    }
}
