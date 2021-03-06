package de.lukas.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.lukas.main.listeners.plugin;

public class PlayTimeAbfrage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            int gesamt = plugin.getConfig().getInt(player.getName()+ " minutesGesamt");
            int rest = plugin.getConfig().getInt("PlayTimeAllowed", 90) - plugin.getConfig().getInt(player.getName()+ " minutes");
            player.sendMessage(ChatColor.DARK_RED + "Playtime left: "+ rest + " min");
            player.sendMessage(ChatColor.DARK_PURPLE + "Playtime: " + gesamt);
            }
        else {
            System.out.println("Not available!");
            sender.sendMessage("Not available!");
        }

        return false;
    }
}
