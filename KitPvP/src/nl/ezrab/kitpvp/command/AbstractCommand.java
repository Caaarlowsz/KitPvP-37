package nl.ezrab.kitpvp.command;

import org.bukkit.entity.Player;

public abstract class AbstractCommand {

    public abstract void onCommand(Player p, String[] args);
}
