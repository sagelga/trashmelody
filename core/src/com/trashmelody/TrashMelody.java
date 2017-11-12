package com.trashmelody;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.trashmelody.screens.SplashScreen;

import static com.trashmelody.Utils.getViewportWidth;

public class TrashMelody extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	private Assets assets;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();

		Constant.SCALE = getViewportWidth() / Constant.WIDTH;

		Injector injector = Guice.createInjector(new GameModule(this));
		assets = injector.getInstance(Assets.class);
		Gdx.input.setInputProcessor(new DebugInputProcessor());

		setScreen(injector.getInstance(SplashScreen.class));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();

		batch.dispose();
		font.dispose();
		assets.dispose();
	}
}
