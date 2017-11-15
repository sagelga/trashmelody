package com.trashmelody;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.trashmelody.handlers.DebugInputProcessor;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.GameModule;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.screens.GameScreen;
import com.trashmelody.screens.LazyScreen;
import com.trashmelody.screens.LoadingScreen;
import com.trashmelody.screens.SplashScreen;
import com.trashmelody.systems.Systems;
import com.trashmelody.utils.Debugger;
import com.trashmelody.utils.Grapher;

public class TrashMelody extends Game {
    public Injector injector;
    public SpriteBatch batch;
    public BitmapFont font;
    public Engine engine = new Engine();
    private Assets assets;
    private ScreenProvider screens;
    public float SCALE;

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
        setLazyScreen(injector.getInstance(GameScreen.class));
	}

	@Override
	public void render () {
		super.render();
		batch.begin();
		// Toggling the Debugger for all screen
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
		// Flashing the background music progress bar
		batch.end();
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
    }

    private void loadBootstrap() {
        SCALE = Gdx.graphics.getPpiX() / 100;
        injector.getInstance(Systems.class).list.stream()
                .map(systemClass -> injector.getInstance(systemClass))
                .forEach(entitySystem -> engine.addSystem(entitySystem));
    }
}
