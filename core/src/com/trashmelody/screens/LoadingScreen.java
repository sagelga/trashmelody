package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.google.inject.Provider;
import com.trashmelody.*;
import com.trashmelody.utils.GifDecoder;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.trashmelody.Assets.LOADING_LOGO;
import static com.trashmelody.Assets.MUSIC;
import static com.trashmelody.Utils.*;

@Singleton
public class LoadingScreen extends ScreenAdapter {
    private TrashMelody game;
    private Assets assets;
    private Provider<WarningScreen> warningScreen;
    private LazyScreen nextScreen;
    private Animation<TextureRegion> loadingScreenLogo;
    private Music loadingScreenMusic;

    public  long time_lapsed;
    float elapsed;

    @Inject
    public LoadingScreen(TrashMelody game, Assets assets, ScreenProvider screenProvider) {
        this.game = game;
        this.assets = assets;
        this.warningScreen = screenProvider.getProvider(WarningScreen.class);
        this.loadingScreenLogo = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal(LOADING_LOGO).read());
    }

    @Override
    public void show(){
        time_lapsed = TimeUtils.millis();
    }

    @Override
    public void render(float delta) { // Continuously run during active
        clearScreen(253,243,255,1);
        elapsed += delta;

        if(assets.update()){
            if (nextScreen != null) {
                nextScreen.getLazyAssets(assets);
                game.setScreen(nextScreen);
            } else {
                game.setScreen(warningScreen.get());
            }
        }

        // Start loading assets
        game.batch.begin();
        game.batch.draw(loadingScreenLogo.getKeyFrame(elapsed), getCenterX()/2, getCenterY()/2);

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Loading Screen",TimeUtils.timeSinceMillis(time_lapsed),assets.getProgress());
        // Debug zone

        game.batch.end();
    }

    public void setNextScreen(LazyScreen nextScreen) {
        this.nextScreen = nextScreen;
    }
}

