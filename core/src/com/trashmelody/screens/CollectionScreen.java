package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;
import com.trashmelody.Utils;

import static com.trashmelody.Utils.*;

import javax.inject.Inject;
import javax.rmi.CORBA.Util;

import static com.trashmelody.Utils.*;

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

        Debugger.runDebugger(game.batch, game.font,"Collection Screen");
        Debugger.runAdvancedDebugger(game.batch,game.font,0,0);

        game.batch.end();
    }
}
