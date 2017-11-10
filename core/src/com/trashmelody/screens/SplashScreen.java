package com.trashmelody.screens;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;

import javax.inject.Inject;
import com.google.inject.Provider;

import static com.trashmelody.Utils.*;

public class SplashScreen extends ScreenAdapter {
    private TrashMelody game;
    private Assets assets;
    private Provider<WarningScreen> warningScreen;
    private Texture splashScreenLogo;
    private Music splashScreenMusic;


    private int count = 0;

    @Inject
    public SplashScreen(TrashMelody game, Assets assets, Provider<WarningScreen> warningScreen) {
        this.game = game;
        this.assets = assets;
        this.warningScreen = warningScreen;

        this.splashScreenLogo = assets.get(Assets.SPLASH_LOGO, Assets.TEXTURE);
        this.splashScreenMusic = assets.get(Assets.MUSIC_BG1,Assets.MUSIC);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        if(assets.assetManager.update()){
            splashScreenMusic.dispose();
            splashScreenMusic.pause();
            game.setScreen(warningScreen.get());
        }

        // Start loading assets
        game.batch.begin();
        drawCenter(game.batch, splashScreenLogo, 500F, 286F);
        splashScreenMusic.play();

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Splash Screen");
        // Debug zone

        game.batch.end();
    }

}
