package me.ksviety.plugins.mc.locations.listeners;

import me.ksviety.plugins.mc.locations.Plugin;
import me.ksviety.plugins.mc.locations.pojo.saves.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventListener implements Listener {

    //  Listening to the event for joining players
    //  Checking if the player that just joined is not saved
    //  Saving them if so
    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoined(PlayerJoinEvent e) {
        Player[] savedPlayers = Plugin.playersData.getPlayers();
        Player newPlayer;

        //  Checking if the player is saved
        for (Player player: savedPlayers) {

            //  Return from the method if the player is saved
            if (player.getUUID().equals(e.getPlayer().getUniqueId()))
                return;

        }

        //  Saving the player
        newPlayer = new Player(e.getPlayer().getUniqueId());

        Plugin.playersData.addPlayer(newPlayer);

    }

}
