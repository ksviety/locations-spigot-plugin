package me.ksviety.plugins.mc.locations.pojo;

import me.ksviety.plugins.mc.locations.util.Vector2xz;
import me.ksviety.plugins.mc.locations.util.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class Location {

    private String name;
    private String label;
    private Vector2xz firstPosition;
    private Vector2xz secondPosition;
    private Vector3 warpPosition;
    private String world;

    //  **  GETTERS **  //

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public Vector2xz getFirstPosition() {
        return firstPosition;
    }

    public Vector2xz getSecondPosition() {
        return secondPosition;
    }

    public Vector2xz getWarpPosition() {
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

    public void setFirstPosition(Vector2xz position) {
        firstPosition = new Vector2xz(position);
    }

    public void setSecondPosition(Vector2xz position) {
        secondPosition = new Vector2xz(position);
    }

    //  TODO add validation if the new warp position is located inside the cube of firstPosition and secondPosition
    public boolean setWarpPosition(Vector3 position) {

        warpPosition = new Vector3(position);

        return true;
    }

    @Override
    public String toString() {
        String finalString = "Name: %name \nPosition 1: %pos1 \nPosition 2: %pos2 \nWarp: %warp ";

        finalString = finalString.replaceAll("%name", name);
        finalString = finalString.replaceAll("%pos1", firstPosition.toString());
        finalString = finalString.replaceAll("%pos2", secondPosition.toString());
        finalString = finalString.replaceAll("%warp", warpPosition.toString());

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

    public Location(String name, String label, Vector2xz pos1, Vector2xz pos2, Vector3 warpPosition, World world) {

        this.name = name.toLowerCase();
        this.label = label;
        this.firstPosition = pos1;
        this.secondPosition = pos2;
        this.warpPosition = warpPosition;
        this.world = world.getName();

    }

}
