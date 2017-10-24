package com.trashmelody;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Utils {
    public static void drawCenter(Batch batch, Texture texture, float width, float height) {
        float viewportWidth = Gdx.graphics.getWidth();
        float viewportHeight = Gdx.graphics.getHeight();
        float horizontalCenter = (viewportWidth - width) / 2F;
        float verticalCenter = (viewportHeight - height) / 2F;

        batch.draw(texture, horizontalCenter, verticalCenter, width, height);
    }
}
