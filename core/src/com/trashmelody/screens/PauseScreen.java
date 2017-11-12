package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.trashmelody.Utils.*;

@Singleton
public class PauseScreen extends ScreenAdapter {
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
    public PauseScreen(TrashMelody game, Assets assets, Camera camera, Viewport viewport) {
        this.game = game;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);


        //Variable handlers     |Assets retrival path
        this.continuebtn       = assets.get(Assets.PAUSE_CONTINUE_BTN1, Assets.TEXTURE);
        this.settingbtn        = assets.get(Assets.PAUSE_SETTING_BTN1, Assets.TEXTURE);
        this.retrybtn          = assets.get(Assets.PAUSE_RETRY_BTN1, Assets.TEXTURE);
        this.homebtn           = assets.get(Assets.PAUSE_HOME_BTN1, Assets.TEXTURE);
        this.selectBar         = assets.get(Assets.PAUSE_SELECTOR_ARROW, Assets.TEXTURE);
        this.touchContinuebtn  = assets.get(Assets.PAUSE_CONTINUE_BTN1, Assets.TEXTURE);
        this.touchSettingbtn   = assets.get(Assets.PAUSE_SETTING_BTN1, Assets.TEXTURE);
        this.touchRetrybtn     = assets.get(Assets.PAUSE_RETRY_BTN1, Assets.TEXTURE);
        this.touchHomebtn      = assets.get(Assets.PAUSE_HOME_BTN1, Assets.TEXTURE);

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

}
