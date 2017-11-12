package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.LazyScreen;
import com.trashmelody.TrashMelody;

import javax.inject.Inject;

import static com.trashmelody.Assets.*;
import static com.trashmelody.Utils.*;

@Singleton
public class StageSelectScreen extends LazyScreen {
    private TrashMelody game;
    private Provider<MenuScreen> menuScreen;
    private GameScreen gameScreen;
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
    private Texture selectArrowLeft;    private Texture selectArrowRight;
    private Texture overlayBackground;

    private String selected;

    @Inject
    public StageSelectScreen(TrashMelody game, Provider<MenuScreen> menuScreen, OrthographicCamera camera,GameScreen gameScreen) {
        this.game = game;
        this.menuScreen = menuScreen;
        this.gameScreen = gameScreen;
        this.camera = camera;
    }

    @Override
    public void show(){
        if (SplashScreen.splashScreenMusic.isPlaying()){
            SplashScreen.splashScreenMusic.stop();
            SplashScreen.splashScreenMusic.setLooping(false);
        }
    }

    @Override
    public void render(float delta) {
        clearScreen(253, 243, 255, 1);
//        camera.update();
//        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        // Show the logo and clouds
        drawCenter(game.batch,overlayBackground,6464/6,4460/6);
        drawCenter(game.batch,trashworldLogo,2265/5,1370/5);
        drawCenter(game.batch,cloud,7507/6,2644/6);

        // Show the header + footer of the game
        game.batch.draw(header,0,getViewportHeight()-100,4485/5,608/5);
        drawCenterX(game.batch,footer,getViewportWidth(),296/5,0);

        // Show the button interfaces
        game.batch.draw(buttonPlay,20,5,670/5,239/5);
        game.batch.draw(buttonBack,getViewportWidth()-20-(687/5),5,687/5,236/5);
        game.batch.draw(selectArrowLeft,getViewportWidth() - (2702/6),getViewportHeight()/10,2702/8,546/8);
        game.batch.draw(selectArrowRight,getViewportWidth() - (2702/4),getViewportHeight()/10,2702/8,546/8);

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

        if (Gdx.input.isKeyJustPressed(Input.Keys.C)){
            game.setScreen(gameScreen);
        }

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Stage Selection Screen");
        // Debug zone

        game.batch.end();
    }

    @Override
    public void loadLazyAssets(Assets assets) {
        assets.load(STAGE_BUILDING_OFFICE, TEXTURE);
        assets.load(STAGE_TEXT_OFFICE, TEXTURE);
        assets.load(STAGE_BUILDING_CINEMA, TEXTURE);
        assets.load(STAGE_TEXT_CINEMA, TEXTURE);
        assets.load(STAGE_BUILDING_HOSPITAL, TEXTURE);
        assets.load(STAGE_TEXT_HOSPITAL, TEXTURE);
        assets.load(STAGE_BUILDING_SCHOOL, TEXTURE);
        assets.load(STAGE_TEXT_SCHOOL, TEXTURE);
        assets.load(STAGE_BUILDING_HOME, TEXTURE);
        assets.load(STAGE_TEXT_HOME, TEXTURE);
        assets.load(STAGE_BUILDING_CAFE, TEXTURE);
        assets.load(STAGE_TEXT_CAFE, TEXTURE);
        assets.load(STAGE_BG_BACKBUTTON, TEXTURE);
        assets.load(STAGE_BG_PLAYBUTTON, TEXTURE);
        assets.load(STAGE_BG_CLOUD, TEXTURE);
        assets.load(STAGE_BG_HEADER, TEXTURE);
        assets.load(STAGE_BG_FOOTER, TEXTURE);
        assets.load(STAGE_BG_TRASHWORLD, TEXTURE);
        assets.load(STAGE_BG_OVERLAY, TEXTURE);
        assets.load(STAGE_BG_ARROW_L, TEXTURE);
        assets.load(STAGE_BG_ARROW_R, TEXTURE);
    }

    @Override
    public void getLazyAssets(Assets assets) {
        this.stageHome = assets.get(STAGE_BUILDING_HOME, TEXTURE);           // 2176 × 2164
        this.stageOffice = assets.get(STAGE_BUILDING_OFFICE, TEXTURE);         // 2408 × 1356
        this.stageCafe = assets.get(STAGE_BUILDING_CAFE, TEXTURE);           // 1608 x 1062
        this.stageCinema = assets.get(STAGE_BUILDING_CINEMA, TEXTURE);         // 1539 × 1901
        this.stageHospital = assets.get(STAGE_BUILDING_HOSPITAL, TEXTURE);       // 1919 × 1402
        this.stageSchool = assets.get(STAGE_BUILDING_SCHOOL, TEXTURE);         // 2489 × 1372

        this.stageHomeText = assets.get(STAGE_TEXT_HOME, TEXTURE);               // 2826 × 487
        this.stageOfficeText = assets.get(STAGE_TEXT_OFFICE, TEXTURE);             // 2507 × 487
        this.stageCafeText = assets.get(STAGE_TEXT_CAFE, TEXTURE);               // 2057 × 487
        this.stageCinemaText = assets.get(STAGE_TEXT_CINEMA, TEXTURE);             // 2601 × 487
        this.stageHospitalText = assets.get(STAGE_TEXT_HOSPITAL, TEXTURE);           // 3428 × 487
        this.stageSchoolText = assets.get(STAGE_TEXT_SCHOOL, TEXTURE);             // 2702 × 487

        this.buttonBack = assets.get(STAGE_BG_BACKBUTTON, TEXTURE);           // 687  × 236
        this.buttonPlay = assets.get(STAGE_BG_PLAYBUTTON, TEXTURE);           // 670  × 239
        this.header = assets.get(STAGE_BG_HEADER, TEXTURE);               // 4485 × 608
        this.footer = assets.get(STAGE_BG_FOOTER, TEXTURE);               // 8002 × 296
        this.cloud = assets.get(STAGE_BG_CLOUD, TEXTURE);                // 7507 × 2644
        this.trashworldLogo = assets.get(STAGE_BG_TRASHWORLD, TEXTURE);           // 2265 × 1370
        this.overlayBackground = assets.get(STAGE_BG_OVERLAY, TEXTURE);   // 6464 × 4460
        this.selectArrowLeft = assets.get(STAGE_BG_ARROW_L, TEXTURE);
        this.selectArrowRight = assets.get(STAGE_BG_ARROW_R, TEXTURE);
    }
}
