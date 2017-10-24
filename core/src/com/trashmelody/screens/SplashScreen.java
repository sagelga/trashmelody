package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trashmelody.Assets;
import com.trashmelody.Utils;

import javax.inject.Inject;

public class SplashScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private Texture splashScreenLogo;

    @Inject
    public SplashScreen(SpriteBatch batch, Assets assets) {
        this.batch = batch;

        splashScreenLogo = assets.getSplashScreenLogo();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        Utils.drawCenter(batch, splashScreenLogo, 500F, 286F);
        batch.end();
    }
}
