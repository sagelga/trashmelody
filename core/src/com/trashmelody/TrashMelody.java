package com.trashmelody;

import com.badlogic.gdx.Game;
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
		Injector injector = Guice.createInjector(new GameModule(this));
		batch = new SpriteBatch();
		font = new BitmapFont();
		assets = injector.getInstance(Assets.class);

		Constant.SCALE = getViewportWidth() / Constant.WIDTH;

		setScreen(injector.getInstance(SplashScreen.class));
	}

	@Override
	public void render () {
		super.render();
	}

	@Inject
	@Override
	public void dispose() {
		super.dispose();

		batch.dispose();
		font.dispose();
		assets.dispose();
	}
}
