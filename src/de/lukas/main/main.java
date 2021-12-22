package de.lukas.main;
import de.lukas.commands.GivePlayerTime;
import de.lukas.commands.PlayTimeAbfrage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
    @Override
    public void onEnable() {
        getCommand("left").setExecutor(new PlayTimeAbfrage()); //PlayTimeAbfrage
        getCommand("addtime").setExecutor(new GivePlayerTime());
        System.out.println("[time-limit]: Done!");
        startTimer();
        new listeners(this);
    }


    private void startTimer() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run() {
                for (OfflinePlayer player : Bukkit.getOfflinePlayers()){
                    int vergangen = getConfig().getInt(player.getName()+ " ZeitVer");
                    vergangen++;
                    getConfig().set(player.getName()+ " ZeitVer",vergangen);
                    saveConfig();
                    if (vergangen == 720){  //hier Dauer bis wieder erlaubt
                        int minutes = getConfig().getInt(player.getName() + " minutes");
                        getConfig().set(player.getName()+ " minutes",0);
                        saveConfig();
                    }
                }

                for (Player players : Bukkit.getOnlinePlayers() ){
                    int allowed = getConfig().getInt("PlayTimeAllowed",90);           //hier Zeit eintragen!
                    int minutes = getConfig().getInt(players.getName()+ " minutes");
                    int gesamt = getConfig().getInt(players.getName()+ " minutesGesamt");
                    minutes++;
                    getConfig().set(players.getName()+ " minutes",minutes);
                    getConfig().set(players.getName()+ " minutesGesamt",minutes);
                    getConfig().getInt("PlayTimeAllowed");
                    saveConfig();

                    if(minutes == 0){

                    }

                    if (minutes + 10== allowed){
                        players.sendMessage(ChatColor.RED + players.getName() + " 10 minutes left!");
                        System.out.println(players.getName() + " 10 min");
                    }
                    if (minutes + 1== allowed){
                        players.sendMessage(ChatColor.RED + players.getName() + " 1 minute left!");
                        System.out.println(players.getName() + " 1 min");
                    }
                    if (minutes >= allowed){
                        players.kickPlayer(ChatColor.DARK_RED + "You have reached your time limit!");
                        System.out.println(players.getName() + " kicked --> no time left");
                        getConfig().set(players.getName()+ " ZeitVer",0);
                        saveConfig();
                    }
                    if (minutes <= allowed){


                    }

                    else {
                        System.out.println("[time-limit]: Error!");
                    }
                }
            }
        }, 20*60,20*60);
    }

    @Override
    public void onDisable() {
        System.out.println("[time-limit]: Shutdown successful!");
    }
}
