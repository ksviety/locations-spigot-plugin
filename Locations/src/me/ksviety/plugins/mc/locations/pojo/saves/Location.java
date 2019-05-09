package me.ksviety.plugins.mc.locations.pojo.saves;

import me.ksviety.plugins.mc.locations.misc.Vector2;

public class Location {

    private String name;
    private String label;
    private Vector2 pos1;
    private Vector2 pos2;
    private Vector2 warpPosition;

    //  **  GETTERS **  //

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public Vector2 getPos1() {
        return pos1;
    }

    public Vector2 getPos2() {
        return pos2;
    }

    public Vector2 getWarpPosition() {
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

    public void setPos1(Vector2 pos1) {
        this.pos1 = pos1;
    }

    public void setPos2(Vector2 pos2) {
        this.pos2 = pos2;
    }

    //  TODO add validation if the new warp position is located inside the cube of pos1 and pos2
    public boolean setWarpPosition(Vector2 position) {

        warpPosition = position;

        return true;
    }

    public Location(String name, String label, Vector2 pos1, Vector2 pos2, Vector2 warpPosition) {

        this.name = name.toLowerCase();
        this.label = label;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.warpPosition = warpPosition;

    }

}
