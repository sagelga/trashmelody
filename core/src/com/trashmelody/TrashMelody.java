package com.trashmelody;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.trashmelody.screens.SplashScreen;

import static com.trashmelody.Utils.clearScreen;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertNotSame;

public class TrashMelody extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();

		Injector injector = Guice.createInjector(new GameModule(this));
		assertSame(
		        injector.getInstance(SplashScreen.class),
                injector.getInstance(SplashScreen.class)
        );

		setScreen(injector.getInstance(SplashScreen.class));
	}

	@Override
	public void render () {
	    clearScreen();

		super.render();
	}
}
