package me.ksviety.plugins.mc.locations.pojo;

import com.google.gson.annotations.Expose;
import me.ksviety.plugins.mc.locations.util.LocationType;
import me.ksviety.plugins.mc.locations.util.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Location {
    @Expose private String name;
    @Expose private String label;
    @Expose private Vector3 firstPosition;
    @Expose private Vector3 secondPosition;
    @Expose private Vector3 warpPosition;
    @Expose private String world;
    @Expose private int priority;
    @Expose private LocationType type;
    @Expose private boolean active;

    @Expose(deserialize = false, serialize = false)
    private List<UUID> players;

    public boolean hasIn(Vector3 object) {
        float minX = Float.min(firstPosition.getX(), secondPosition.getX());
        float minY = Float.min(firstPosition.getY(), secondPosition.getY());
        float minZ = Float.min(firstPosition.getZ(), secondPosition.getZ());

        float maxX = Float.max(firstPosition.getX(), secondPosition.getX());
        float maxY = Float.max(firstPosition.getY(), secondPosition.getY());
        float maxZ = Float.max(firstPosition.getZ(), secondPosition.getZ());

        //  minX <= objectX <= maxX
        //  minZ <= objectZ <= maxZ
        //  minY <= objectY <= maxY
        if (minX <= object.getX() && maxX >= object.getX()) {

            if (minZ <= object.getZ() && maxZ >= object.getZ())
                return minY <= object.getY() && maxY >= object.getY();

        }

        return false;
    }

    //  **  GETTERS **  //

    public boolean isActive() {
        return active;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public Vector3 getFirstPosition() {
        return firstPosition;
    }

    public Vector3 getSecondPosition() {
        return secondPosition;
    }

    public Vector3 getWarpPosition() {
        return warpPosition;
    }

    public String getWorld() {
        return world;
    }

    public org.bukkit.Location getWarpLocation() {
        World world = Bukkit.getWorld(this.world);

        return new org.bukkit.Location(
                world,
                (double)warpPosition.getX(),
                (double)warpPosition.getY(),
                (double)warpPosition.getZ());
    }

    public int getPriority() {
        return priority;
    }

    public LocationType getType() {
        return type;
    }

    //  **  SETTERS **  //

    public boolean setName(String name) {

        //  Making sure it doesn't contain any spaces as they might cause trouble
        //  with using the location name as a command parameter
        if (name.contains(" "))
            return false;

        this.name = name.toLowerCase();

        return true;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFirstPosition(Vector3 position) {
        firstPosition = new Vector3(position);
    }

    public void setSecondPosition(Vector3 position) {
        secondPosition = new Vector3(position);
    }

    public boolean setWarpPosition(Vector3 position) {

        if (hasIn(position)) {

            warpPosition = new Vector3(position);

            return true;
        }

        return false;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public void addPlayer(UUID player) {
        this.players.add(player);
    }

    public void removePlayer(UUID player) {
        this.players.remove(player);
    }

    @Override
    public String toString() {
        List<String> players = new ArrayList<>();
        String finalString = "Name: %name \n" +
                "Label: %label\n" +
                "Position 1: %pos1 \n" +
                "Position 2: %pos2 \n" +
                "Warp: %warp\n" +
                "Priority: %priority\n" +
                "World: %world\n" +
                "Type: %type\n" +
                "Activity: %activity\n" +
                "Current players: [%players]";

        for (Player player: Bukkit.getOnlinePlayers()) {

            if (this.players.contains(player.getUniqueId()))
                players.add(player.getName());

        }

        finalString = finalString.replaceAll("%name", name);
        finalString = finalString.replaceAll("%pos1", firstPosition.toString());
        finalString = finalString.replaceAll("%pos2", secondPosition.toString());
        finalString = finalString.replaceAll("%warp", warpPosition.toString());
        finalString = finalString.replaceAll("%world", world);
        finalString = finalString.replaceAll("%priority", String.valueOf(priority));
        finalString = finalString.replaceAll("%type", type.name());
        finalString = finalString.replaceAll("%label", label);
        finalString = finalString.replaceAll("%players", String.join(", ", players));
        finalString = finalString.replaceAll("%activity", active? "Active": "Inactive");

        return finalString;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof String)
            return ((String) obj).equalsIgnoreCase(name);

        if ((obj instanceof Location) && ((Location) obj).getName().equalsIgnoreCase(getName()))
            return true;

        return false;
    }

    public boolean equals(String name) {

        if (name.equalsIgnoreCase(this.name))
            return true;

        return false;
    }

    public Location(String name, String label, Vector3 pos1, Vector3 pos2, Vector3 warpPosition,
                    World world, int priority, LocationType type) {

        this.name = name.toLowerCase();
        this.label = label;
        this.firstPosition = pos1;
        this.secondPosition = pos2;
        this.warpPosition = warpPosition;
        this.world = world.getName();
        this.priority = priority;
        this.type = type;
        this.active = false;

        this.players = new ArrayList<>();

    }

    public Location() {
        this.players = new ArrayList<>();
    }

}
