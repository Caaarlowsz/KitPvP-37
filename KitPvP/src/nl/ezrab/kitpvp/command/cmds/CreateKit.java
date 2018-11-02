package nl.ezrab.kitpvp.command.cmds;

import nl.ezrab.kitpvp.KitPvP;
import nl.ezrab.kitpvp.command.AbstractCommand;
import nl.ezrab.kitpvp.command.CommandInfo;
import nl.ezrab.kitpvp.kit.KitCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@CommandInfo(description = "Make a kit", usage = "<name> <material>", aliases = {"ck", "createkit"}, op = true)
public class CreateKit extends AbstractCommand {

    private ArrayList<String> kits;

    public CreateKit(ArrayList<String> kits) {
        this.kits = kits;
    }

    @Override
    public void onCommand(Player p, String[] args) throws IllegalArgumentException {
        if (args.length == 0) {
            p.sendMessage(KitPvP.plugin.prefix + ChatColor.RED + "Please specify a kit name.");
            return;
        }

        if (args.length == 1) {
            p.sendMessage(KitPvP.plugin.prefix + ChatColor.RED + "Please specify a material name.");
            return;
        }

        String name = args[0];
        try {
            Material material = Material.getMaterial(args[1].toUpperCase());
            KitCreator kitCreator = new KitCreator(name, material, kits);
            kitCreator.createKit(p);
        } catch (IllegalArgumentException e) {
            p.sendMessage(KitPvP.plugin.prefix + ChatColor.RED + "This is not a valid material");
        }
    }
}
