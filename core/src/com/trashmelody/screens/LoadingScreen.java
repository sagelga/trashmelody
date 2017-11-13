package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.*;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.utils.Debugger;
import com.trashmelody.utils.GifDecoder;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class LoadingScreen extends LazyScreen {
    private TrashMelody game;
    private Assets assets;
    private ScreenProvider screens;
    private LazyScreen nextScreen;
    private Animation<TextureRegion> loadingScreenLogo;
    private Music loadingScreenMusic;

    public  long time_lapsed;
    float elapsed;

    @Inject
    LoadingScreen(TrashMelody game, Assets assets, ScreenProvider screens) {
        this.game = game;
        this.assets = assets;
        this.screens = screens;
    }

    @Override
    public void show(){
        time_lapsed = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        clearScreen(253,243,255,1);
        elapsed += delta;

        if(assets.update()){
            if (nextScreen != null) {
                nextScreen.afterLoad(assets);
                game.setScreen(nextScreen);
            } else {
                screens.get(WarningScreen.class).afterLoad(assets);
                screens.get(MenuScreen.class).afterLoad(assets);
                game.setLazyScreen(screens.get(WarningScreen.class));
            }
        }

        // Start loading assets
        game.batch.begin();
        drawCenter(game.batch, loadingScreenLogo.getKeyFrame(elapsed), 150, 128);

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Loading Screen",TimeUtils.timeSinceMillis(time_lapsed),assets.getProgress());
        // Debug zone

        game.batch.end();
    }

    public void setNextScreen(LazyScreen nextScreen) {
        this.nextScreen = nextScreen;
    }

    @Override
    protected void loadAssets(Assets assets) {
        this.loadingScreenLogo = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal(LOADING_LOGO).read());
    }
}

