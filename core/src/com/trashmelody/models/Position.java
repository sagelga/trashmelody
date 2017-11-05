package com.trashmelody.models;

public class Position {
    public float x;
    public float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(float x, float y) {
        return new Position(x, y);
    }
}
