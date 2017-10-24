package com.trashmelody;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.trashmelody.screens.SplashScreen;

import static com.trashmelody.Utils.clearScreen;

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
	    clearScreen();

		super.render();
	}
}
