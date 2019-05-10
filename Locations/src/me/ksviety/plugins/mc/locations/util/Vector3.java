package me.ksviety.plugins.mc.locations.util;

public class Vector3 extends Vector2xz {

    private int y;

    //  **  GETTERS **

    public int getY() {
        return y;
    }

    //  **  SETTERS **

    public void setY(int y) {
        this.y = y;
    }

    public Vector3(int xyz) {
        super(xyz);
        y = xyz;
    }

    public Vector3(int x, int y, int z) {
        super(x, z);
        y = y;
    }

    public Vector3(Vector3 vector) {
        super(vector);
        y = vector.getY();
    }

}
