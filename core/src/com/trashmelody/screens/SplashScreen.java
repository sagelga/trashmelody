package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private Texture splashScreenLogo;
    public static Music splashScreenMusic;
    private long time_lapsed = TimeUtils.millis();

    @Inject
    public SplashScreen(TrashMelody game, ScreenProvider screenProvider) {
        this.game = game;
        this.screenProvider = screenProvider;
        this.loadingScreen = screenProvider.getProvider(LoadingScreen.class);
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
            game.setScreen(loadingScreen.get());
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
