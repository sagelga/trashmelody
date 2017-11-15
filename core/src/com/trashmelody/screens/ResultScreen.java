package com.trashmelody.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
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
public class ResultScreen extends LazyScreen {
    private TrashMelody game;
    private Camera camera;
    private Viewport viewport;
    private Texture bg, header, footer, gradeA, stats;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

    @Inject
    ResultScreen(TrashMelody game, Camera camera, Viewport viewport) {
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

        game.batch.draw(bg, 0, 0, vw, vh);
        game.batch.draw(header, 0, vh/1.1F,vw/2,vh/11);
        game.batch.draw(footer, 0, 0, vw, vh/12);
        game.batch.draw(gradeA, vw/12, vh/3.5F, vw/4, vh/2);
        game.batch.draw(stats, vw/2, vh/5, vw/4, vh/1.5F);
        

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Result Screen");

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
        assets.load(RESULT_RESULT_BACKGROUND, TEXTURE);
        assets.load(RESULT_RESULT_HEADER, TEXTURE);
        assets.load(RESULT_RESULT_FOOTER, TEXTURE);
        assets.load(RESULT_RESULT_GRADE_A, TEXTURE);
        assets.load(RESULT_RESULT_GRADE_B, TEXTURE);
        assets.load(RESULT_RESULT_GRADE_C, TEXTURE);
        assets.load(RESULT_RESULT_GRADE_D, TEXTURE);
        assets.load(RESULT_RESULT_GRADE_F, TEXTURE);
        assets.load(RESULT_RESULT_PERFECT, TEXTURE);
        assets.load(RESULT_RESULT_GOOD, TEXTURE);
        assets.load(RESULT_RESULT_NICE, TEXTURE);
        assets.load(RESULT_RESULT_MISS, TEXTURE);
        assets.load(RESULT_RESULT_COMBO, TEXTURE);
        assets.load(RESULT_RESULT_TEXT_ALL, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.bg = assets.get(RESULT_RESULT_BACKGROUND, TEXTURE);
        this.header = assets.get(RESULT_RESULT_HEADER, TEXTURE);
        this.footer = assets.get(RESULT_RESULT_FOOTER, TEXTURE);
        this.gradeA = assets.get(RESULT_RESULT_GRADE_A, TEXTURE);
        this.stats = assets.get(RESULT_RESULT_TEXT_ALL, TEXTURE);
    }
}
