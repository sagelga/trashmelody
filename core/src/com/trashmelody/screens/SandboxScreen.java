package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Inject;
import com.trashmelody.Utils;

import static io.vavr.API.println;

public class SandboxScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private Camera camera;
    private Sprite badLogicLogo;
    private Texture badLogicTexture;

    @Inject
    SandboxScreen(SpriteBatch batch, Camera camera) {
        this.batch = batch;
        this.camera = camera;
        badLogicTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        badLogicLogo = new Sprite(badLogicTexture, 0, 0, Utils.getViewportWidth(), Utils.getViewportHeight());
    }

    @Override
    public void render(float delta) {
        Utils.clearScreen();

        camera.update();

        batch.begin();
        batch.draw(badLogicTexture, 0, 0, Utils.getViewportWidth(), Utils.getViewportWidth());
        badLogicLogo.draw(batch);
        if (Gdx.input.justTouched()) {
            println(Gdx.input.getX());
            println(Gdx.input.getY());
        }
        batch.end();
    }
}
