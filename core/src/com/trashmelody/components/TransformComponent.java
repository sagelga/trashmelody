package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import io.vavr.control.Option;

public class TransformComponent implements Component {
    public Vector2 position;
    public Option<Vector2> size;
    public float angelRadiant;
    public float scale;
    public boolean flipX = false;
    public boolean flipY = false;
    public Align align = Align.Center;

    public enum Align {
        Center, UpperRight, UpperLeft
    }

    public TransformComponent(Vector2 position, Option<Vector2> size, float scale, float angleRadiant) {
        this.position = position;
        this.size = size;
        this.scale = scale;
        this.angelRadiant = angleRadiant;
    }

    public TransformComponent(float x, float y) {
        this(new Vector2(x, y));
    }

    public TransformComponent(Vector2 position) {
        this(position, Option.none());
    }

    public TransformComponent(float x, float y, float width, float height) {
        this(new Vector2(x, y), Option.some(new Vector2(width, height)));
    }

    public TransformComponent(float x, float y, float width, float height, Align align) {
        this(new Vector2(x, y), Option.some(new Vector2(width, height)));

        this.align = align;
    }

    public TransformComponent(float x, float y, float width, float height, float scale) {
        this(new Vector2(x, y), Option.some(new Vector2(width, height)), scale);
    }

    public TransformComponent(Vector2 position, float scale) {
        this(position, Option.none(), scale);
    }

    public TransformComponent(Vector2 position, Option<Vector2> size) {
        this(position, size, 1F);
    }

    public TransformComponent(Vector2 position, Option<Vector2> size, float scale) {
        this(position, size, scale, 0F);
    }
}
