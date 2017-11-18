package com.trashmelody.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    private OrthographicCamera camera;
    private Viewport viewport;
    private Texture gradeA, gradeB, gradeC, gradeD, gradeF, stats, gradeToShow;
    private Texture bg, header, footer, btnBack;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();
    private enum Grade { A, B, C, D, F }
    private Grade grade;

    @Inject
    ResultScreen(TrashMelody game, OrthographicCamera camera, Viewport viewport) {
        this.game = game;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);
    }

    @Override
    public void render(float delta) {
        // Assume that the grade is B
        grade = Grade.B;

        clearScreen();

        game.batch.begin();

        game.batch.draw(bg, 0, 0, vw, vh);
        game.batch.draw(header, 0, vh/1.1F, (float) vw/1.7F, findRatio(1200, 105, (float) vw/1.7F, 'h'));
        game.batch.draw(footer, 0, 0, vw, findRatio(1920, 72, vw, 'h'));
        game.batch.draw(btnBack, vw / 1.15F, 0, findRatio(180, 54, vh/16F, 'w'), vh / 16);

        switch (grade) {
            case A: gradeToShow = gradeA; break;
            case B: gradeToShow = gradeB; break;
            case C: gradeToShow = gradeC; break;
            case D: gradeToShow = gradeD; break;
            case F: gradeToShow = gradeF; break;
            default: gradeToShow = gradeA;
        }

        game.batch.draw(gradeToShow, vw/12, vh/5.6F, vw/3.2F, vh/1.6F);
        game.batch.draw(stats, vw/2.1F, vh/5.6F, vw/4.3F, vh/1.6F);

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
        assets.load(RESULT_RESULT_GRADE_A, TEXTURE);
        assets.load(RESULT_RESULT_GRADE_B, TEXTURE);
        assets.load(RESULT_RESULT_GRADE_C, TEXTURE);
        assets.load(RESULT_RESULT_GRADE_D, TEXTURE);
        assets.load(RESULT_RESULT_GRADE_F, TEXTURE);
        assets.load(RESULT_RESULT_TEXT_ALL, TEXTURE);
        assets.load(GLOBAL_FOOTER_BAR, TEXTURE);
        assets.load(GLOBAL_ICON_BACK, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.bg = assets.get(RESULT_RESULT_BACKGROUND, TEXTURE);
        this.header = assets.get(RESULT_RESULT_HEADER, TEXTURE);
        this.gradeA = assets.get(RESULT_RESULT_GRADE_A, TEXTURE);
        this.gradeB = assets.get(RESULT_RESULT_GRADE_B, TEXTURE);
        this.gradeC = assets.get(RESULT_RESULT_GRADE_C, TEXTURE);
        this.gradeD = assets.get(RESULT_RESULT_GRADE_D, TEXTURE);
        this.gradeF = assets.get(RESULT_RESULT_GRADE_F, TEXTURE);
        this.stats = assets.get(RESULT_RESULT_TEXT_ALL, TEXTURE);
        this.footer = assets.get(GLOBAL_FOOTER_BAR, TEXTURE);
        this.btnBack = assets.get(GLOBAL_ICON_BACK, TEXTURE);
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
