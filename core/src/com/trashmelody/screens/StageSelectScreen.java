package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.*;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.MusicManager;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.utils.Debugger;

import java.util.ArrayList;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class StageSelectScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screens;
    private Camera camera;
    private MusicManager musicManager;
    private Assets assets;

    // Defining building value
    private Texture bdHomeShow;
    private Texture bdHomeHide;
    private Texture stageHomeText;
    private Texture bdCafeShow;
    private Texture bdCafeHide;
    private Texture stageCafeText;
    private Texture bdCinemaShow;
    private Texture bdCinemaHide;
    private Texture stageCinemaText;
    private Texture bdHospitalShow;
    private Texture bdHospitalHide;
    private Texture stageHospitalText;
    private Texture bdSchoolShow;
    private Texture bdSchoolHide;
    private Texture stageSchoolText;
    private Texture bdOfficeShow;
    private Texture bdOfficeHide;
    private Texture stageOfficeText;

    private Music music1;
    private Music music2;
    private Music music3;
    private Music music4;
    private Music music5;
    private Music music6;
//    private Music music7;

    private Texture buttonBack;
    private Texture buttonPlay;
    private Texture header;
    private Texture footer;
    private Texture cloud;
    private Texture trashworldLogo;
    private Texture selectArrowLeft;
    private Texture selectArrowRight;
    private Texture overlayBackground;
    private BitmapFont font;

    private int currentStageNumber = 0;
    private int modes;
    private int cooldown;

    @Inject
    StageSelectScreen(TrashMelody game, Camera camera, ScreenProvider screens, MusicManager musicManager) {
        this.game = game;
        this.screens = screens;
        this.camera = camera;
        this.musicManager = musicManager;
    }

    @Override
    public void show() {
        if (musicManager.getMusicPlaying(MUSIC_BG1)) {
            musicManager.stopMusic(MUSIC_BG1);
        }
        cooldown = 50;
    }

    @Override
    public void render(float delta) {
        clearScreen(253, 243, 255, 1);
//        camera.update();
//        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        // Show the logo and clouds
        drawCenter(game.batch, overlayBackground, 6464 / 6, 4460 / 6);
        drawCenter(game.batch, trashworldLogo, 2265 / 5, 1370 / 5);
        drawCenter(game.batch, cloud, 7507 / 6, 2644 / 6);

        // Show the header + footer of the game
        game.batch.draw(header, 0, getViewportHeight() - 100, 4485 / 5, 608 / 5);
        drawCenterX(game.batch, footer, getViewportWidth(), 296 / 5, 0);

        // Show the button interfaces
        game.batch.draw(buttonPlay, 20, 5, 670 / 5, 239 / 5);
        game.batch.draw(buttonBack, getViewportWidth() - 20 - (687 / 5), 5, 687 / 5, 236 / 5);
        game.batch.draw(selectArrowLeft, getViewportWidth() - (291 / 6), getViewportHeight() / 10, 291 / 8, 456 / 8);
        game.batch.draw(selectArrowRight, getViewportWidth() - (291 / 4), getViewportHeight() / 10, 291 / 8, 456 / 8);

        // Show the text of the selected item
        switch (currentStageNumber) {
            case (0):
                game.batch.draw(stageCafeText, 20, getViewportHeight() - 608 / 6, 1850 / 5, 487 / 6);
                game.batch.draw(bdCafeShow, 750F, 625F, 1608 / 7, 1062 / 7);

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_1_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);

                }
                break;
            case (1):
                game.batch.draw(stageCinemaText, 20, getViewportHeight() - 608 / 6, 2601 / 6, 487 / 6);
                game.batch.draw(bdCinemaShow, 1000F, 425F, 1539 / 7, 1901 / 7);

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_2_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);

                }
                break;
            case (2):
                game.batch.draw(stageHospitalText, 20, getViewportHeight() - 608 / 6, 3428 / 6, 487 / 6);
                game.batch.draw(bdHospitalShow, 1000F, 275F, 1919 / 7, 1402 / 7);

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_3_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);

                }
                break;
            case (3):
                game.batch.draw(stageSchoolText, 20, getViewportHeight() - 608 / 6, 2702 / 6, 487 / 6);
                game.batch.draw(bdSchoolShow, 600F, 150F, 2489 / 7, 1372 / 7);

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_4_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);

                }
                break;
            case (4):
                game.batch.draw(stageHomeText, 20, getViewportHeight() - 608 / 6, 2826 / 6, 487 / 6);
                game.batch.draw(bdHomeShow, 275F, 375F, 2176 / 7, 2164 / 7);

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_5_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);

                }
                break;
            case (5):
                game.batch.draw(stageOfficeText, 20, getViewportHeight() - 608 / 6, 2507 / 6, 487 / 6);
                game.batch.draw(bdOfficeShow, 450F, 625F, 2408 / 7, 1356 / 7);

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_6_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);

                }
                break;
            default:
                currentStageNumber %= 5;
        }


        // Show the stage building --------------------------------
        if (currentStageNumber != 0) {
            game.batch.draw(bdCafeHide, 750F, 625F, 1608 / 7, 1062 / 7);
        }
        if (currentStageNumber != 1) {
            game.batch.draw(bdCinemaHide, 1000F, 425F, 1539 / 7, 1901 / 7);
        }
        if (currentStageNumber != 2) {
            game.batch.draw(bdHospitalHide, 1000F, 275F, 1919 / 7, 1402 / 7);
        }
        if (currentStageNumber != 3) {
            game.batch.draw(bdSchoolHide, 600F, 150F, 2489 / 7, 1372 / 7);
        }
        if (currentStageNumber != 4) {
            game.batch.draw(bdHomeHide, 275F, 375F, 2176 / 7, 2164 / 7);
        }
        if (currentStageNumber != 5) {
            game.batch.draw(bdOfficeHide, 450F, 625F, 2408 / 7, 1356 / 7);
        }

        if (cooldown > 0) {
            cooldown--;
        }

