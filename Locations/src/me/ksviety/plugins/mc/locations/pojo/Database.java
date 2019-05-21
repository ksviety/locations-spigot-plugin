package me.ksviety.plugins.mc.locations.pojo;

import me.ksviety.plugins.mc.locations.data.database.IDatabase;

public class Database {

    private IDatabase.Database database;
    private String username;
    private String password;
    private String url;
    private boolean use;

    //  GETTERS

    public IDatabase.Database getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public boolean use() {
        return use;
    }

    //  SETTERS

    public void setDatabase(IDatabase.Database database) {
        this.database = database;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void use(boolean use) {
        this.use = use;
    }

    public static Database getDefault() {
        Database database = new Database();

        database.setDatabase(IDatabase.Database.MYSQL);
        database.setUsername("username");
        database.setPassword("123");
        database.setUrl("jdbc:mysql://localhost:3306/database");
        database.use(false);

        return database;
    }

}
