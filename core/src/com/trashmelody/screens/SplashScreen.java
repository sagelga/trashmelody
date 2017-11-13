package com.trashmelody.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.*;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.MusicManager;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.utils.Debugger;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class SplashScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screens;
    private MusicManager musicManager;
    private Texture splashScreenLogo;
    public static Music splashScreenMusic;

    private long time_lapsed;

    @Inject
    SplashScreen(TrashMelody game, ScreenProvider screens, MusicManager musicManager) {
        this.game = game;
        this.screens = screens;
        this.musicManager = musicManager;
    }

    @Override
    public void show() { // Run while screen is active
        musicManager.playMusic(MUSIC_BG1);
        musicManager.setDefault(MUSIC_BG1);
        time_lapsed = TimeUtils.millis();
    }

    @Override
    public void render(float delta) { // Continuously run during active
        clearScreen();

        if (TimeUtils.timeSinceMillis(time_lapsed) > 5000) {
            game.setScreen(screens.get(LoadingScreen.class));
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
        screens.get(WarningScreen.class).load(assets);
        screens.get(MenuScreen.class).load(assets);
        screens.get(SandboxScreen.class).load(assets);
        screens.get(NameScreen.class).load(assets);
    }
}
