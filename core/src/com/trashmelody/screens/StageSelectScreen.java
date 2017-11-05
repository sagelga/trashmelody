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
    private Texture stageHome;          private Texture stageHomeText;
    private Texture stageCafe;          private Texture stageCafeText;
    private Texture stageCinema;        private Texture stageCinemaText;
    private Texture stageHospital;      private Texture stageHospitalText;
    private Texture stageSchool;        private Texture stageSchoolText;
    private Texture stageOffice;        private Texture stageOfficeText;
    private Texture buttonBack;         private Texture buttonPlay;
    private Texture header;             private Texture footer;
    private Texture cloud;              private Texture trashworldLogo;
    private Texture overlayBackground;  private Texture selectArrow;

    private String selected;

    @Inject public StageSelectScreen(TrashMelody game, Assets assets, Provider<MenuScreen> menuScreen, OrthographicCamera camera) {
        this.game = game;
        this.menuScreen = menuScreen;
        this.camera = camera;
        //Variable handlers      |Assets retrival path                                               |Asset Resolution
        this.stageHome          = assets.getStageSelectAssets("home");              // 2176 × 2164
        this.stageOffice        = assets.getStageSelectAssets("office");            // 2408 × 1356
        this.stageCafe          = assets.getStageSelectAssets("cafe");              // 1608 x 1062
        this.stageCinema        = assets.getStageSelectAssets("cinema");            // 1539 × 1901
        this.stageHospital      = assets.getStageSelectAssets("hospital");          // 1919 × 1402
        this.stageSchool        = assets.getStageSelectAssets("school");            // 2489 × 1372

        this.stageHomeText      = assets.getStageSelectAssets("home-text");         // 2826 × 487
        this.stageOfficeText    = assets.getStageSelectAssets("office-text");       // 2507 × 487
        this.stageCafeText      = assets.getStageSelectAssets("cafe-text");         // 2057 × 487
        this.stageCinemaText    = assets.getStageSelectAssets("cinema-text");       // 2601 × 487
        this.stageHospitalText  = assets.getStageSelectAssets("hospital-text");     // 3428 × 487
        this.stageSchoolText    = assets.getStageSelectAssets("school-text");       // 2702 × 487

        this.buttonBack         = assets.getStageSelectAssets("back-button");       // 687  × 236
        this.buttonPlay         = assets.getStageSelectAssets("play-button");       // 670  × 239
        this.header             = assets.getStageSelectAssets("header");            // 4485 × 608
        this.footer             = assets.getStageSelectAssets("footer");            // 8002 × 296
        this.cloud              = assets.getStageSelectAssets("cloud");             // 7507 × 2644
        this.trashworldLogo     = assets.getStageSelectAssets("trash-world");       // 2265 × 1370
        this.overlayBackground  = assets.getStageSelectAssets("overlay-background");//
        this.selectArrow        = assets.getStageSelectAssets("select-arrow");      //
    }

    @Override
    public void render(float delta) {
        clearScreen(253, 243, 255, 1);
//        camera.update();
//        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        // Show the logo and clouds
        drawCenter(game.batch,trashworldLogo,2265/5,1370/5);
        drawCenter(game.batch,cloud,7507/6,2644/6);
        // Show the header + footer of the game
        game.batch.draw(header,0,getViewportHeight()-100,4485/5,608/5);
        drawCenterX(game.batch,footer,8002/5,296/5,0);
        // Show the button interfaces
        game.batch.draw(buttonPlay,20,5,670/5,239/5);
        game.batch.draw(buttonBack,getViewportWidth()-20-(687/5),5,687/5,236/5);

        selected = "Hospital"; // For debug purpose only
        // Show the text of the selected item
        switch (selected){
            case ("Cafe"):
                game.batch.draw(stageCafeText, 20,getViewportHeight()-608/6,2057/5,487/6);
                break;
            case("Cinema"):
                game.batch.draw(stageCinemaText,20,getViewportHeight()-608/6,2601/6,487/6);
                break;
            case("Home"):
                game.batch.draw(stageHomeText,20,getViewportHeight()-608/6,2826/6,487/6);
                break;
            case("Hospital"):
                game.batch.draw(stageHospitalText,20,getViewportHeight()-608/6,3428/6,487/6);
                break;
            case("School"):
                game.batch.draw(stageSchoolText,20,getViewportHeight()-608/6,2702/6,487/6);
                break;
            case("Office"):
                game.batch.draw(stageOfficeText,20,getViewportHeight()-608/6,2507/6,487/6);
        }
        // Show the stage building
        game.batch.draw(stageHome, 275F,375F,2176/9,2164/9);
        game.batch.draw(stageOffice, 450F,625F,2408/9,1356/9);
        game.batch.draw(stageCafe, 750F,625F,1608/9,1062/9);
        game.batch.draw(stageCinema, 1000F,425F,1539/9,1901/9);
        game.batch.draw(stageHospital, 1000F,275F,1919/9,1402/9);
        game.batch.draw(stageSchool, 600F,150F,2489/9,1372/9);


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
