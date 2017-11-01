package com.trashmelody.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import com.trashmelody.Assets;
import com.trashmelody.TrashMelody;
import static com.trashmelody.Utils.*;

import javax.inject.Inject;

public class StageSelectScreen extends ScreenAdapter {
    private TrashMelody game;
    private MenuScreen menuScreen;
    private OrthographicCamera camera;

    // Defining building value
    private Texture stageCafe;          private Texture stageCafeText;
    private Texture stageCinema;        private Texture stageCinemaText;
    private Texture stageHome;          private Texture stageHomeText;
    private Texture stageHospital;      private Texture stageHospitalText;
    private Texture stageSchool;        private Texture stageSchoolText;
    private Texture stageOffice;        private Texture stageOfficeText;
    private Texture stageBack;          private Texture stagePlay;
    private Texture header;             private Texture footer;
    private Texture cloud;              private Texture trashworld_logo;

    @Inject
    public StageSelectScreen(TrashMelody game, Assets assets, MenuScreen menuScreen, OrthographicCamera camera) {
        this.game = game;
        this.menuScreen = menuScreen;

        this.stageCafe = assets.getStageSelectAssets("cafe");
        this.stageCafeText = assets.getStageSelectAssets("cafe-text");
        this.stageCinema = assets.getStageSelectAssets("cinema");
        this.stageCinemaText = assets.getStageSelectAssets("cinema-text");
        this.stageHome = assets.getStageSelectAssets("home");
        this.stageHomeText = assets.getStageSelectAssets("home-text");
        this.stageSchool = assets.getStageSelectAssets("school");
        this.stageSchoolText = assets.getStageSelectAssets("school-text");
        this.stageHospital = assets.getStageSelectAssets("hospital");
        this.stageHospitalText = assets.getStageSelectAssets("hospital-text");
        this.stageOffice = assets.getStageSelectAssets("office");
        this.stageOfficeText = assets.getStageSelectAssets("office-text");
        this.stageBack = assets.getStageSelectAssets("stage-backbutton");
        this.stagePlay = assets.getStageSelectAssets("stage-playbutton");
        this.header = assets.getStageSelectAssets("header");
        this.footer = assets.getStageSelectAssets("footer");
        this.cloud = assets.getStageSelectAssets("cloud");
        this.trashworld_logo = assets.getStageSelectAssets("trash-world");

    }

    @Override
    public void render(float delta) {
        clearScreen(253, 243, 255, 1);
//        camera.update();
//        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(stageCafe, 100F,100F,200*SCREEN_SIZE,200*SCREEN_SIZE);
        game.batch.draw(stageCinema, 400F,300F,200*SCREEN_SIZE,200*SCREEN_SIZE);
        game.batch.draw(stageHome, 600F,500F,200*SCREEN_SIZE,200*SCREEN_SIZE);
        game.batch.draw(stageSchool, 250F,100F,200*SCREEN_SIZE,200*SCREEN_SIZE);
        game.batch.draw(stageHospital, 300F,100F,200*SCREEN_SIZE,200*SCREEN_SIZE);
        game.batch.draw(stageOffice, 350F,100F,200*SCREEN_SIZE,200*SCREEN_SIZE);

        // Debug zone
        game.font.draw(game.batch, "Stage Selection Screen",30, 40);
        game.font.draw(game.batch, getViewportHeight() + " x " + getViewportWidth(),30, 60);
        // Debug zone
        game.batch.end();
    }
}
