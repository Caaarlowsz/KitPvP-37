package nl.ezrab.kitpvp.events;

import nl.ezrab.kitpvp.KitPvP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class InteractListener implements Listener {

    private KitPvP kitPvP;
    private ArrayList<Inventory> inventories;

    public InteractListener(KitPvP kitPvP, ArrayList<Inventory> inventories) {
        this.kitPvP = kitPvP;
        this.inventories = inventories;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        p.sendMessage(this.inventories.toString());
    }
}
