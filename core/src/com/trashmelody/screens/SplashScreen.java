package com.trashmelody.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trashmelody.Assets;

import javax.inject.Inject;

import static com.trashmelody.Utils.clearScreen;
import static com.trashmelody.Utils.drawCenter;

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
        clearScreen();

        batch.begin();
        drawCenter(batch, splashScreenLogo, 500F, 286F);
        batch.end();
    }
}
