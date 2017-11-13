package com.trashmelody;

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
import com.trashmelody.screens.*;
import com.trashmelody.utils.Debugger;
import com.trashmelody.utils.Grapher;

public class TrashMelody extends Game {
    public Injector injector;
    public SpriteBatch batch;
    public BitmapFont font;
    private Assets assets;
    private ScreenProvider screens;
    public float SCALE;

	@Override
	public void create() {
        this.injector = getInjector();
		this.batch = new SpriteBatch();
		this.font = new BitmapFont();
        this.assets = injector.getInstance(Assets.class);
        this.screens = injector.getInstance(ScreenProvider.class);
        loadBootstrap();
        loadImportantAssets(this.assets, this.screens);

        Gdx.input.setInputProcessor(injector.getInstance(DebugInputProcessor.class));
		setScreen(injector.getInstance(SplashScreen.class));
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
        (new Grapher()).graph("generated/graph", injector);

        return injector;
    }

    private void loadImportantAssets(Assets assets, ScreenProvider screens) {
        screens.get(SplashScreen.class).load(assets);
        screens.get(LoadingScreen.class).load(assets);
        assets.finishLoading();
        screens.get(SplashScreen.class).afterLoad(assets);
        screens.get(LoadingScreen.class).afterLoad(assets);
    }

    private void loadBootstrap() {
        SCALE = Gdx.graphics.getPpiX() / 100;
    }
}
