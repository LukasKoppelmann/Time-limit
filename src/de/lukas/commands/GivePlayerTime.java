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
                                    player.sendMessage(ChatColor.RED + "Zeit darf nicht kleiner / gleich 0 sein!");
                                } else if (x >= plugin.getConfig().getInt(t.getName() + " minutes")) {
                                    int verf = plugin.getConfig().getInt(t.getName() + " minutes");
                                    player.sendMessage(ChatColor.RED + "Die Zeit darf nicht größer als " + verf + " sein");
                                } else if (x == plugin.getConfig().getInt(t.getName() + " minutes")) {
                                    int minutes = plugin.getConfig().getInt(t.getName() + " minutes");
                                    plugin.getConfig().set(t.getName() + " minutes", minutes - x);
                                    plugin.saveConfig();
                                    player.sendMessage(ChatColor.GREEN + "Zeit wurde um " + x + " min erweitert");
                                    if (minutes >= 90) {
                                        plugin.getConfig().set(t.getName() + " minutes", 0);
                                        player.sendMessage(ChatColor.YELLOW + "Deine Zeit wurde auf 90 min zurückgesetzt!");
                                        player.sendMessage(ChatColor.YELLOW + "Mehr als 90 Minuten sind nicht erlaubt!");
                                    }
                                } else if (x <= plugin.getConfig().getInt(t.getName() + " minutes")) {
                                    int minutes = plugin.getConfig().getInt(t.getName() + " minutes");
                                    plugin.getConfig().set(t.getName() + " minutes", minutes - x);
                                    plugin.saveConfig();
                                    player.sendMessage(ChatColor.GREEN + "Zeit wurde um " + x + " min erweitert");
                                    if (minutes >= 90) {
                                        plugin.getConfig().set(t.getName() + " minutes", 0);
                                        player.sendMessage(ChatColor.YELLOW + "Deine Zeit wurde auf 90 min zurückgesetzt!");
                                        player.sendMessage(ChatColor.YELLOW + "Mehr als 90 Minuten sind nicht erlaubt!");
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "Fehler beim Ausführen des Commands...");
                                }
                            }
                           else if (o != null) {
                                int x = Integer.parseInt(args[1]);
                                if (x <= 0) {
                                    player.sendMessage(ChatColor.RED + "Zeit darf nicht kleiner /gleich 0 sein!");
                                } else if (x >= plugin.getConfig().getInt(o.getName() + " minutes")) {
                                    int verf = plugin.getConfig().getInt(o.getName() + " minutes");
                                    player.sendMessage(ChatColor.RED + "Die Zeit darf nicht größer als " + verf + " sein");
                                } else if (x == plugin.getConfig().getInt(o.getName() + " minutes")) {
                                    int minutes = plugin.getConfig().getInt(o.getName() + " minutes");
                                    plugin.getConfig().set(o.getName() + " minutes", minutes - x);
                                    plugin.saveConfig();
                                    player.sendMessage(ChatColor.GREEN + "Zeit wurde um " + x + " min erweitert");
                                    if (minutes >= 90) {
                                        plugin.getConfig().set(o.getName() + " minutes", 0);
                                        player.sendMessage(ChatColor.YELLOW + "Die Zeit wurde auf 90 min zurückgesetzt!");
                                        player.sendMessage(ChatColor.YELLOW + "Mehr als 90 Minuten sind nicht erlaubt!");
                                    }
                                } else if (x <= plugin.getConfig().getInt(o.getName() + " minutes")) {
                                    int minutes = plugin.getConfig().getInt(o.getName() + " minutes");
                                    plugin.getConfig().set(o.getName() + " minutes", minutes - x);
                                    plugin.saveConfig();
                                    player.sendMessage(ChatColor.GREEN + "Zeit wurde um " + x + " min erweitert");
                                    if (minutes >= 90) {
                                        plugin.getConfig().set(o.getName() + " minutes", 0);
                                        player.sendMessage(ChatColor.YELLOW + "Die Zeit wurde auf 90 min zurückgesetzt!");
                                        player.sendMessage(ChatColor.YELLOW + "Mehr als 90 Minuten sind nicht erlaubt!");
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "Fehler beim Ausführen des Commands...");
                                }
                            }
                        }

                    }
                    else{
                        player.sendMessage(ChatColor.RED + "/addtime <Spieler> <Zeit>");
                    }

                }
                else{
                    player.sendMessage(ChatColor.DARK_RED + "Du hast dafür keine Berechtigung!");
                    player.sendMessage(ChatColor.DARK_RED + "Frage einen Admin nach Zeit oder warte 12 Stunden");
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
                        System.out.println("Minuten wurden hinzugefügt");
                    }
                          else if (o != null) {
                             int minutes = plugin.getConfig().getInt(o.getName() + " minutes");
                           plugin.getConfig().set(o.getName() + " minutes", minutes - x);
                         plugin.saveConfig();
                       System.out.println("Minuten wurden hinzugefügt");
                        System.out.println("Achtung Spielername muss korrekt sein!");
                      }
                    else {
                        System.out.println("Fehler bei der Ausführung...");
                    }
                }
            }
            return false;
        }
    }
