package nl.ezrab.kitpvp.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
//        TODO check if player is clicking the right inventory.
//        if (e.getClickedInventory().getName().equals("")) {
//
//        }

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
