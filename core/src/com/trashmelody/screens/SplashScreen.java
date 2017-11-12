package com.trashmelody.screens;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;

import javax.inject.Inject;
import com.google.inject.Provider;

import static com.trashmelody.Utils.*;

public class SplashScreen extends ScreenAdapter {
    private TrashMelody game;
    private Assets assets;
    private LoadingScreen loadingScreen;
    private Texture splashScreenLogo;
    public static Music splashScreenMusic;

    private long time_lapsed = TimeUtils.millis();

    @Inject
    public SplashScreen(TrashMelody game, Assets assets,LoadingScreen loadingScreen) {
        this.game = game;
        this.assets = assets;
        this.loadingScreen = loadingScreen;

        this.splashScreenLogo = assets.get(Assets.SPLASH_LOGO, Assets.TEXTURE);
        splashScreenMusic = assets.get(Assets.MUSIC_BG1,Assets.MUSIC);
    }

    @Override
    public void show(){ // Run while screen is active
        splashScreenMusic.setVolume(0.3F); // for development phase only
        splashScreenMusic.play();
        splashScreenMusic.setLooping(true);
    }

    @Override
    public void render(float delta) { // Continuously run during active
        clearScreen();
        if (TimeUtils.timeSinceMillis(time_lapsed) > 5000) {
            game.setScreen(loadingScreen);
        }

        // Start loading assets
        game.batch.begin();
        drawCenter(game.batch, splashScreenLogo, 500F, 286F);

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Splash Screen",splashScreenMusic.getVolume(),TimeUtils.timeSinceMillis(time_lapsed),0);
        // Debug zone

        game.batch.end();
    }

}
