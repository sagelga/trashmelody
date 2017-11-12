package com.trashmelody;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.trashmelody.screens.SplashScreen;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertNotSame;

import static com.trashmelody.Utils.getViewportWidth;

public class TrashMelody extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	private Assets assets;
	Injector injector;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();

		Constant.SCALE = getViewportWidth() / Constant.WIDTH;

		injector = Guice.createInjector(Stage.PRODUCTION, new GameModule(this));
		assets = injector.getInstance(Assets.class);
		Gdx.input.setInputProcessor(injector.getInstance(DebugInputProcessor.class));
		setScreen(injector.getInstance(SplashScreen.class));
	}

	@Override
	public void render () {
		super.render();
		batch.begin();
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
		batch.end();

	}

	@Override
	public void dispose() {
		super.dispose();

		batch.dispose();
		font.dispose();
		assets.dispose();
	}
}
