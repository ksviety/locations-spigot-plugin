package me.ksviety.plugins.mc.locations.misc;

public class Vector2 {

    public int x;
    public int y;

    public static Vector2 zero = new Vector2(0, 0);

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(int xy) {
        x = y = xy;
    }

}
