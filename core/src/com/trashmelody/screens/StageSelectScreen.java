package com.trashmelody.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import com.google.inject.Provider;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;
import static com.trashmelody.Utils.*;

import javax.inject.Inject;

public class StageSelectScreen extends ScreenAdapter {
    private TrashMelody game;
    private Provider<MenuScreen> menuScreen;
    private OrthographicCamera camera;

    // Defining building value
    private Texture stageCafe;          private Texture stageCafeText;
    private Texture stageCinema;        private Texture stageCinemaText;
    private Texture stageHome;          private Texture stageHomeText;
    private Texture stageHospital;      private Texture stageHospitalText;
    private Texture stageSchool;        private Texture stageSchoolText;
    private Texture stageOffice;        private Texture stageOfficeText;
    private Texture buttonBack;         private Texture buttonPlay;
    private Texture header;             private Texture footer;
    private Texture cloud;              private Texture trashworld_logo;

    @Inject
    public StageSelectScreen(TrashMelody game, Assets assets, Provider<MenuScreen> menuScreen, OrthographicCamera camera) {
        this.game = game;
        this.menuScreen = menuScreen;
        this.camera = camera;

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
        this.buttonBack = assets.getStageSelectAssets("back-button");
        this.buttonPlay = assets.getStageSelectAssets("play-button");
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
        drawCenter(game.batch,trashworld_logo,2265/5,1370/5);// 2265 × 1370
        drawCenter(game.batch,cloud,7507/6,2644/6); // 7507x2644

        game.batch.draw(header,0,getViewportHeight()-100,4485/5,608/5); // 4485 × 608
        drawCenterX(game.batch,footer,8002/5,296/5,0); // 8002 × 296

//        switch (selected){
//            case ("Cafe"):
                game.batch.draw(stageCafeText, 20,getViewportHeight()-608/6,2057/5,487/6); // 2057 × 487
//        }

        game.batch.draw(buttonPlay,20,10,670/5,239/5); // 670 × 239
        game.batch.draw(buttonBack,getViewportWidth()-20-(687/5),10,687/5,236/5); // 687 × 236

        game.batch.draw(stageCafe, 100F,100F,200,200);
        game.batch.draw(stageCinema, 400F,300F,200,200);
        game.batch.draw(stageHome, 600F,500F,200,200);
        game.batch.draw(stageSchool, getViewportWidth()/3,getViewportHeight()/4,2489/8,1372/8); // 2489 × 1372
        game.batch.draw(stageHospital, 300F,100F,200,200);
        game.batch.draw(stageOffice, 350F,100F,200,200);


        if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
            game.setScreen(menuScreen.get());
        }

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            Debugger.debug_mode = !Debugger.debug_mode;
        }
        if (Debugger.debug_mode){
            Debugger.runDebugger(game.batch, game.font,"Stage Selection Screen");
            Debugger.runAdvancedDebugger(game.batch,game.font,0,0);
        }
        // Debug zone

        game.batch.end();
    }
}
