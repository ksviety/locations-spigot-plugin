package me.ksviety.plugins.mc.locations.util;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    //  Input values: [Test, Test2, Something, Anything]
    //  Input key: Te
    //  output: [Test, Test2]
    public static List<String> clarificate(@NotNull String key, @NotNull List<String> values) {
        List<String> output = new ArrayList<>();

        for (String value: values) {

            if (value.startsWith(key))
                output.add(value);

        }

        return output;
    }

    //  Input values: [Test, Test2, Something, Anything]
    //  Input key: som
    //  output: [Something]
    public static List<String> clarificateIgnoreCase(@NotNull String key, @NotNull List<String> values) {
        List<String> output = new ArrayList<>();

        for (String value: values) {

            if (value.toLowerCase().startsWith(key.toLowerCase()))
                output.add(value);

        }

        return output;
    }

}
