package me.ksviety.plugins.mc.locations.util.NMS;

import net.minecraft.server.v1_13_R2.*;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_13_R2 implements NMS {

    @Override
    public void sendActionBarMessage(Player p, String s) {
        EntityPlayer entity = ((CraftPlayer) p).getHandle();
        IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + s + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(component, ChatMessageType.GAME_INFO);

        entity.playerConnection.sendPacket(packet);
    }

}
