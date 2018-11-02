package nl.ezrab.kitpvp.command;

import nl.ezrab.kitpvp.KitPvP;
import nl.ezrab.kitpvp.command.cmds.CreateKit;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

public class CommandManager implements CommandExecutor {

    private ArrayList<AbstractCommand> cmds = new ArrayList<>();

    public CommandManager() {
        this.cmds.add(new CreateKit());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(KitPvP.plugin.prefix + ChatColor.RED + "You have to be a player to use KitPvP.");
            return true;
        }
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("kitpvp")) {
            if (args.length == 0) {
                for (AbstractCommand gcmd : cmds) {
                    CommandInfo info = gcmd.getClass().getAnnotation(CommandInfo.class);
                    p.sendMessage(KitPvP.plugin.prefix + ChatColor.AQUA + "/kitpvp (" + StringUtils.join(info.aliases(), ", ").trim() + ") " + ChatColor.DARK_AQUA + info.usage() + " - " + ChatColor.GREEN + info.description());
                }
                return true;
            }
            AbstractCommand wanted = null;

            for (AbstractCommand gcmd : cmds) {
                CommandInfo info = gcmd.getClass().getAnnotation(CommandInfo.class);
                for (String alias : info.aliases()) {
                    if (alias.equals(args[0])) {
                        wanted = gcmd;
                        break;
                    }
                }
            }

            if (wanted == null) {
                p.sendMessage(KitPvP.plugin.prefix + ChatColor.RED + "Could not find command.");
                return true;
            }

            if (wanted.getClass().getAnnotation(CommandInfo.class).op() && !p.isOp()) {
                p.sendMessage(KitPvP.plugin.prefix + ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }

            ArrayList<String> newArgs = new ArrayList<>();
            Collections.addAll(newArgs, args);
            newArgs.remove(0);
            args = newArgs.toArray(new String[0]);

            wanted.onCommand(p, args);
        }
        return true;
    }
}
