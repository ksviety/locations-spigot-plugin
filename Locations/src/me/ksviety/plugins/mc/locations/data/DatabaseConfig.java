package me.ksviety.plugins.mc.locations.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ksviety.plugins.mc.locations.pojo.Database;
import me.ksviety.plugins.mc.locations.util.FileManagement;
import me.ksviety.plugins.mc.locations.util.Files;

import java.io.*;

public class DatabaseConfig implements ILoadable, ISavable {

    private Database database = new Database();

    public Database getConfig() {
        return database;
    }

    public Database clone() {
        Database clone = new Database();

        clone.setUrl(database.getUrl());
        clone.setPassword(database.getPassword());
        clone.setUsername(database.getUsername());
        clone.setDatabase(database.getDatabase());
        clone.use(database.use());

        return clone;
    }

    @Override
    public boolean load() {
        String data;

        try {

            data = FileManagement.readFile(Files.DATABASE_CONFIG_FILE);

            database = new Gson().fromJson(data, Database.class);

        } catch (IOException e) {

            database = Database.getDefault();

            return false;
        }

        return true;
    }

    @Override
    public boolean save() {
        String data;

        try {

            data = new GsonBuilder().setPrettyPrinting().create().toJson(database);

            FileManagement.writeFile(Files.DATABASE_CONFIG_FILE, data);

        } catch (IOException e) {

            e.printStackTrace();

            return false;
        }

        return true;
    }

}