//        if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
//            game.setLazyScreen(screens.get(MenuScreen.class));
//        }
//
//        if (Gdx.input.isKeyJustPressed(Input.Keys.C)){
//            game.setLazyScreen(screens.get(GameScreen.class));
//        }

        if (modes == 0) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_RIGHT)) {
                currentStageNumber = (currentStageNumber + 1) % 6;
                cooldown = 50;
                musicManager.stopMusic();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_LEFT)) {
                currentStageNumber = (currentStageNumber - 1) % 6;
                if (currentStageNumber < 0) {
                    currentStageNumber = 5;
                }
                cooldown = 50;
                musicManager.stopMusic();
            }
        }
//
//        if ((modes == 0) && (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))) {
//            modes++;
//            font.draw(game.batch, "Fuck you",getViewportWidth() - 100,getViewportHeight() - 100);
//
//        }

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font, "Stage Selection Screen");
        // Debug zone

        game.batch.end();
    }

    @Override
    public void loadAssets(Assets assets) {
        assets.load(STAGE_BD_SHOW_OFFICE, TEXTURE);
        assets.load(STAGE_BD_SHOW_CINEMA, TEXTURE);
        assets.load(STAGE_BD_SHOW_HOSPITAL, TEXTURE);
        assets.load(STAGE_BD_SHOW_SCHOOL, TEXTURE);
        assets.load(STAGE_BD_SHOW_HOME, TEXTURE);
        assets.load(STAGE_BD_SHOW_CAFE, TEXTURE);

        assets.load(STAGE_BD_HIDE_OFFICE, TEXTURE);
        assets.load(STAGE_BD_HIDE_CINEMA, TEXTURE);
        assets.load(STAGE_BD_HIDE_HOSPITAL, TEXTURE);
        assets.load(STAGE_BD_HIDE_SCHOOL, TEXTURE);
        assets.load(STAGE_BD_HIDE_HOME, TEXTURE);
        assets.load(STAGE_BD_HIDE_CAFE, TEXTURE);

        assets.load(STAGE_TEXT_OFFICE, TEXTURE);
        assets.load(STAGE_TEXT_CINEMA, TEXTURE);
        assets.load(STAGE_TEXT_HOSPITAL, TEXTURE);
        assets.load(STAGE_TEXT_SCHOOL, TEXTURE);
        assets.load(STAGE_TEXT_HOME, TEXTURE);
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

        assets.load(MUSIC_1_SONG, MUSIC);
        assets.load(MUSIC_2_SONG, MUSIC);
        assets.load(MUSIC_3_SONG, MUSIC);
        assets.load(MUSIC_4_SONG, MUSIC);
        assets.load(MUSIC_5_SONG, MUSIC);
        assets.load(MUSIC_6_SONG, MUSIC);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.bdOfficeShow       = assets.get(STAGE_BD_SHOW_OFFICE, TEXTURE); // 2408 × 1356
        this.bdCinemaShow       = assets.get(STAGE_BD_SHOW_CINEMA, TEXTURE); // 1539 × 1901
        this.bdHospitalShow     = assets.get(STAGE_BD_SHOW_HOSPITAL, TEXTURE); // 1919 × 1402
        this.bdSchoolShow       = assets.get(STAGE_BD_SHOW_SCHOOL, TEXTURE); // 2489 × 1372
        this.bdHomeShow         = assets.get(STAGE_BD_SHOW_HOME, TEXTURE); // 2176 × 2164
        this.bdCafeShow         = assets.get(STAGE_BD_SHOW_CAFE, TEXTURE); // 1608 x 1062

        this.bdOfficeHide       = assets.get(STAGE_BD_HIDE_OFFICE, TEXTURE);
        this.bdCinemaHide       = assets.get(STAGE_BD_HIDE_CINEMA, TEXTURE);
        this.bdHospitalHide     = assets.get(STAGE_BD_HIDE_HOSPITAL, TEXTURE);
        this.bdSchoolHide       = assets.get(STAGE_BD_HIDE_SCHOOL, TEXTURE);
        this.bdHomeHide         = assets.get(STAGE_BD_HIDE_HOME, TEXTURE);
        this.bdCafeHide         = assets.get(STAGE_BD_HIDE_CAFE, TEXTURE);

        this.stageHomeText      = assets.get(STAGE_TEXT_HOME, TEXTURE); // 2826 × 487
        this.stageOfficeText    = assets.get(STAGE_TEXT_OFFICE, TEXTURE); // 2507 × 487
        this.stageCafeText      = assets.get(STAGE_TEXT_CAFE, TEXTURE); // 2057 × 487
        this.stageCinemaText    = assets.get(STAGE_TEXT_CINEMA, TEXTURE); // 2601 × 487
        this.stageHospitalText  = assets.get(STAGE_TEXT_HOSPITAL, TEXTURE); // 3428 × 487
        this.stageSchoolText    = assets.get(STAGE_TEXT_SCHOOL, TEXTURE); // 2702 × 487

        this.buttonBack         = assets.get(STAGE_BG_BACKBUTTON, TEXTURE); // 687  × 236
        this.buttonPlay         = assets.get(STAGE_BG_PLAYBUTTON, TEXTURE); // 670  × 239
        this.header             = assets.get(STAGE_BG_HEADER, TEXTURE); // 4485 × 608
        this.footer             = assets.get(STAGE_BG_FOOTER, TEXTURE); // 8002 × 296
        this.cloud              = assets.get(STAGE_BG_CLOUD, TEXTURE); // 7507 × 2644
        this.trashworldLogo     = assets.get(STAGE_BG_TRASHWORLD, TEXTURE); // 2265 × 1370
        this.overlayBackground  = assets.get(STAGE_BG_OVERLAY, TEXTURE); // 6464 × 4460
        this.selectArrowLeft    = assets.get(STAGE_BG_ARROW_L, TEXTURE); // 291 × 456
        this.selectArrowRight   = assets.get(STAGE_BG_ARROW_R, TEXTURE); // 291 × 456

        this.music1             = assets.get(MUSIC_1_SONG, MUSIC);
        this.music2             = assets.get(MUSIC_2_SONG, MUSIC);
        this.music3             = assets.get(MUSIC_3_SONG, MUSIC);
        this.music4             = assets.get(MUSIC_4_SONG, MUSIC);
        this.music5             = assets.get(MUSIC_5_SONG, MUSIC);
        this.music6             = assets.get(MUSIC_6_SONG, MUSIC);

        this.font = assets.getSuperSpaceFont(42, Color.RED);

    }

    @Override
    public void hide() {
        currentStageNumber = 0;
        cooldown = 0;
        musicManager.stopMusic();
    }
}
