package nl.ezrab.kitpvp.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class InventoryListener implements Listener {

    private ArrayList<String> kits;

    public InventoryListener(ArrayList<String> kits) {
        this.kits = kits;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (!e.getClickedInventory().getName().equals(kits.toString())) {
            return;
        }

        e.setCancelled(true);

        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || !clickedItem.getItemMeta().hasDisplayName() || !clickedItem.getItemMeta().hasDisplayName()) {
            return;
        }
        ItemMeta clickedItemMeta = clickedItem.getItemMeta();

//        TODO Check if player is clicking an item with the right display name.
//        if (clickedItemMeta.getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', ""))) {
//
//        }

    }
}
