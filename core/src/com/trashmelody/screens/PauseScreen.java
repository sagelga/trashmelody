package com.trashmelody.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.managers.Assets;
import com.trashmelody.utils.Debugger;
import com.trashmelody.TrashMelody;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class PauseScreen extends LazyScreen {
    private TrashMelody game;
    private Camera camera;
    private Viewport viewport;
    private Stage stage;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

    // Defining building value
    private Texture continuebtn;
    private Texture settingbtn;
    private Texture retrybtn;
    private Texture homebtn;
    private Texture selectBar;
    private Texture touchContinuebtn;
    private Texture touchSettingbtn;
    private Texture touchRetrybtn;
    private Texture touchHomebtn;

    @Inject
    PauseScreen(TrashMelody game, Camera camera, Viewport viewport) {
        this.game = game;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        //Components Draw
        game.batch.draw(continuebtn,0,vh/10,vw,vh/1.15F);


        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font, "Game Screen");
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
        assets.load(PAUSE_CONTINUE_BTN1, TEXTURE);
        assets.load(PAUSE_SETTING_BTN1, TEXTURE);
        assets.load(PAUSE_RETRY_BTN1, TEXTURE);
        assets.load(PAUSE_HOME_BTN1, TEXTURE);
        assets.load(PAUSE_SELECTOR_ARROW, TEXTURE);
        assets.load(PAUSE_CONTINUE_BTN1, TEXTURE);
        assets.load(PAUSE_SETTING_BTN1, TEXTURE);
        assets.load(PAUSE_RETRY_BTN1, TEXTURE);
        assets.load(PAUSE_HOME_BTN1, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.continuebtn = assets.get(PAUSE_CONTINUE_BTN1, TEXTURE);
        this.settingbtn = assets.get(PAUSE_SETTING_BTN1, TEXTURE);
        this.retrybtn = assets.get(PAUSE_RETRY_BTN1, TEXTURE);
        this.homebtn = assets.get(PAUSE_HOME_BTN1, TEXTURE);
        this.selectBar = assets.get(PAUSE_SELECTOR_ARROW, TEXTURE);
        this.touchContinuebtn = assets.get(PAUSE_CONTINUE_BTN1, TEXTURE);
        this.touchSettingbtn = assets.get(PAUSE_SETTING_BTN1, TEXTURE);
        this.touchRetrybtn = assets.get(PAUSE_RETRY_BTN1, TEXTURE);
        this.touchHomebtn = assets.get(PAUSE_HOME_BTN1, TEXTURE);
    }
}
