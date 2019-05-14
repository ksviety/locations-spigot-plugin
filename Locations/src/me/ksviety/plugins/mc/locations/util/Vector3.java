package me.ksviety.plugins.mc.locations.util;

public class Vector3 extends Vector2xz {

    private float y;

    public static final Vector3 zero = new Vector3(0);

    //  **  GETTERS **

    public float getY() {
        return y;
    }

    //  **  SETTERS **

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: @x y: @y z: @z"
                .replace("@x", String.valueOf(super.getX()))
                .replace("@z", String.valueOf(super.getZ()))
                .replace("@y", String.valueOf(y));
    }

    public Vector3(float xyz) {
        super(xyz);
        y = xyz;
    }

    public Vector3(float x, float y, float z) {
        super(x, z);
        this.y = y;
    }

    public Vector3(Vector3 vector) {
        super(vector);
        y = vector.getY();
    }

}
