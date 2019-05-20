package me.ksviety.plugins.mc.locations.util.NMS;

import static net.minecraft.server.v1_13_R1.PacketPlayOutTitle.*;

import me.ksviety.plugins.mc.locations.util.NMS.objects.Text;
import net.minecraft.server.v1_13_R1.*;
import org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_13_R1 implements NMS {

    @Override
    public void sendGameInfo(Player p, String s) {
        EntityPlayer entity = ((CraftPlayer) p).getHandle();
        Text content = new Text().setText(s);
        IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a(content.toJson());
        PacketPlayOutChat packet = new PacketPlayOutChat(component, ChatMessageType.GAME_INFO);

        entity.playerConnection.sendPacket(packet);

    }

    @Override
    public void sendTitle(Player p, Text title, Text subTitle, int fadeIn, int stay, int fadeOut) {
        EntityPlayer entity = ((CraftPlayer) p).getHandle();

        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a(title.toJson());
        IChatBaseComponent chatSubTitle = IChatBaseComponent.ChatSerializer.a(subTitle.toJson());

        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
        PacketPlayOutTitle subTitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubTitle);
        PacketPlayOutTitle lengthsPacket = new PacketPlayOutTitle(fadeIn, stay, fadeOut);

        entity.playerConnection.sendPacket(titlePacket);
        entity.playerConnection.sendPacket(subTitlePacket);
        entity.playerConnection.sendPacket(lengthsPacket);
    }

}
