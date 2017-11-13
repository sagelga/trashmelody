package com.trashmelody.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static io.vavr.API.println;

public class RenderingUtils {
    public static void drawCenter(Batch batch, Texture texture, float width, float height) {
        float horizontalCenter = (getViewportWidth() - width) / 2F;
        float verticalCenter = (getViewportHeight() - height) / 2F;

        batch.draw(texture, horizontalCenter, verticalCenter, width, height);
    }

    public static void drawCenter(Batch batch, TextureRegion texture, float width, float height) {
        float horizontalCenter = (getViewportWidth() - width) / 2F;
        float verticalCenter = (getViewportHeight() - height) / 2F;

        batch.draw(texture, horizontalCenter, verticalCenter, width, height);
    }

    public static void drawCenterX(Batch batch, Texture texture, float width, float height, float posY) {
        float horizontalCenter = (getViewportWidth() - width) / 2F;

        batch.draw(texture, horizontalCenter, posY, width, height);
    }

    public static void drawCenterY(Batch batch, Texture texture, float width, float height, float posX) {
        float verticalCenter = (getViewportHeight() - height) / 2F;

        batch.draw(texture, posX, verticalCenter, width, height);
    }

    public static void clearScreen() {
        clearScreen(255F, 255F, 255F, 255F);
    }
    public static void clearScreen(float red, float green, float blue, float alpha){
        Gdx.gl.glClearColor(red / 255F, green / 255F, blue / 255F, alpha);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static int getViewportWidth() {
        return Gdx.graphics.getWidth();
    }

    public static int getViewportHeight() {
        return Gdx.graphics.getHeight();
    }

    public static float getCenterX() {
        return getViewportWidth()/2;
    }

    public static float getCenterY() {
        return getViewportHeight()/2;
    }

    public static float getScreenPpi() {
        return Gdx.graphics.getPpiX();
    }

    public static void logInputCoordinate() {
        println(String.format("(X: %d, Y:%d)", Gdx.input.getX(), Gdx.input.getY()));
    }

    public static boolean userSkipScene(){
        return Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.ENTER);
    }

    public static float findRatio(float w, float h, float newSize, char wOrH) {
        /* w = original width ratio
         * h = original height ratio
         * newSize = new width or height
         * wOrH = find width or height? */
        if (wOrH == 'w') {
            float newWidth = newSize;
            return ((float)w/h) * newWidth;
        } else if (wOrH == 'h') {
            float newHeight = newSize;
            return ((float)h/w) * newHeight;
        } else return 0;
    }

}
