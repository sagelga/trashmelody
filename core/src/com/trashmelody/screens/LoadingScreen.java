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

public class LoadingScreen extends ScreenAdapter {
    private TrashMelody game;
    private Assets assets;
    private Provider<WarningScreen> warningScreen;
    private Texture loadingScreenLogo;
    private Music loadingScreenMusic;

    private ProgressBar barStyle;
    private ProgressBar bar;
    private TextureRegionDrawable textureBar;

    private long time_lapsed = TimeUtils.millis() + 5000;

    @Inject
    public LoadingScreen(TrashMelody game, Assets assets, Provider<WarningScreen> warningScreen) {
        this.game = game;
        this.assets = assets;
        this.warningScreen = warningScreen;

        this.loadingScreenLogo  = assets.get(Assets.LOADING_LOGO,   Assets.TEXTURE);
    }

//    @Override
////    public void show(){ // Run while screen is active
////        splashScreenMusic.setVolume(0.5F); // for development phase only
////        splashScreenMusic.play();
////        splashScreenMusic.setLooping(true);
////    }

    @Override
    public void render(float delta) { // Continuously run during active
        clearScreen(253,243,255,1);

        if(assets.assetManager.update()){
            game.setScreen(warningScreen.get());
        }

        // Start loading assets
        game.batch.begin();
        drawCenter(game.batch, loadingScreenLogo, 960/2, 540/2); // 960 × 540

//        if (AssetManager.getProgress()){
//            textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("barGreen_horizontalMid.png"))));
//            barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
//            barStyle.knobBefore = barStyle.knob;
//            bar = new ProgressBar(0F, 10F, 0.1F, false, barStyle);
//            bar.setPosition(10, 10);
//            bar.setSize(290, bar.getPrefHeight());
//            bar.setAnimateDuration(2);
//        }

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Splash Screen",SplashScreen.splashScreenMusic.getVolume(),TimeUtils.timeSinceMillis(time_lapsed));
        // Debug zone

        game.batch.end();
    }

}
