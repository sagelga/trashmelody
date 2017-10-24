package com.trashmelody;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.trashmelody.screens.SplashScreen;

public class TrashMelody extends Game {
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();

		Injector injector = Guice.createInjector(new GameModule(this));
		setScreen(injector.getInstance(SplashScreen.class));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();
	}
}
