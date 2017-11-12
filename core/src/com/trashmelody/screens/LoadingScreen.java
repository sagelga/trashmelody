package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;
import com.trashmelody.utils.GifDecoder;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.trashmelody.Assets.LOADING_LOGO;
import static com.trashmelody.Utils.*;

@Singleton
public class LoadingScreen extends ScreenAdapter {
    private TrashMelody game;
    private Assets assets;
    private Provider<WarningScreen> warningScreen;
    private Animation<TextureRegion> loadingScreenLogo;
    private Music loadingScreenMusic;

    private long time_lapsed = TimeUtils.millis();
    float elapsed;

    @Inject
    public LoadingScreen(TrashMelody game, Assets assets, Provider<WarningScreen> warningScreen) {
        this.game = game;
        this.assets = assets;
        this.warningScreen = warningScreen;
        this.loadingScreenLogo = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal(LOADING_LOGO).read());
    }

    @Override
    public void render(float delta) { // Continuously run during active
        clearScreen(253,243,255,1);
        elapsed += delta;

        if(assets.update()){
            game.setScreen(warningScreen.get());
        }

        // Start loading assets
        game.batch.begin();
        game.batch.draw(loadingScreenLogo.getKeyFrame(elapsed), getCenterX()/2, getCenterY()/2);
//        if (assets.getProgress()){
//            textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("barGreen_horizontalMid.png"))));
//            barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
//            barStyle.knobBefore = barStyle.knob;
//            bar = new ProgressBar(0F, 10F, 0.1F, false, barStyle);
//            bar.setPosition(10, 10);
//            bar.setSize(290, bar.getPrefHeight());
//            bar.setAnimateDuration(2);
//        }

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Splash Screen",SplashScreen.splashScreenMusic.getVolume(),TimeUtils.timeSinceMillis(time_lapsed),assets.getProgress());
        // Debug zone

        game.batch.end();
    }

}

