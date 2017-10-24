package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trashmelody.TrashMelody;
import com.trashmelody.Utils;

import javax.inject.Inject;

public class SplashScreen extends ScreenAdapter {
    private SpriteBatch batch;
    public Texture img;
    public int scrHeight;
    public int scrWidth;

    @Inject
    public SplashScreen(SpriteBatch batch) {
        this.batch = batch;

        img = new Texture("splash-logo.png");
        scrHeight = Gdx.graphics.getHeight();
        scrWidth = Gdx.graphics.getWidth();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(img, Utils.alignCenter(scrWidth, 250), Utils.alignCenter(scrHeight, 143), 500, 286);
        batch.end();
    }

}
