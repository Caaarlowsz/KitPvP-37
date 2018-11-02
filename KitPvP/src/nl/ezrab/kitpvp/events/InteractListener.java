package nl.ezrab.kitpvp.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class InteractListener implements Listener {

    private ArrayList<String> kits;

    public InteractListener(ArrayList<String> kits) {
        this.kits = kits;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(kits.toString())) {
                p.sendMessage("nice");
            }
        }
    }
}
