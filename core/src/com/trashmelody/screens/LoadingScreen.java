package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.TrashMelody;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.utils.AnimatedImage;
import com.trashmelody.utils.Debugger;
import com.trashmelody.utils.GifDecoder;

import static com.trashmelody.managers.Assets.LOADING_LOGO;
import static com.trashmelody.utils.RenderingUtils.clearScreen;
import static com.trashmelody.utils.RenderingUtils.drawCenter;

@Singleton
public class LoadingScreen extends LazyScreen {
    private TrashMelody game;
    private Assets assets;
    private ScreenProvider screens;
    private Camera camera;
    private LazyScreen nextScreen;
    private Stage stage;
    private Animation<TextureRegion> loadingScreenLogo;
    private Music loadingScreenMusic;

    public long time_lapsed;
    float elapsed;

    @Inject
    LoadingScreen(TrashMelody game, Assets assets, ScreenProvider screens, Camera camera) {
        this.game = game;
        this.assets = assets;
        this.screens = screens;
        this.camera = camera;
    }

    @Override
    public void show() {
        time_lapsed = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        clearScreen(253,243,255,1);
        elapsed += delta;

        if (assets.update() && false) {
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
//        game.batch.begin();
//        drawCenter(game.batch, loadingScreenLogo.getKeyFrame(elapsed), 150, 128);
//
//         Debug zone
//        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Loading Screen",TimeUtils.timeSinceMillis(time_lapsed),assets.getProgress());
//         Debug zone
//
//        game.batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void setNextScreen(LazyScreen nextScreen) {
        this.nextScreen = nextScreen;
    }

    @Override
    protected void loadAssets(Assets assets) {
        this.loadingScreenLogo = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal(LOADING_LOGO).read());
    }

    @Override
    public void afterLoad(Assets assets) {
        this.stage = getStage();
    }

    private Stage getStage() {
        AnimatedImage loadingAnimation = new AnimatedImage(loadingScreenLogo);

        Container container = new Container<>(loadingAnimation);
        container.setFillParent(true);
        container.center();

        Stage stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);
        stage.addActor(container);

        return stage;
    }
}

