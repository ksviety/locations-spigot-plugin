package me.ksviety.plugins.mc.locations.util.NMS;

import me.ksviety.plugins.mc.locations.util.NMS.objects.Text;
import org.bukkit.entity.Player;

public interface NMS {

    String v1_12_R1 = "v1_12_R1";
    String v1_13_R1 = "v1_13_R1";
    String v1_13_R2 = "v1_13_R2";
    String v1_14_R1 = "v1_14_R1";

    void sendGameInfo(Player p, String s);

    void sendTitle(Player p, Text title, Text subTitle, int fadeIn, int stay, int fadeOut);

}
