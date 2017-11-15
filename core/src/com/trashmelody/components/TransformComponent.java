package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.trashmelody.entities.ScanLine;

public class TransformComponent implements Component {
    public Vector2 position;
    public Vector2 size;
    public float angelRadiant;
    public float scale;

    public TransformComponent(Vector2 position, float scale, float angleRadiant) {
        this.position = position;
        this.scale = scale;
        this.angelRadiant = angleRadiant;
    }

    public TransformComponent(Vector2 position,
                              float scale) {
        this(position, scale, 0F);
    }

    public TransformComponent(Vector2 position) {
        this(position, 1F);
    }
}
