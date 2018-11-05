package nl.ezrab.kitpvp.command.cmds;

import nl.ezrab.kitpvp.KitPvP;
import nl.ezrab.kitpvp.command.AbstractCommand;
import nl.ezrab.kitpvp.command.CommandInfo;
import nl.ezrab.kitpvp.kit.KitManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

@CommandInfo(description = "Make a kit", usage = "<name> <material>", aliases = {"ck", "createkit"}, op = true)
public class CreateKit extends AbstractCommand {

    private KitPvP kitPvP;
    private ArrayList<Inventory> inventories;

    public CreateKit(KitPvP kitPvP, ArrayList<Inventory> inventories) {
        this.kitPvP = kitPvP;
        this.inventories = inventories;
    }

    @Override
    public void onCommand(Player p, String[] args) throws IllegalArgumentException {
        if (args.length == 0) {
            p.sendMessage(this.kitPvP.prefix + ChatColor.RED + "Please specify a kit name.");
            return;
        }

        if (args.length == 1) {
            p.sendMessage(this.kitPvP.prefix + ChatColor.RED + "Please specify a material name.");
            return;
        }

        String name = args[0];
        try {
            Material material = Material.getMaterial(args[1].toUpperCase());
            KitManager kitCreator = new KitManager(this.kitPvP, name, material, this.inventories);
            kitCreator.createKit(p);
        } catch (IllegalArgumentException e) {
            p.sendMessage(this.kitPvP.prefix + ChatColor.RED + "This is not a valid material");
        }
    }
}
