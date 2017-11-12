package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
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
public class ResultScreen extends ScreenAdapter {
    private TrashMelody game;
    private Camera camera;
    private Viewport viewport;
    private Texture bg, header, footer, gradeA, stats;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

    @Inject
    public ResultScreen(TrashMelody game, Assets assets, Camera camera, Viewport viewport) {
        this.game = game;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);

        this.bg = assets.get(Assets.RESULT_RESULT_BACKGROUND, Assets.TEXTURE);
        this.header = assets.get(Assets.RESULT_RESULT_HEADER, Assets.TEXTURE);
        this.footer = assets.get(Assets.RESULT_RESULT_FOOTER, Assets.TEXTURE);
        this.gradeA = assets.get(Assets.RESULT_RESULT_GRADE_A, Assets.TEXTURE);
        this.stats = assets.get(Assets.RESULT_RESULT_TEXT_ALL, Assets.TEXTURE);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(bg, 0, 0, vw, vh);
        game.batch.draw(header, 0, vh-105, 1200, 105);
        game.batch.draw(footer, 0, 0, vw, ((float)79/1920)*vw);
        drawCenterY(game.batch, gradeA, 589, 700, 150);
        drawCenterY(game.batch, stats, 409, 700, getCenterX());

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Result Screen");

        // Debug zone

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
    }
}
