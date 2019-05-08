package me.ksviety.plugins.mc.locations.misc;

public class Vector3 {

    private float x;
    private float y;
    private float z;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public static Vector3 zero = new Vector3(0, 0 ,0);

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(float xyz) {
        x = y = z = xyz;
    }

}
