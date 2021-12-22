package de.lukas.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;


public class listeners implements Listener {

    public static main plugin;

    public listeners(main plugin) {
        listeners.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        event.setLeaveMessage(ChatColor.DARK_RED + player.getName() + " has been kicked! --> no Time left");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int rest = plugin.getConfig().getInt("PlayTimeAllowed", 90) - plugin.getConfig().getInt(player.getName() + " minutes");
        event.setJoinMessage(ChatColor.GREEN + "Hello, " + player.getName() + "! Welcome back!");
        event.getPlayer().sendMessage(ChatColor.DARK_RED + "Playtime left: " + rest + " min");
        event.getPlayer().sendMessage(ChatColor.AQUA + "Tipp: /left");
        int vergangen = plugin.getConfig().getInt(event.getPlayer().getName() + " ZeitVer");
        plugin.getConfig().set(event.getPlayer().getName() + " ZeitVer", vergangen);
        plugin.saveConfig();
        if (vergangen >= 720) {  //hier Dauer bis wieder erlaubt
            int minutes = plugin.getConfig().getInt(event.getPlayer().getName() + " minutes");
            plugin.getConfig().set(event.getPlayer().getName() + " minutes", 0);
            plugin.getConfig().set(event.getPlayer().getName() + " ZeitVer", 0);
            plugin.saveConfig();
        }
        if(plugin.getConfig().getInt(event.getPlayer().getName()+ " minutes") == 90){
            int zeitb = 720 - vergangen;
            event.getPlayer().kickPlayer(ChatColor.DARK_RED + "You have reached yout time limit! Wait: " + zeitb + " min");
        }
        if(plugin.getConfig().getInt(event.getPlayer().getName()+ " minutes") >= 90){
            int zeitb = 720 - vergangen;
            event.getPlayer().kickPlayer(ChatColor.DARK_RED + "You have reached yout time limit! Wait: "+ zeitb + " min");
        }
        else{

        }
    }
}