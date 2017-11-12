package com.trashmelody.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.trashmelody.*;

import static com.trashmelody.Assets.*;
import static com.trashmelody.Utils.clearScreen;
import static com.trashmelody.Utils.drawCenter;

@Singleton
public class SplashScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screenProvider;
    private Provider<LoadingScreen> loadingScreen;
    private MusicManager musicManager;
    private Assets assets;
    private Texture splashScreenLogo;
    public static Music splashScreenMusic;

    private long time_lapsed;

    @Inject
    public SplashScreen(TrashMelody game, Assets assets, ScreenProvider screenProvider, MusicManager musicManager) {
        this.game = game;
        this.assets = assets;
        this.screenProvider = screenProvider;
        this.musicManager = musicManager;
        this.loadingScreen = screenProvider.getProvider(LoadingScreen.class);
    }

    @Override
    public void show(){ // Run while screen is active
        musicManager.playMusic(MUSIC_BG1);
        musicManager.setDefault(MUSIC_BG1);
        time_lapsed = TimeUtils.millis();
    }

    @Override
    public void render(float delta) { // Continuously run during active
        clearScreen();

        if (TimeUtils.timeSinceMillis(time_lapsed) > 5000) {
            game.setScreen(loadingScreen.get());
        }

        // Start loading assets
        game.batch.begin();
        drawCenter(game.batch, splashScreenLogo, 500F, 286F);

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Splash Screen",TimeUtils.timeSinceMillis(time_lapsed));
        // Debug zone

        game.batch.end();
    }

    @Override
    protected void loadAssets(Assets assets) {
        assets.load(SPLASH_LOGO, TEXTURE);
        assets.load(MUSIC_BG1, MUSIC);
    }

    @Override
    public void afterLoad(Assets assets) {
        splashScreenLogo = assets.get(SPLASH_LOGO, TEXTURE);
        splashScreenMusic = assets.get(MUSIC_BG1, MUSIC);
        screenProvider.get(WarningScreen.class).load(assets);
        screenProvider.get(MenuScreen.class).load(assets);
        screenProvider.get(SandboxScreen.class).load(assets);
        screenProvider.get(NameScreen.class).load(assets);
    }
}
