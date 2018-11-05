package nl.ezrab.kitpvp.events;

import nl.ezrab.kitpvp.KitPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class InteractListener implements Listener {

    private KitPvP kitPvP;
    private ArrayList<Inventory> inv;

    public InteractListener(KitPvP kitPvP, ArrayList<Inventory> inv) {
        this.kitPvP = kitPvP;
        this.inv = inv;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(kitPvP.getConfig()
                .getString("kits." + p.getInventory().getItemInMainHand().getItemMeta().getDisplayName()))) {
            p.sendMessage("Doesn't work.");
            return;
        } else {
            p.sendMessage("works");
        }

        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            p.openInventory(this.inv.get(0));
        }
    }
}
