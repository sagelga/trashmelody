package com.trashmelody.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.*;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.utils.Debugger;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class WarningScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screens;
    private Texture warningScreenText;
    private long timeLapsed;
    private Camera camera;

    private Viewport viewport;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

    @Inject
    WarningScreen(TrashMelody game, ScreenProvider screens, Camera camera, Viewport viewport) {
        this.game = game;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);
        this.screens = screens;
    }

    @Override
    public void show() {
        timeLapsed = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        clearScreen(253, 243, 255, 1);
        if (TimeUtils.timeSinceMillis(timeLapsed) > 5000) {
            game.setLazyScreen(screens.get(MenuScreen.class));
        }

        // Start loading assets
        game.batch.begin();
        drawCenter(game.batch, warningScreenText, 1000, findRatio(1097, 504, 1000, 'h'));

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Warning Screen",TimeUtils.timeSinceMillis(timeLapsed));
        // Debug zone

        game.batch.end();
    }

    @Override
    protected void loadAssets(Assets assets) {
        assets.load(WARNING_TEXT, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.warningScreenText = assets.get(WARNING_TEXT, TEXTURE);
    }
}
