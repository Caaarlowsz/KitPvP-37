package nl.ezrab.kitpvp.events;

import nl.ezrab.kitpvp.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class InteractListener implements Listener {

    private ArrayList<String> kits;
    private final Inventory inv;

    public InteractListener(ArrayList<String> kits) {
        this.kits = kits;

        this.inv = Bukkit.createInventory(null, 9, ChatColor
                .translateAlternateColorCodes('&', this.kits.toString().replaceAll("[\\[\\]]", "")));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (this.kits == null || this.inv == null) {
            return;
        }

        Player p = e.getPlayer();

        if (!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(KitPvP.plugin.getConfig().getString(this.kits.toString())))) {
            KitPvP.plugin.getLogger().info(KitPvP.plugin.getConfig().getString(this.kits.toString()));
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            e.setCancelled(true);
            p.openInventory(this.inv);
        }
    }
}
