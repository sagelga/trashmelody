package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
public class PauseScreen extends LazyScreen {
    private TrashMelody game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private GameScreen gameScreen;
    private MenuScreen menuScreen;
    private ResultScreen resultScreen;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();


    // Defining building value
    private Texture continuebtn, retrybtn, homebtn, selectBar, touchContinuebtn, touchRetrybtn, touchHomebtn;
    int count = 1;

    @Inject
    PauseScreen(TrashMelody game, OrthographicCamera camera, Viewport viewport, ScreenProvider screens) {
        this.game = game;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);
        this.menuScreen = screens.get(MenuScreen.class);
        this.gameScreen = screens.get(GameScreen.class);
        this.resultScreen = screens.get(ResultScreen.class);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        //Components Draw
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) count++;
        else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) count--;

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            switch (count) {
                case 1:
                    game.setLazyScreen(gameScreen);
                    break;
                case 2:
                    gameScreen.setCommand(GameScreen.Command.Restart);
                    game.setLazyScreen(gameScreen);
                    break;
                case 3:
                    gameScreen.setCommand(GameScreen.Command.Restart);
                    game.setLazyScreen(menuScreen);
                    break;
            }
        }

        if (count != 1) game.batch.draw(continuebtn, vw / 3, vh / 1.5F, vw / 3, vh / 10);
        else {
            game.batch.draw(touchContinuebtn, vw / 3, vh / 1.5F, vw / 3, vh / 10);
            game.batch.draw(selectBar, vw / 3.7F, vh / 1.48F, vw / 2.2F, vh / 10);
        }

        if (count != 2) game.batch.draw(retrybtn, vw / 3, vh / 2, vw / 3, vh / 10);
        else {
            game.batch.draw(touchRetrybtn, vw / 3, vh / 2, vw / 3, vh / 10);
            game.batch.draw(selectBar,vw/3.7F,vh/1.98F,vw/2.2F,vh/10);
        }

        if (count != 3) game.batch.draw(homebtn, vw / 3, vh / 3, vw / 3, vh / 10);
        else {
            game.batch.draw(touchHomebtn, vw / 3, vh / 3, vw / 3, vh / 10);
            game.batch.draw(selectBar,vw/3.7F,vh/2.98F,vw/2.2F,vh/10);
        }

        if (count > 3) count = 1; else if (count<1) count = 3;

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font, "Pause Screen");
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
        assets.load(PAUSE_RETRY_BTN1, TEXTURE);
        assets.load(PAUSE_HOME_BTN1, TEXTURE);
        assets.load(PAUSE_SELECTOR_ARROW, TEXTURE);
        assets.load(PAUSE_CONTINUE_BTN2, TEXTURE);
        assets.load(PAUSE_RETRY_BTN2, TEXTURE);
        assets.load(PAUSE_HOME_BTN2, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.continuebtn = assets.get(PAUSE_CONTINUE_BTN1, TEXTURE);
        this.retrybtn = assets.get(PAUSE_RETRY_BTN1, TEXTURE);
        this.homebtn = assets.get(PAUSE_HOME_BTN1, TEXTURE);
        this.selectBar = assets.get(PAUSE_SELECTOR_ARROW, TEXTURE);
        this.touchContinuebtn = assets.get(PAUSE_CONTINUE_BTN2, TEXTURE);
        this.touchRetrybtn = assets.get(PAUSE_RETRY_BTN2, TEXTURE);
        this.touchHomebtn = assets.get(PAUSE_HOME_BTN2, TEXTURE);
    }
}
