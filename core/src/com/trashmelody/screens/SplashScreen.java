package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.google.inject.Singleton;
import com.trashmelody.*;

import javax.inject.Inject;

import static com.trashmelody.Utils.clearScreen;
import static com.trashmelody.Utils.drawCenter;
import static com.trashmelody.Assets.MUSIC_BG1;


@Singleton
public class SplashScreen extends ScreenAdapter {
    private TrashMelody game;
    ScreenProvider screenProvider;
    private MusicManager musicManager;
    private Assets assets;
    private Texture splashScreenLogo;
    public static Music splashScreenMusic;
    private long time_lapsed = TimeUtils.millis();

    @Inject
    public SplashScreen(TrashMelody game, Assets assets, ScreenProvider screenProvider, MusicManager musicManager) {
        this.game = game;
        this.assets = assets;
        this.screenProvider = screenProvider;
        this.musicManager = musicManager;

        this.splashScreenLogo = assets.get(Assets.SPLASH_LOGO, Assets.TEXTURE);
        splashScreenMusic = assets.get(Assets.MUSIC_BG1,Assets.MUSIC);
    }

    @Override
    public void show(){ // Run while screen is active
        musicManager.playMusic(MUSIC_BG1);
    }

    @Override
    public void render(float delta) { // Continuously run during active
        clearScreen();

        if (TimeUtils.timeSinceMillis(time_lapsed) > 5000) {
            game.setScreen(screenProvider.get(LoadingScreen.class));
        }

        // Start loading assets
        game.batch.begin();
        drawCenter(game.batch, splashScreenLogo, 500F, 286F);

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Splash Screen",splashScreenMusic.getVolume(),TimeUtils.timeSinceMillis(time_lapsed),assets.getProgress());
        // Debug zone

        game.batch.end();
    }

}
