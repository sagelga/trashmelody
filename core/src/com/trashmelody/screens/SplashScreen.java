package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;

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
    private StageSelectScreen stageSelectScreen;
    private CollectionScreen collectionScreen;
    private SandboxScreen sandboxScreen;
    private int count = 0;

    @Inject
    public SplashScreen(TrashMelody game, Assets assets, MenuScreen menuScreen, SettingsScreen settingsScreen,
                        WarningScreen warningScreen, StageSelectScreen stageSelectScreen, CollectionScreen collectionScreen,
                        SandboxScreen sandboxScreen) {
        this.game = game;
        this.warningScreen = warningScreen;
        this.settingsScreen = settingsScreen;
        this.menuScreen = menuScreen;
        this.stageSelectScreen = stageSelectScreen;
        this.collectionScreen = collectionScreen;
        this.sandboxScreen = sandboxScreen;
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
        if (count >= 1000) {
            game.setScreen(warningScreen);
        }
        if (userSkipScene() && (count > 500)) {
            // Speed up the delay time by doing userSkipScene() pre-defined methods.
            count += 100;
        }
        count += 5;

        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            game.setScreen(menuScreen); // For speeding up development
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            game.setScreen(settingsScreen); // For speeding up development
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)){
            game.setScreen(stageSelectScreen);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)){
            game.setScreen(collectionScreen);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            game.setScreen(sandboxScreen);
        }

        // Start loading assets
        game.batch.begin();
        drawCenter(game.batch, splashScreenLogo, 500F, 286F);

        // Debug zone
        Debugger.runDebugger(game.batch, game.font,"Splash Screen");
        Debugger.runAdvancedDebugger(game.batch,game.font,0,count/10);
        // Debug zone

        game.batch.end();
    }
}

