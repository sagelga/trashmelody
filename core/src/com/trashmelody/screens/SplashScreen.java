package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.trashmelody.Assets;
import com.trashmelody.TrashMelody;
import com.trashmelody.Utils;

import javax.inject.Inject;

import static com.trashmelody.Utils.clearScreen;
import static com.trashmelody.Utils.drawCenter;
import static io.vavr.API.println;

public class SplashScreen extends ScreenAdapter {
    private TrashMelody game;
    private MenuScreen menuScreen;
    private Texture splashScreenLogo;
    private SettingsScreen settingsScreen;

    @Inject
    public SplashScreen(TrashMelody game, Assets assets, MenuScreen menuScreen,
                        SettingsScreen settingsScreen) {
        this.game = game;
        this.menuScreen = menuScreen;
        this.splashScreenLogo = assets.getSplashScreenLogo();
        this.settingsScreen = settingsScreen;
    }

    @Override
    public void render(float delta) {
        clearScreen();

        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            game.setScreen(menuScreen);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            game.setScreen(settingsScreen);
        }

        game.batch.begin();
        drawCenter(game.batch, splashScreenLogo, 500F, 286F);
        game.font.draw(game.batch, "Splash Screen", 30, 40);
        game.batch.end();
    }
}
