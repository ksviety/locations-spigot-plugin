package me.ksviety.plugins.mc.locations.pojo;

import com.google.gson.annotations.JsonAdapter;

public class Settigns {

    //  Placeholders
    public static final String LOCATION_NAME_PLACEHOLDER = "%LOCATION_NAME%";
    public static final String COMMAND_PLACEHOLDER = "%COMMAND%";

    //  Default command messages
    public final String defaultRanSuccessfullyMessage = COMMAND_PLACEHOLDER + " has ran successfully.";
    public final String defaultRanUnsuccessfullyMessage = COMMAND_PLACEHOLDER + " has ran unsuccessfully.";

    //  Display messages
    public final String unlockedLocationMessage = "Unlocked location " + LOCATION_NAME_PLACEHOLDER;


}
