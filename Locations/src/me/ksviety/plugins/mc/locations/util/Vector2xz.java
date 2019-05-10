package me.ksviety.plugins.mc.locations.util;

public class Vector2xz {

    private int x;
    private int z;

    public static Vector2xz zero = new Vector2xz(0);

    //  **  GETTERS **

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    //  **  SETTERS **

    public void setX(int x) {
        this.x = x;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "x: @x z: @z".replace("@x", String.valueOf(x)).replace("@z", String.valueOf(z));
    }

    public Vector2xz(int xz) {
        x = xz;
        z = xz;
    }

    public Vector2xz(int x, int z) {
        this.x = x;
        this.z = z;
    }

}
