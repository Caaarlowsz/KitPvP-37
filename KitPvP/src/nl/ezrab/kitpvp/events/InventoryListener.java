package nl.ezrab.kitpvp.events;

import nl.ezrab.kitpvp.KitPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    private KitPvP kitPvP;

    public InventoryListener(KitPvP kitPvP) {
        this.kitPvP = kitPvP;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getClickedInventory() != null) {
            if (!kitPvP.getConfig().contains("kits." + e.getClickedInventory().getName())) {
                return;
            }
        }
        e.setCancelled(true);
    }
}
