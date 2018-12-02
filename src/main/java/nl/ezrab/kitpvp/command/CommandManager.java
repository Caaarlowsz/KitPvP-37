package nl.ezrab.kitpvp.command;

import nl.ezrab.kitpvp.KitPvP;
import nl.ezrab.kitpvp.command.cmds.CreateArena;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

public class CommandManager implements CommandExecutor {

    private KitPvP kitPvP;
    private ArrayList<GameCommand> cmds;

    public CommandManager(KitPvP kitPvP) {
        this.kitPvP = kitPvP;

        this.cmds = new ArrayList<GameCommand>();
        this.cmds.add(new CreateArena(this.kitPvP));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(this.kitPvP.prefix + ChatColor.RED + "You must be a player to execute this command.");
            return true;
        }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("kitpvp")) {
            if (args.length == 0) {
                for (GameCommand gameCommand : this.cmds) {
                    CommandInfo info = gameCommand.getClass().getAnnotation(CommandInfo.class);
                    p.sendMessage(this.kitPvP.prefix + ChatColor.GOLD + "/kitpvp (" + StringUtils.join(info.aliases(), " ").trim() + ") " + info.usage() + " - " + info.description());
                }
                return true;
            }

            GameCommand wanted = null;

            for (GameCommand gameCommand : this.cmds) {
                CommandInfo info = gameCommand.getClass().getAnnotation(CommandInfo.class);
                for (String alias : info.aliases()) {
                    if (alias.equalsIgnoreCase(args[0])) {
                        wanted = gameCommand;
                        break;
                    }
                }
            }

            if (wanted == null) {
                p.sendMessage(this.kitPvP.prefix + ChatColor.RED + "Could not find command.");
                return true;
            }

            if (wanted.getClass().getAnnotation(CommandInfo.class).op() && !p.isOp()) {
                p.sendMessage(this.kitPvP.prefix + ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }

            ArrayList<String> newArgs = new ArrayList<String>();
            Collections.addAll(newArgs, args);
            newArgs.remove(0);
            args = newArgs.toArray(new String[0]);

            wanted.onCommand(p, args);
        }
        return true;
    }
}
