package nl.ezrab.kitpvp.events;

import nl.ezrab.kitpvp.KitPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryListener implements Listener {

    private KitPvP kitPvP;

    public InventoryListener(KitPvP kitPvP) {
        this.kitPvP = kitPvP;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (kitPvP.getConfig().contains("kits." + e.getClickedInventory().getName())) {
            if (!(e.getClickedInventory().getName().contains(kitPvP.getConfig().getString("kits." + e.getClickedInventory().getName())))) {
                return;
            }
        }

        e.setCancelled(true);

        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || !clickedItem.getItemMeta().hasDisplayName() || !clickedItem.getItemMeta().hasDisplayName()) {
            return;
        }
        ItemMeta clickedItemMeta = clickedItem.getItemMeta();


    }
}
