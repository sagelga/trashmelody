package com.trashmelody;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.trashmelody.handlers.DebugInputProcessor;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.GameModule;
import com.trashmelody.managers.MusicManager;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.screens.*;
import com.trashmelody.systems.Systems;
import com.trashmelody.utils.Debugger;
import com.trashmelody.utils.Grapher;
import com.trashmelody.utils.RenderingUtils;

import static com.trashmelody.managers.Assets.PLAYER_MUTE;
import static com.trashmelody.managers.Assets.PLAYER_PLAY;
import static com.trashmelody.managers.Assets.TEXTURE;
import static com.trashmelody.utils.RenderingUtils.getViewportWidth;
import static com.trashmelody.utils.RenderingUtils.getViewportHeight;

public class TrashMelody extends Game {
    public Injector injector;
    public SpriteBatch batch;
    public BitmapFont font;
    public Engine engine = new Engine();
    private Assets assets;
    private ScreenProvider screens;
    public final float WIDTH = 1920;
    public final float HEIGHT = 1080;
    public float SCALE;
    public static boolean enableAnimation = false;

    private Texture playerPlay, playerStop;
    private int timer;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.injector = getInjector();
        this.assets = injector.getInstance(Assets.class);
        this.screens = injector.getInstance(ScreenProvider.class);
        loadBootstrap();
        loadImportantAssets();

        Gdx.input.setInputProcessor(injector.getInstance(DebugInputProcessor.class));
        setLazyScreen(injector.getInstance(ScreenProvider.getScreenFromEnv().getOrElse(SplashScreen.class)));
    }

    @Override
    public void render() {
        MusicManager musicManager = injector.getInstance(MusicManager.class);
        ShapeRenderer shapeRenderer = new ShapeRenderer();

        super.render();

        batch.begin();
        if (Gdx.input.isKeyJustPressed(Input.Keys.EQUALS) || Gdx.input.isKeyJustPressed(Input.Keys.MINUS)) {
            timer = 60;
        }

        if (timer > 0) {
            if (musicManager.getBackgroundMusicVolume() > 0)
                RenderingUtils.drawCenter(batch, playerPlay, 336, 336);
            else
                RenderingUtils.drawCenter(batch, playerStop, 336, 336);

            timer--;
        }
        batch.end();

        if (timer > 0) {
            // Draw the volume bar
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(246 / 255F, 227 / 255F, 231 / 255F, 1);
            shapeRenderer.rect((getViewportWidth() - 252) / 2, getViewportHeight() / 2.45F, 252 * musicManager.getBackgroundMusicVolume(), 20);
            shapeRenderer.end();

            // Draw the hidden volume bar
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(1, 1, 1, 1);
            shapeRenderer.rect((getViewportWidth() - 252) / 2, getViewportHeight() / 2.45F, 252, 20);
            shapeRenderer.end();
        }
    }

    @Override
    public void dispose() {
        super.dispose();

        batch.dispose();
        font.dispose();
        assets.dispose();
    }

    public void setLazyScreen(LazyScreen screen) {
        if (screen.isLoaded()) {
            super.setScreen(screen);
        } else {
            screen.load(assets);
            LoadingScreen loadingScreen = screens.get(LoadingScreen.class);
            loadingScreen.setNextScreen(screen);
            super.setScreen(loadingScreen);
        }
    }

    private Injector getInjector() {
        Injector injector = Guice.createInjector(Stage.PRODUCTION, new GameModule(this));
        (new Grapher()).graph("dependencies-graph.dot", injector);

        return injector;
    }

    private void loadImportantAssets() {
        SplashScreen splashScreen = screens.get(SplashScreen.class);
        LoadingScreen loadingScreen = screens.get(LoadingScreen.class);
        splashScreen.load(assets);
        loadingScreen.load(assets);
        assets.finishLoading();
        splashScreen.setLoaded(true);
        splashScreen.afterLoad(assets);
        splashScreen.setLoaded(true);
        loadingScreen.afterLoad(assets);

        this.playerPlay = assets.get(PLAYER_PLAY, TEXTURE);
        this.playerStop = assets.get(PLAYER_MUTE, TEXTURE);
    }

    private void loadBootstrap() {
        SCALE = Gdx.graphics.getPpiX() / 100;
        ScreenProvider.MAPPER = ScreenProvider.screenClasses.toMap(screen -> screen, screen -> injector.getProvider(screen));
    }
}
