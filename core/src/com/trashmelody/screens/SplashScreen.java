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
import static com.trashmelody.Utils.userSkipScene;

public class SplashScreen extends ScreenAdapter {
    private TrashMelody game;
    private WarningScreen warningScreen;
    private Texture splashScreenLogo;
    private SettingsScreen settingsScreen;
    private MenuScreen menuScreen;
    private NameScreen nameScreen;
    private int count;

    @Inject
    public SplashScreen(TrashMelody game, Assets assets, MenuScreen menuScreen,
                        SettingsScreen settingsScreen, WarningScreen warningScreen,NameScreen nameScreen) {
        this.game = game;
        this.warningScreen = warningScreen;
        this.settingsScreen = settingsScreen;
        this.menuScreen = menuScreen;
        this.nameScreen = nameScreen;
        this.splashScreenLogo = assets.getSplashScreenLogo();
    }

    @Override
    public void render(float delta) {
        clearScreen();

        if (Gdx.input.justTouched()) {
            game.setScreen(warningScreen);
        }
        /* NOTE : isTouched() will be triggered once. Holding the screen will trigger this once.
           justTouched() can be triggered multiple times  Holding the screen will also triggers */
        if (count >= 500) {
            game.setScreen(warningScreen);
        }
        if (userSkipScene()) {
            // Speed up the delay time with SkipScene()
            count += 100;
        }
        count++;

        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            game.setScreen(nameScreen);
        }

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

