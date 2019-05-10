package me.ksviety.plugins.mc.locations.misc;

public class Vector2xz {

    public int x;
    public int z;

    public static Vector2xz zero = new Vector2xz(0, 0);

    public Vector2xz(int x, int z) {
        this.x = x;
        this.z = z;
    }

    @Override
    public String toString() {
        return "x: @x z: @z".replace("@x", String.valueOf(x)).replace("@z", String.valueOf(z));
    }

    public Vector2xz(int xy) {
        x = z = xy;
    }

}
