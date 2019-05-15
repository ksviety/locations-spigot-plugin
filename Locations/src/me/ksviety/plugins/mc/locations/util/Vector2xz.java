package me.ksviety.plugins.mc.locations.util;

import com.google.gson.annotations.Expose;

public class Vector2xz {

    @Expose private float x;
    @Expose private float z;

    public static Vector2xz zero = new Vector2xz(0);

    public static float getDistance(Vector2xz a, Vector2xz b) {
        return (float)Math.sqrt( Math.pow( a.getX() - b.getX(), 2 ) + Math.pow( a.getZ() - b.getZ(), 2 ) );
    }

    //  **  GETTERS **

    public float getX() {
        return x;
    }

    public float getZ() {
        return z;
    }

    //  **  SETTERS **

    public void setX(float x) {
        this.x = x;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "x: @x z: @z".replace("@x", String.valueOf(x)).replace("@z", String.valueOf(z));
    }

    public Vector2xz(float xz) {
        x = xz;
        z = xz;
    }

    public Vector2xz(float x, float z) {
        this.x = x;
        this.z = z;
    }

    public Vector2xz(Vector2xz vector) {
        x = vector.getX();
        z = vector.getZ();
    }

}
