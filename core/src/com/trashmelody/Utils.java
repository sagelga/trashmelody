package com.trashmelody;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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

    public static void drawCenterX(Batch batch, Texture texture, float width, float height, float posY) {
        float horizontalCenter = (getViewportWidth() - width) / 2F;

        batch.draw(texture, horizontalCenter, posY, width, height);
    }

    public static void clearScreen() {
        Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
