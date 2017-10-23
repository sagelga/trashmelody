package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.trashmelody.TrashMelody;
import com.trashmelody.Utils;

public class SplashScreen extends ScreenAdapter {
    private TrashMelody game;
    public Texture img;
    public int scrHeight;
    public int scrWidth;

    public SplashScreen(TrashMelody game) {
        this.game = game;
        img = new Texture("splash-logo.png");
        scrHeight = Gdx.graphics.getHeight();
        scrWidth = Gdx.graphics.getWidth();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(img, Utils.alignCenter(scrWidth, 250), Utils.alignCenter(scrHeight, 143), 500, 286);
        game.batch.end();
    }

}
