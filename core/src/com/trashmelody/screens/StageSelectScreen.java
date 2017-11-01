package com.trashmelody.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;

import com.trashmelody.Assets;
import com.trashmelody.TrashMelody;
import static com.trashmelody.Utils.*;

import javax.inject.Inject;
import javax.rmi.CORBA.Util;
import javax.xml.soap.Text;

public class StageSelectScreen extends ScreenAdapter {
    private TrashMelody game;
    private MenuScreen menuScreen;

    // Defining building value
    private Texture stageCafe;          //private Texture stageCafeText;
    private Texture stageCinema;        //private Texture stageCinemaText;
    private Texture stageHome;          //private Texture stageHomeText;
    private Texture stageHospital;      //private Texture stageHospitalText;
    private Texture stageSchool;        //private Texture stageSchoolText;
    private Texture stageOffice;        //private Texture stageOfficeText;

    @Inject
    public StageSelectScreen(TrashMelody game, Assets assets, MenuScreen menuScreen) {
        this.game = game;
        this.menuScreen = menuScreen;

        this.stageCafe = assets.getStageSelectAssets("cafe");
//        this.stageCafeText = assets.getStageSelectAssets("cafe-text");

        this.stageCinema = assets.getStageSelectAssets("cinema");
//        this.stageCinemaText = assets.getStageSelectAssets("cinema-text");

        this.stageHome = assets.getStageSelectAssets("home");
//        this.stageHomeText = assets.getStageSelectAssets("home-text");

        this.stageSchool = assets.getStageSelectAssets("school");
//        this.stageSchoolText = assets.getStageSelectAssets("school-text");

        this.stageHospital = assets.getStageSelectAssets("hospital");
//        this.stageHospitalText = assets.getStageSelectAssets("hospital-text");

        this.stageOffice = assets.getStageSelectAssets("office");
//        this.stageOfficeText = assets.getStageSelectAssets("office-text");

    }

    @Override
    public void render(float delta) {
        clearScreen(253, 243, 255, 1);

        game.batch.begin();
        drawCenter(game.batch,stageCafe, 20,20);
        drawCenter(game.batch,stageCinema, 20,20);
        drawCenter(game.batch,stageHome, 20,20);
        drawCenter(game.batch,stageSchool, 20,20);
        drawCenter(game.batch,stageHospital, 20,20);
        drawCenter(game.batch,stageOffice, 20,20);
        game.batch.end();
    }
}
