package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.LazyScreen;
import com.trashmelody.TrashMelody;

import static com.trashmelody.Assets.*;
import static com.trashmelody.Utils.*;

@Singleton
public class CollectionScreen extends LazyScreen {
    private TrashMelody game;
    private Camera camera;
    private Viewport viewport;
    private Texture bg, screenTitle;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

    @Inject
    public CollectionScreen(TrashMelody game, Camera camera, Viewport viewport) {
        this.game = game;
        this.camera = camera;
        this.viewport = viewport;
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();

        game.batch.begin();
        drawCenter(game.batch, bg, vw, vh);
        // drawCenterX(game.batch, screenTitle, vw, hey, vh-400);

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Collection Screen");
        // Debug zone

        game.batch.end();
    }

    @Override
    public void loadAssets(Assets assets) {
        assets.load(COLLECTION_BG, TEXTURE);
        assets.load(COLLECTION_SCREEN_TITLE, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.bg = assets.get(COLLECTION_BG, TEXTURE);
        this.screenTitle = assets.get(COLLECTION_SCREEN_TITLE, TEXTURE);
    }
}
