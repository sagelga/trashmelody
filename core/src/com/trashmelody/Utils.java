package com.trashmelody;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Utils {
    public static void drawCenter(Batch batch, Texture texture, float width, float height) {
        float horizontalCenter = (getViewportWidth() - width) / 2F;
        float verticalCenter = (getViewportHeight() - height) / 2F;

        batch.draw(texture, horizontalCenter, verticalCenter, width, height);
    }

    public static void clearScreen() {
        Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    public static void clearScreen(float red, float green, float blue, float alpha){
        /// Overloads clearScreen() method
        Gdx.gl.glClearColor(red, green, blue, alpha);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static float getViewportWidth() {
        return Gdx.graphics.getWidth();
    }

    public static float getViewportHeight() {
        return Gdx.graphics.getHeight();
    }

    public static boolean userSkipScene(){
        return Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.ENTER);
    }
}
