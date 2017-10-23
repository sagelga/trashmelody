package com.trashmelody;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trashmelody.Utils;

import javax.rmi.CORBA.Util;

public class TrashMelody extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private int scrHeight;
	private int scrWidth;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("splash-logo.png");
	}

	@Override
	public void render () {
		scrHeight = Gdx.graphics.getHeight();
		scrWidth = Gdx.graphics.getWidth();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, Utils.alignCenter(scrWidth, 250), Utils.alignCenter(scrHeight, 143), 500, 286);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
