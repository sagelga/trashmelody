package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Provider;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;

import javax.inject.Inject;

import java.awt.*;

import static com.trashmelody.Utils.*;

public class MenuScreen extends ScreenAdapter {
    private TrashMelody game;
    private Provider<StageSelectScreen> stageSelectScreen;
    private Camera camera;
    private Viewport viewport;
    private Stage stage;
    private Container<MenuButtons> container;
    private MenuButtons menuButtons;
    private Texture splashScreenLogo;
    private Texture bg, btnStart, btnCollection, btnSetting, btnExit, borderLeft, borderRight;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

    @Inject
    public MenuScreen(TrashMelody game, Assets assets, Camera camera, Viewport viewport,
                      Provider<StageSelectScreen> stageSelectScreen, MenuButtons menuButtons) {
        this.game = game;
        this.stageSelectScreen = stageSelectScreen;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fill, vw, vh, camera);
        this.stage = new Stage(this.viewport);
        this.menuButtons = menuButtons;
        this.container = new Container<>();
        container.setFillParent(true);
        container.align(Align.top);
        container.pad(50);
        container.setActor(menuButtons);
        stage.addActor(container);
//        stage.addActor(menuButtons);
        Gdx.input.setInputProcessor(stage);

        this.splashScreenLogo   = assets.get(Assets.SPLASH_LOGO, Assets.TEXTURE);
        this.bg                 = assets.get(Assets.MENU_BG, Assets.TEXTURE);
        this.btnStart           = assets.get(Assets.MENU_BTN_START, Assets.TEXTURE);
        this.btnCollection      = assets.get(Assets.MENU_BTN_COLLECTION, Assets.TEXTURE);
        this.btnSetting         = assets.get(Assets.MENU_BTN_SETTING, Assets.TEXTURE);
        this.btnExit            = assets.get(Assets.MENU_BTN_EXIT, Assets.TEXTURE);
        this.borderLeft         = assets.get(Assets.MENU_BORDER_LEFT, Assets.TEXTURE);
        this.borderRight        = assets.get(Assets.MENU_BORDER_RIGHT, Assets.TEXTURE);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        float backgroundWidth = vh / bg.getHeight() * bg.getWidth();
        float backgroundHeight = vh;
        game.batch.draw(bg, (vw - backgroundWidth)/2, 0, backgroundWidth, backgroundHeight);
//        game.batch.draw(borderLeft, 0, 0, , backgroundHeight);
//        game.batch.draw(borderRight, );

        // Click 'ENTER' equivalent to clicking play (for now)
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(stageSelectScreen.get());
        }

        /// Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            Debugger.debug_mode = !Debugger.debug_mode;
        }
        if (Debugger.debug_mode){
            Debugger.runDebugger(game.batch, game.font,"Menu Screen Screen");
        }
        // Debug zone
        game.batch.end();

        stage.act();
        stage.draw();
    }

    private void update(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
