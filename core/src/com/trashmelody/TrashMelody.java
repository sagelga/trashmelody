package com.trashmelody;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.trashmelody.screens.*;

import static com.trashmelody.Utils.getViewportWidth;

public class TrashMelody extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	private Assets assets;
	private ScreenProvider screenProvider;
	Injector injector;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();

		Constant.SCALE = getViewportWidth() / Constant.WIDTH;

		injector = Guice.createInjector(Stage.DEVELOPMENT, new GameModule(this));
		this.assets = injector.getInstance(Assets.class);
		this.screenProvider = injector.getInstance(ScreenProvider.class);

		screenProvider.get(SplashScreen.class).load(assets);
		screenProvider.get(LoadingScreen.class).load(assets);
		assets.finishLoading();
		screenProvider.get(SplashScreen.class).afterLoad(assets);
		screenProvider.get(LoadingScreen.class).afterLoad(assets);

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
			LoadingScreen loadingScreen = screenProvider.get(LoadingScreen.class);
			loadingScreen.setNextScreen(screen);
			super.setScreen(loadingScreen);
		}
	}
}
