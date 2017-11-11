package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Inject;
import com.trashmelody.TrashMelody;
import com.trashmelody.Utils;

import static io.vavr.API.println;

public class SandboxScreen extends ScreenAdapter {
    private TrashMelody game;
    private Camera camera;
    private Sprite badLogicLogo;
    private Texture badLogicTexture;

    @Inject
    SandboxScreen(TrashMelody game, Camera camera) {
        this.camera = camera;
        this.game = game;
        badLogicTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        badLogicLogo = new Sprite(badLogicTexture, 0, 0, Utils.getViewportWidth(), Utils.getViewportHeight());
    }

    @Override
    public void render(float delta) {
        Utils.clearScreen();

        camera.update();

        game.batch.begin();
        game.batch.draw(badLogicTexture, 0, 0, Utils.getViewportWidth(), Utils.getViewportWidth());
        badLogicLogo.draw(game.batch);
        if (Gdx.input.justTouched()) {
            println(Gdx.input.getX());
            println(Gdx.input.getY());
        }
        game.batch.end();
    }
}
