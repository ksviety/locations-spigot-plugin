package me.ksviety.plugins.mc.locations.pojo;

import me.ksviety.plugins.mc.locations.util.LocationType;
import me.ksviety.plugins.mc.locations.util.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

public class Location {
    private String name;
    private String label;
    private Vector3 firstPosition;
    private Vector3 secondPosition;
    private Vector3 warpPosition;
    private String world;
    private int priority;
    private LocationType type;

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

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String finalString = "Name: %name \n" +
                "Position 1: %pos1 \n" +
                "Position 2: %pos2 \n" +
                "Warp: %warp\n" +
                "Priority: %priority\n" +
                "World: %world\n" +
                "Type: %type";

        finalString = finalString.replaceAll("%name", name);
        finalString = finalString.replaceAll("%pos1", firstPosition.toString());
        finalString = finalString.replaceAll("%pos2", secondPosition.toString());
        finalString = finalString.replaceAll("%warp", warpPosition.toString());
        finalString = finalString.replaceAll("%world", world);
        finalString = finalString.replaceAll("%priority", String.valueOf(priority));
        finalString = finalString.replaceAll("%type", type.name());

        return finalString;
    }

    @Override
    public boolean equals(Object obj) {

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

    }

}
