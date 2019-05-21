package me.ksviety.plugins.mc.locations.data.database;

import me.ksviety.plugins.mc.locations.Plugin;

public class DatabaseManager {

    private IDatabase fallbackDatabase = new MySQL();

    public IDatabase getDatabase() {
        return fallbackDatabase;
    }

    public String getInfo() {
        StringBuilder finalString = new StringBuilder();

        finalString.append("Database: ").append(Plugin.databaseConfig.getConfig().getDatabase()).append('\n');
        finalString.append("Username: ").append(Plugin.databaseConfig.getConfig().getUsername()).append('\n');
        finalString.append("Password: ").append(Plugin.databaseConfig.getConfig().getPassword()).append('\n');
        finalString.append("URL: ").append(Plugin.databaseConfig.getConfig().getUrl()).append('\n');
        finalString.append("Use: ").append(Plugin.databaseConfig.getConfig().use()).append('\n');

        return finalString.toString();
    }

}
