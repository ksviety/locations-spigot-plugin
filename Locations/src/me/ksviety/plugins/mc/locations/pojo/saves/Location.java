package me.ksviety.plugins.mc.locations.pojo.saves;

import me.ksviety.plugins.mc.locations.util.Vector2xz;

public class Location {

    private String name;
    private String label;
    private Vector2xz firstPosition;
    private Vector2xz secondPosition;
    private Vector2xz warpPosition;

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
        firstPosition = position;
    }

    public void setSecondPosition(Vector2xz position) {
        secondPosition = position;
    }

    //  TODO add validation if the new warp position is located inside the cube of firstPosition and secondPosition
    public boolean setWarpPosition(Vector2xz position) {

        warpPosition = position;

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

    public Location(String name, String label, Vector2xz pos1, Vector2xz pos2, Vector2xz warpPosition) {

        this.name = name.toLowerCase();
        this.label = label;
        this.firstPosition = pos1;
        this.secondPosition = pos2;
        this.warpPosition = warpPosition;

    }

}
