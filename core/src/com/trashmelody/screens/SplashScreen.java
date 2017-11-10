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
    private ResultScreen resultScreen;
    private int count = 0;

    @Inject
    public SplashScreen(TrashMelody game, Assets assets, MenuScreen menuScreen, SettingsScreen settingsScreen,
                        WarningScreen warningScreen, StageSelectScreen stageSelectScreen, CollectionScreen collectionScreen,
                        SandboxScreen sandboxScreen, ResultScreen resultScreen) {
        this.game = game;

        this.warningScreen = warningScreen;
        this.settingsScreen = settingsScreen;
        this.menuScreen = menuScreen;
        this.stageSelectScreen = stageSelectScreen;
        this.collectionScreen = collectionScreen;
        this.sandboxScreen = sandboxScreen;
        this.resultScreen = resultScreen;
        this.splashScreenLogo = assets.get(Assets.SPLASH_LOGO, Assets.TEXTURE);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        if (count >= 1000) {
            game.setScreen(warningScreen);
        }
        if (userSkipScene() && (count > 200)) {
            // Speed up the delay time by doing userSkipScene() pre-defined methods.
            count += 200;
        }
        count += 5;

        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            game.setScreen(menuScreen);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            game.setScreen(settingsScreen);
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            game.setScreen(resultScreen);
        }

        // Start loading assets
        game.batch.begin();
        drawCenter(game.batch, splashScreenLogo, 500F, 286F);

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            Debugger.debug_mode = !Debugger.debug_mode;
        }
        if (Debugger.debug_mode){
            Debugger.runDebugger(game.batch, game.font,"Splash Screen");
            Debugger.runAdvancedDebugger(game.batch,game.font,0,count/10);
        }
        // Debug zone

        game.batch.end();
    }
}

