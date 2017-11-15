package com.trashmelody.components;

import com.badlogic.ashley.core.Component;

public class ScanLineComponent implements Component {
    public Direction direction = Direction.Right;
    public float speed;

    public enum Direction {
        Left, Right
    }

    public ScanLineComponent(float speed) {
        this.speed = speed;
    }
}
