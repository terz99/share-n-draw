package com.example.terz99.whiteboard;

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
