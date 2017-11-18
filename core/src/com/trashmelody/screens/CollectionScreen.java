package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.utils.Debugger;
import com.trashmelody.TrashMelody;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class CollectionScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screens;
    private OrthographicCamera camera;
    private Viewport viewport;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();
    private int count = 1;

    @Inject
    CollectionScreen(TrashMelody game, OrthographicCamera camera, ScreenProvider screens, Viewport viewport) {
        this.game = game;
        this.screens = screens;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        game.batch.begin();


        if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
            game.setLazyScreen(screens.get(MenuScreen.class));
        }



        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Collection Screen");
        // Debug zone

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
    }

    @Override
    public void loadAssets(Assets assets) {

    }

    @Override
    public void afterLoad(Assets assets) {

    }
}
