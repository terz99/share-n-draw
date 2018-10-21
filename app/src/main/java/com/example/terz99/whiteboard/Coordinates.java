package com.example.terz99.whiteboard;

/**
 * Represents the coordinates of the fingerpaths (the lines which are drawn on the screen).
 */
public class Coordinates {
    private float x;
    private float y;
    private String id;
    private int action;

    public Coordinates(){
        /* Empty for Firebase to be able to deserialize coordinates */
    }

    public Coordinates(float x, float y, String id, int action) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.action = action;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getId() {
        return id;
    }

    public int getAction() {
        return action;
    }
}
