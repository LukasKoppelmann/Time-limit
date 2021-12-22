package de.lukas.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.lukas.main.listeners.plugin;

    public class GivePlayerTime implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
            if(sender instanceof Player){
                Player player = (Player) sender;
                if(player.isOp()){
                    if(args.length == 0){
                        player.sendMessage(ChatColor.RED + "/addtime <Spieler> <Zeit>");
                    }
                    else if(args.length == 1){
                        player.sendMessage(ChatColor.RED + "/addtime <Spieler> <Zeit>");
                    }
                    else if(args.length ==2){
                        if(args[0] != null) {
                            String username = args[0];
                            Player t = Bukkit.getPlayer(username);
                            OfflinePlayer o = Bukkit.getOfflinePlayer(username);

                            if (t != null) {
                                int x = Integer.parseInt(args[1]);
                                if (x <= 0) {
                                    player.sendMessage(ChatColor.RED + "Time must not be less than / equal to 0!");
                                } else if (x >= plugin.getConfig().getInt(t.getName() + " minutes")) {
                                    int verf = plugin.getConfig().getInt(t.getName() + " minutes");
                                    player.sendMessage(ChatColor.RED + "The time must not be greater than " + verf);
                                } else if (x == plugin.getConfig().getInt(t.getName() + " minutes")) {
                                    int minutes = plugin.getConfig().getInt(t.getName() + " minutes");
                                    plugin.getConfig().set(t.getName() + " minutes", minutes - x);
                                    plugin.saveConfig();
                                    player.sendMessage(ChatColor.GREEN + "Time has been extended by " + x + " min");
                                    if (minutes >= 90) {
                                        plugin.getConfig().set(t.getName() + " minutes", 0);
                                        player.sendMessage(ChatColor.YELLOW + "Your time has been reset to 90 minutes!");
                                        player.sendMessage(ChatColor.YELLOW + "More than 90 minutes are not allowed!");
                                    }
                                } else if (x <= plugin.getConfig().getInt(t.getName() + " minutes")) {
                                    int minutes = plugin.getConfig().getInt(t.getName() + " minutes");
                                    plugin.getConfig().set(t.getName() + " minutes", minutes - x);
                                    plugin.saveConfig();
                                    player.sendMessage(ChatColor.GREEN + "Time has been extended by " + x + " min");
                                    if (minutes >= 90) {
                                        plugin.getConfig().set(t.getName() + " minutes", 0);
                                        player.sendMessage(ChatColor.YELLOW + "Your time has been reset to 90 minutes!");
                                        player.sendMessage(ChatColor.YELLOW + "More than 90 minutes are not allowed!");
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "Error!");
                                }
                            }
                           else if (o != null) {
                                int x = Integer.parseInt(args[1]);
                                if (x <= 0) {
                                    player.sendMessage(ChatColor.RED + "Time must not be less than / equal to 0!");
                                } else if (x >= plugin.getConfig().getInt(o.getName() + " minutes")) {
                                    int verf = plugin.getConfig().getInt(o.getName() + " minutes");
                                    player.sendMessage(ChatColor.RED + "The time must not be greater than " + verf);
                                } else if (x == plugin.getConfig().getInt(o.getName() + " minutes")) {
                                    int minutes = plugin.getConfig().getInt(o.getName() + " minutes");
                                    plugin.getConfig().set(o.getName() + " minutes", minutes - x);
                                    plugin.saveConfig();
                                    player.sendMessage(ChatColor.GREEN + "Time has been extended by " + x + " min");
                                    if (minutes >= 90) {
                                        plugin.getConfig().set(o.getName() + " minutes", 0);
                                        player.sendMessage(ChatColor.YELLOW + "Your time has been reset to 90 minutes!");
                                        player.sendMessage(ChatColor.YELLOW + "More than 90 minutes are not allowed!");
                                    }
                                } else if (x <= plugin.getConfig().getInt(o.getName() + " minutes")) {
                                    int minutes = plugin.getConfig().getInt(o.getName() + " minutes");
                                    plugin.getConfig().set(o.getName() + " minutes", minutes - x);
                                    plugin.saveConfig();
                                    player.sendMessage(ChatColor.GREEN + "Time has been extended by " + x + " min");
                                    if (minutes >= 90) {
                                        plugin.getConfig().set(o.getName() + " minutes", 0);
                                        player.sendMessage(ChatColor.YELLOW + "Your time has been reset to 90 minutes!");
                                        player.sendMessage(ChatColor.YELLOW + "More than 90 minutes are not allowed!");
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "Error!");
                                }
                            }
                        }

                    }
                    else{
                        player.sendMessage(ChatColor.RED + "/addtime <Spieler> <Zeit>");
                    }

                }
                else{
                    player.sendMessage(ChatColor.DARK_RED + "You are not authorized to do this!");
                    player.sendMessage(ChatColor.DARK_RED + "Ask an admin for time or wait 12 hours!");
                }
            }
            else {
                if (args[0] != null) {
                    int x = Integer.parseInt(args[1]);
                    String username = args[0];
                    Player t = Bukkit.getPlayer(username);
                    OfflinePlayer o = Bukkit.getOfflinePlayer(username);
                    if (t != null) {
                        int minutes = plugin.getConfig().getInt(t.getName() + " minutes");
                        plugin.getConfig().set(t.getName() + " minutes", minutes - x);
                        plugin.saveConfig();
                        System.out.println("Minutes have been added");
                    }
                          else if (o != null) {
                             int minutes = plugin.getConfig().getInt(o.getName() + " minutes");
                           plugin.getConfig().set(o.getName() + " minutes", minutes - x);
                         plugin.saveConfig();
                       System.out.println("Minutes have been added");
                        System.out.println("Attention, the player's name must be correct!");
                      }
                    else {
                        System.out.println("Error!");
                    }
                }
            }
            return false;
        }
    }
