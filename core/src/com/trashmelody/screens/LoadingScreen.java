package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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
import static com.trashmelody.utils.RenderingUtils.getViewportHeight;
import static com.trashmelody.utils.RenderingUtils.getViewportWidth;

;

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
    private Viewport viewport;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

    public long time_lapsed;
    float elapsed;

    @Inject
    LoadingScreen(TrashMelody game, Assets assets, ScreenProvider screens, Camera camera,Viewport viewport) {
        this.game = game;
        this.assets = assets;
        this.screens = screens;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);
    }

    @Override
    public void show() {
        time_lapsed = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        clearScreen(253, 243, 255, 1);
        elapsed += delta;

        if (assets.update()) {
            if (nextScreen != null) {
                nextScreen.afterLoad(assets);
                nextScreen.setLoaded(true);
                game.setScreen(nextScreen);
            } else {
                screens.get(WarningScreen.class).afterLoad(assets);
                screens.get(MenuScreen.class).afterLoad(assets);
                game.setLazyScreen(screens.get(WarningScreen.class));
            }
        }

        // Start loading assets

        // Debug zone
        if (Debugger.debug_mode)
            Debugger.runDebugger(game.batch, game.font, "Loading Screen", TimeUtils.timeSinceMillis(time_lapsed), assets.getProgress());
        // Debug zone

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
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
        container.maxSize(259, 221);

        Stage stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);
        stage.addActor(container);

        return stage;
    }
}

