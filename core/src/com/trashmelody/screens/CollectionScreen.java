package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.trashmelody.Utils.*;

@Singleton
public class CollectionScreen extends ScreenAdapter {
    private TrashMelody game;
    private Camera camera;
    private Viewport viewport;
    private Texture bg, screenTitle;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

    @Inject
    public CollectionScreen(TrashMelody game, Assets assets, Camera camera, Viewport viewport) {
        this.game = game;
        this.camera = camera;
        this.viewport = viewport;

        this.bg             = assets.get(Assets.COLLECTION_BG, Assets.TEXTURE);
        this.screenTitle    = assets.get(Assets.COLLECTION_SCREEN_TITLE, Assets.TEXTURE);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();

        game.batch.begin();
        drawCenter(game.batch, bg, vw, vh);
        // drawCenterX(game.batch, screenTitle, vw, hey, vh-400);

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Collection Screen");
        // Debug zone

        game.batch.end();
    }
}
