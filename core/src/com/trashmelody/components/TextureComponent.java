package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class TextureComponent implements Component {
    public Texture texture;
    public Color color = Color.WHITE;

    public TextureComponent(Texture texture) {
        this.texture = texture;
    }

    public TextureComponent(Texture texture, Color color) {
        this.texture = texture;
        this.color = color;
    }

    public void setAlpha(float alpha) {
        color.a = alpha;
    }
}
