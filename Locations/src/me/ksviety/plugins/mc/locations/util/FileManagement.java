package me.ksviety.plugins.mc.locations.util;

import me.ksviety.plugins.mc.locations.Plugin;

import java.io.*;
import java.nio.charset.Charset;

public class FileManagement {

    public static String readFile(File file) throws IOException {
        String currentLine;
        StringBuilder finalString = new StringBuilder();
        FileInputStream input = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));

        while ( (currentLine = bufferedReader.readLine()) != null )
            finalString.append(currentLine).append("\n");

        bufferedReader.close();

        return finalString.toString();
    }

    public static void writeFile(File file, String content) throws IOException {
        FileWriter writer;

        file.getParentFile().mkdirs();

        writer = new FileWriter(file);

        writer.write(content);
        writer.flush();

        writer.close();
    }

    public static String readResourceFile(String resource) throws IOException {
        String currentLine;
        StringBuilder finalString = new StringBuilder();
        InputStream inputStream = Plugin.class.getResourceAsStream(resource);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        while ( (currentLine = bufferedReader.readLine()) != null )
            finalString.append(currentLine).append("\n");

        bufferedReader.close();

        return finalString.toString();
    }

}
