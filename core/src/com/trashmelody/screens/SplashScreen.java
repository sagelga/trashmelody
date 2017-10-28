package com.trashmelody.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.trashmelody.Assets;
import com.trashmelody.TrashMelody;

import javax.inject.Inject;

import static com.trashmelody.Utils.clearScreen;
import static com.trashmelody.Utils.drawCenter;

public class SplashScreen extends ScreenAdapter {
    private TrashMelody game;
    private MenuScreen menuScreen;
    private Texture splashScreenLogo;

    @Inject
    public SplashScreen(TrashMelody game, Assets assets, MenuScreen menuScreen) {
        this.game = game;
        this.menuScreen = menuScreen;

        splashScreenLogo = assets.getSplashScreenLogo();
    }

    @Override
    public void render(float delta) {
        clearScreen();

        if (Gdx.input.justTouched()) {
            game.setScreen(menuScreen);
        }

        game.batch.begin();
        drawCenter(game.batch, splashScreenLogo, 500F, 286F);
        game.font.draw(game.batch, "Splash Screen", 30, 40);
        game.batch.end();
    }
}
