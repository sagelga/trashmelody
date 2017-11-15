package com.trashmelody.components;

import com.badlogic.ashley.core.Component;

public class ScanLineComponent implements Component {
    public enum Direction {
        Left, Right;
    }


    public Direction direction = Direction.Left;
}
