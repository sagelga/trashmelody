package com.trashmelody.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.Assets;
import com.trashmelody.LazyScreen;
import com.trashmelody.TrashMelody;

import static com.trashmelody.Constant.SCALE;
import static com.trashmelody.Utils.*;

@Singleton
public class SettingsScreen extends LazyScreen {
    private TrashMelody game;
    private Camera camera;
    private Viewport viewport;
    private BitmapFont largeFont;
    private BitmapFont mediumFont;

    @Inject
    SettingsScreen(TrashMelody game, Camera camera, Viewport viewport) {
        this.game = game;
        this.camera = camera;
        this.viewport = viewport;
    }

    @Override
    public void render(float delta) {
        clearScreen();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
    }

    @Override
    protected void loadAssets(Assets assets) {
        largeFont = assets.getSuperSpaceFont((int)(40 * SCALE), Color.BLACK);
        mediumFont = assets.getSuperSpaceFont((int)(25 * SCALE), Color.BLACK);
    }
}
