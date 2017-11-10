package com.trashmelody;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.*;
import io.vavr.control.Option;

public class Assets {

    // Splash Screen Assets
    public static final String SPLASH_LOGO              = "splash-logo.png";

    // Warning Screen Assets
    public static final String WARNING_TEXT             = "WarningScreen/warning-screen-text.png";
    public static final String WARNING_LOGO             = "WarningScreen/warning-screen-logo.png";

    // Menu Screen Assets
    public static final String MENU_BTN_START           = "MenuScreen/btn-start.png";
    public static final String MENU_BTN_COLLECTION      = "MenuScreen/btn-collection.png";
    public static final String MENU_BTN_SETTING         = "MenuScreen/btn-setting.png";
    public static final String MENU_BTN_EXIT            = "MenuScreen/btn-exit.png";
    public static final String MENU_BG                  = "MenuScreen/bg.png";
    public static final String MENU_BORDER_LEFT         = "MenuScreen/border-left.png";
    public static final String MENU_BORDER_RIGHT        = "MenuScreen/border-right.png";

    // Name Screen Assets
    public static final String NAME_ENTER_BOX           = "NameScreen/enterbox.png";
    public static final String NAME_ENTER_NAME          = "NameScreen/entername.png";
    public static final String NAME_CLOUD               = "NameScreen/cloud.png";
    public static final String NAME_BORDER              = "NameScreen/border.png";
    public static final String NAME_BACKGROUND          = "NameScreen/bg.png";

    // Stage Selection Screen Assets
    public static final String STAGE_BUILDING_OFFICE    = "StageSelect/Building/stage-office.png";
    public static final String STAGE_BUILDING_CINEMA    = "StageSelect/Building/stage-cinema.png";
    public static final String STAGE_BUILDING_HOSPITAL  = "StageSelect/Building/stage-hospital.png";
    public static final String STAGE_BUILDING_SCHOOL    = "StageSelect/Building/stage-school.png";
    public static final String STAGE_BUILDING_HOME      = "StageSelect/Building/stage-home.png";
    public static final String STAGE_BUILDING_CAFE      = "StageSelect/Building/stage-cafe.png";
    public static final String STAGE_TEXT_OFFICE        = "StageSelect/Text/stage-office-text.png";
    public static final String STAGE_TEXT_CINEMA        = "StageSelect/Text/stage-cinema-text.png";
    public static final String STAGE_TEXT_HOSPITAL      = "StageSelect/Text/stage-hospital-text.png";
    public static final String STAGE_TEXT_SCHOOL        = "StageSelect/Text/stage-school-text.png";
    public static final String STAGE_TEXT_HOME          = "StageSelect/Text/stage-home-text.png";
    public static final String STAGE_TEXT_CAFE          = "StageSelect/Text/stage-cafe-text.png";
    public static final String STAGE_BG_BACKBUTTON      = "StageSelect/Background/stage-backbutton.png";
    public static final String STAGE_BG_PLAYBUTTON      = "StageSelect/Background/stage-playbutton.png";
    public static final String STAGE_BG_CLOUD           = "StageSelect/Background/stage-cloud.png";
    public static final String STAGE_BG_HEADER          = "StageSelect/Background/stage-header.png";
    public static final String STAGE_BG_FOOTER          = "StageSelect/Background/stage-footer.png";
    public static final String STAGE_BG_TRASHWORLD      = "StageSelect/Background/trashworld.png";
    public static final String STAGE_BG_OVERLAY         = "StageSelect/Background/stage-overlay-background.png";
    public static final String STAGE_BG_ARROW_L         = "StageSelect/Background/stage-arrow-left.png";
    public static final String STAGE_BG_ARROW_R         = "StageSelect/Background/stage-arrow-right.png";

    // Collection Stage Assets
    public static final String COLLECTION_SCREEN_TITLE  = "CollectionScreen/screen-title.png";
    public static final String COLLECTION_BG            = "CollectionScreen/bg.jpg";

    // Countdown Stage Assets
    public static final String COUNTDOWN_BACKGROUND1    = "CountdownPage/background1.png";
    public static final String COUNTDOWN_COUNT_NUM_0    = "CountdownPage/count-num-0.png";
    public static final String COUNTDOWN_COUNT_NUM_1    = "CountdownPage/count-num-1.png";
    public static final String COUNTDOWN_COUNT_NUM_2    = "CountdownPage/count-num-2.png";
    public static final String COUNTDOWN_COUNT_NUM_3    = "CountdownPage/count-num-3.png";
    public static final String COUNTDOWN_COUNT_NUM_4    = "CountdownPage/count-num-4.png";
    public static final String COUNTDOWN_COUNT_NUM_5    = "CountdownPage/count-num-5.png";
    public static final String COUNTDOWN_RING           = "CountdownPage/ring.png";
    public static final String COUNTDOWN_STRIPE1        = "CountdownPage/stripe1.png";
    public static final String COUNTDOWN_STRIPE2        = "CountdownPage/stripe2.png";
    public static final String COUNTDOWN_STRIPE3        = "CountdownPage/stripe3.png";
    public static final String COUNTDOWN_STRIPE4        = "CountdownPage/stripe4.png";
    public static final String COUNTDOWN_STRIPE5        = "CountdownPage/stripe5.png";
    public static final String COUNTDOWN_STRIPE6        = "CountdownPage/stripe6.png";

    // Main Game Screen Assets
    public static final String GAME_BACKGROUND1         = "GameScreen/background1.png";
    public static final String GAME_BACKGROUND_FOOTER1  = "GameScreen/background-footer1.png";
    public static final String GAME_BIN_01              = "GameScreen/BIN-1.png";
    public static final String GAME_BIN_02              = "GameScreen/BIN-2.png";
    public static final String GAME_BIN_03              = "GameScreen/BIN-3.png";
    public static final String GAME_BIN_04              = "GameScreen/BIN-4.png";
    public static final String GAME_CENTER              = "GameScreen/CENTER.png";
    public static final String GAME_CHECK               = "GameScreen/CHECK.png";
    public static final String GAME_HEADER              = "GameScreen/HEADER.png";
    public static final String GAME_HEADER2             = "GameScreen/HEADER2.png";
    public static final String GAME_FOOTER              = "GameScreen/FOOTER.png";
    public static final String GAME_HANOI_1             = "GameScreen/HANOI-1.png";
    public static final String GAME_HANOI_2             = "GameScreen/HANOI-2.png";
    public static final String GAME_HANOI_3             = "GameScreen/HANOI-3.png";
    public static final String GAME_HANOI_4             = "GameScreen/HANOI-4.png";
    public static final String GAME_LEVEL_1             = "GameScreen/LEVEL-1.png";
    public static final String GAME_LEVEL_2             = "GameScreen/LEVEL-2.png";
    public static final String GAME_LEVEL_3             = "GameScreen/LEVEL-3.png";
    public static final String GAME_ICON                = "GameScreen/ICON.png";
    public static final String GAME_SONG_NAME_1         = "GameScreen/Song-Name-1.png";
    public static final String GAME_PAUSE               = "GameScreen/PAUSE.png";
    public static final String GAME_LEVEL_BORDER        = "GameScreen/Level-Border.png";
    public static final String GAME_SCORE               = "GameScreen/SCORE.png";
    public static final String GAME_SCORE_1             = "GameScreen/SCORE-1.png";
    public static final String GAME_SCORE_2             = "GameScreen/SCORE-2.png";
    public static final String GAME_SCORE_3             = "GameScreen/SCORE-3.png";
    public static final String GAME_SCORE_4             = "GameScreen/SCORE-4.png";
    public static final String GAME_SCORE_5             = "GameScreen/SCORE-5.png";
    public static final String GAME_STATUS_BAR          = "GameScreen/STATUS-BAR.png";

    // Pause Screen Assets
    public static final String PAUSE_CONTINUE_BTN1      = "PauseScreen/PAUSE-BUTTON-01.png";
    public static final String PAUSE_CONTINUE_BTN2      = "PauseScreen/PAUSE-BUTTON-02.png";
    public static final String PAUSE_SETTING_BTN1       = "PauseScreen/PAUSE-BUTTON-03.png";
    public static final String PAUSE_SETTING_BTN2       = "PauseScreen/PAUSE-BUTTON-04.png";
    public static final String PAUSE_RETRY_BTN1         = "PauseScreen/PAUSE-BUTTON-05.png";
    public static final String PAUSE_RETRY_BTN2         = "PauseScreen/PAUSE-BUTTON-06.png";
    public static final String PAUSE_HOME_BTN1          = "PauseScreen/PAUSE-BUTTON-07.png";
    public static final String PAUSE_HOME_BTN2          = "PauseScreen/PAUSE-BUTTON-08.png";
    public static final String PAUSE_SELECTOR_ARROW     = "PauseScreen/PAUSE-SIDE.png";

    // Calibrate Screen Assets
    public static final String CALIBRATE_BG             = "CalibrateScreen/Calibrate-Background.png";
    public static final String CALIBRATE_HEADER         = "CalibrateScreen/Calibrate-Header.png";
    public static final String CALIBRATE_FOOTER         = "CalibrateScreen/Calibrate-Footer.png";
    public static final String CALIBRATE_NUM_5          = "CalibrateScreen/Calibrate-Num-5.png";
    public static final String CALIBRATE_NUM_4          = "CalibrateScreen/Calibrate-Num-4.png";
    public static final String CALIBRATE_NUM_3          = "CalibrateScreen/Calibrate-Num-3.png";
    public static final String CALIBRATE_NUM_2          = "CalibrateScreen/Calibrate-Num-2.png";
    public static final String CALIBRATE_NUM_1          = "CalibrateScreen/Calibrate-Num-1.png";
    public static final String CALIBRATE_SPEED_POINTER  = "CalibrateScreen/Calibrate-Speed-Pointer.png";
    public static final String CALIBRATE_SPEED_BAR      = "CalibrateScreen/Calibrate-Speed-Bar.png";

    // Result Screen Assets
    public static final String RESULT_RESULT_BACKGROUND = "ResultScreen/Result-Background.png";
    public static final String RESULT_RESULT_HEADER     = "ResultScreen/Result-Header.png";
    public static final String RESULT_RESULT_FOOTER     = "ResultScreen/Result-Footer.png";
    public static final String RESULT_RESULT_GRADE_A    = "ResultScreen/Result-Grade-A.png";
    public static final String RESULT_RESULT_GRADE_B    = "ResultScreen/Result-Grade-B.png";
    public static final String RESULT_RESULT_GRADE_C    = "ResultScreen/Result-Grade-C.png";
    public static final String RESULT_RESULT_GRADE_D    = "ResultScreen/Result-Grade-D.png";
    public static final String RESULT_RESULT_GRADE_F    = "ResultScreen/Result-Grade-F.png";
    public static final String RESULT_RESULT_PERFECT    = "ResultScreen/Result-Perfect.png";
    public static final String RESULT_RESULT_GOOD       = "ResultScreen/Result-Good.png";
    public static final String RESULT_RESULT_NICE       = "ResultScreen/Result-Nice.png";
    public static final String RESULT_RESULT_MISS       = "ResultScreen/Result-Miss.png";
    public static final String RESULT_RESULT_COMBO      = "ResultScreen/Result-Combo.png";

    // Setting Screen Assets
    public static final String SETTING_BACK_BUTTON      = "SettingScreen/SETTING-BACK-BUTTON.png";
    public static final String SETTING_CALIBRATE        = "SettingScreen/SETTING-CALIBRATE.png";
    public static final String SETTING_EFFECT           = "SettingScreen/SETTING-EFFECT.png";
    public static final String SETTING_FULLSCREEN       = "SettingScreen/SETTING-FULLSCREEN.png";
    public static final String SETTING_HEADER           = "SettingScreen/SETTING-HEADER.png";
    public static final String SETTING_MUSIC            = "SettingScreen/SETTING-MUSIC.png";
    public static final String SETTING_SLIDE_BAR        = "SettingScreen/SETTING-SLIDE-BAR.png";
    public static final String SETTING_VOLUME           = "SettingScreen/SETTING-VOLUME.png";
    public static final String SETTING_VOLUME_BAR       = "SettingScreen/SETTING-VOLUME-BAR.png";
    public static final String SETTING_WINDOW           = "SettingScreen/SETTING-WINDOW.png";

    // Music Assets
    public static final String MUSIC_1_SONG             = "Song/1HITORIGOTO/AUDIO.mp3";
    public static final String MUSIC_1_BG_CLEAR         = "Song/1HITORIGOTO/BG1_CLEAR.png";
    public static final String MUSIC_1_BG_OPAC          = "Song/1HITORIGOTO/BG1_OPAC70.png";
    public static final String MUSIC_1_BG_BLUR          = "Song/1HITORIGOTO/BG1_BLUR.png";

    public static final String MUSIC_2_SONG             = "Song/2MARBLE SODA/AUDIO.mp3";
    public static final String MUSIC_2_BG_CLEAR         = "Song/2MARBLE SODA/BG1_CLEAR.jpg";
    public static final String MUSIC_2_BG_OPAC          = "Song/2MARBLE SODA/BG1_OPAC70.png";
    public static final String MUSIC_2_BG_BLUR          = "Song/2MARBLE SODA/BG1_BLUR.png";

    public static final String MUSIC_3_SONG             = "Song/3REUNIOUN/AUDIO.mp3";
    public static final String MUSIC_3_BG_CLEAR         = "Song/3REUNIOUN/BG1_CLEAR.jpg";
    public static final String MUSIC_3_BG_OPAC          = "Song/3REUNIOUN/BG1_OPAC70.jpg";
    public static final String MUSIC_3_BG_BLUR          = "Song/3REUNIOUN/BG1_BLUR.png";

    public static final String MUSIC_4_SONG             = "Song/4KANASHII URESHII/AUDIO.mp3";
    public static final String MUSIC_4_BG_CLEAR         = "Song/4KANASHII URESHII/BG1_CLEAR.jpg";
    public static final String MUSIC_4_BG_OPAC          = "Song/4KANASHII URESHII/BG1_OPAC70.png";
    public static final String MUSIC_4_BG_BLUR          = "Song/4KANASHII URESHII/BG1_BLUR.png";

    public static final String MUSIC_5_SONG             = "Song/5ORANGE/AUDIO.mp3";
    public static final String MUSIC_5_BG_CLEAR         = "Song/5ORANGE/BG1_CLEAR.jpg";
    public static final String MUSIC_5_BG_OPAC          = "Song/5ORANGE/BG1_OPAC70.png";
    public static final String MUSIC_5_BG_BLUR          = "Song/5ORANGE/BG1_BLUR.png";

    public static final String MUSIC_6_SONG             = "Song/6TELL YOUR WORLD/AUDIO.mp3";
    public static final String MUSIC_6_BG_CLEAR         = "Song/6TELL YOUR WORLD/BG1_CLEAR.jpg";
    public static final String MUSIC_6_BG_OPAC          = "Song/6TELL YOUR WORLD/BG1_OPAC70.jpg";
    public static final String MUSIC_6_BG_BLUR          = "Song/6TELL YOUR WORLD/BG1_BLUR.png";

    public static final String MUSIC_7_SONG             = "Song/7STEP AHEAD/AUDIO.mp3";
    public static final String MUSIC_7_BG_CLEAR         = "Song/7STEP AHEAD/BG1_CLEAR.png";
    public static final String MUSIC_7_BG_OPAC          = "Song/7STEP AHEAD/BG1_OPAC70.png";
    public static final String MUSIC_7_BG_BLUR          = "Song/7STEP AHEAD/BG1_BLUR.png";


    private void loadImages() {

        assetManager.load(WARNING_TEXT,             TEXTURE);
        assetManager.load(WARNING_LOGO,             TEXTURE);

        assetManager.load(MENU_BTN_START,           TEXTURE);
        assetManager.load(MENU_BTN_COLLECTION,      TEXTURE);
        assetManager.load(MENU_BTN_SETTING,         TEXTURE);
        assetManager.load(MENU_BTN_EXIT,            TEXTURE);
        assetManager.load(MENU_BG,                  TEXTURE);
        assetManager.load(MENU_BORDER_LEFT,         TEXTURE);
        assetManager.load(MENU_BORDER_RIGHT,        TEXTURE);

        assetManager.load(NAME_BACKGROUND,          TEXTURE);
        assetManager.load(NAME_ENTER_BOX,           TEXTURE);
        assetManager.load(NAME_ENTER_NAME,          TEXTURE);
        assetManager.load(NAME_CLOUD,               TEXTURE);
        assetManager.load(NAME_BORDER,              TEXTURE);

        assetManager.load(STAGE_BUILDING_OFFICE,    TEXTURE);
        assetManager.load(STAGE_TEXT_OFFICE,        TEXTURE);
        assetManager.load(STAGE_BUILDING_CINEMA,    TEXTURE);
        assetManager.load(STAGE_TEXT_CINEMA,        TEXTURE);
        assetManager.load(STAGE_BUILDING_HOSPITAL,  TEXTURE);
        assetManager.load(STAGE_TEXT_HOSPITAL,      TEXTURE);
        assetManager.load(STAGE_BUILDING_SCHOOL,    TEXTURE);
        assetManager.load(STAGE_TEXT_SCHOOL,        TEXTURE);
        assetManager.load(STAGE_BUILDING_HOME,      TEXTURE);
        assetManager.load(STAGE_TEXT_HOME,          TEXTURE);
        assetManager.load(STAGE_BUILDING_CAFE,      TEXTURE);
        assetManager.load(STAGE_TEXT_CAFE,          TEXTURE);
        assetManager.load(STAGE_BG_BACKBUTTON,      TEXTURE);
        assetManager.load(STAGE_BG_PLAYBUTTON,      TEXTURE);
        assetManager.load(STAGE_BG_CLOUD,           TEXTURE);
        assetManager.load(STAGE_BG_HEADER,          TEXTURE);
        assetManager.load(STAGE_BG_FOOTER,          TEXTURE);
        assetManager.load(STAGE_BG_TRASHWORLD,      TEXTURE);
        assetManager.load(STAGE_BG_OVERLAY,         TEXTURE);
        assetManager.load(STAGE_BG_ARROW_L,         TEXTURE);
        assetManager.load(STAGE_BG_ARROW_R,         TEXTURE);

        assetManager.load(COLLECTION_BG,            TEXTURE);
        assetManager.load(COLLECTION_SCREEN_TITLE,  TEXTURE);

        assetManager.load(COUNTDOWN_BACKGROUND1,    TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_0,    TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_1,    TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_2,    TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_3,    TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_4,    TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_5,    TEXTURE);
        assetManager.load(COUNTDOWN_RING,           TEXTURE);
        assetManager.load(COUNTDOWN_STRIPE1,        TEXTURE);
        assetManager.load(COUNTDOWN_STRIPE2,        TEXTURE);
        assetManager.load(COUNTDOWN_STRIPE3,        TEXTURE);
        assetManager.load(COUNTDOWN_STRIPE4,        TEXTURE);
        assetManager.load(COUNTDOWN_STRIPE5,        TEXTURE);
        assetManager.load(COUNTDOWN_STRIPE6,        TEXTURE);

        assetManager.load(GAME_BACKGROUND1,         TEXTURE);
        assetManager.load(GAME_BACKGROUND_FOOTER1,  TEXTURE);
        assetManager.load(GAME_BIN_01,              TEXTURE);
        assetManager.load(GAME_BIN_02,              TEXTURE);
        assetManager.load(GAME_BIN_03,              TEXTURE);
        assetManager.load(GAME_BIN_04,              TEXTURE);
        assetManager.load(GAME_CENTER,              TEXTURE);
        assetManager.load(GAME_CHECK,               TEXTURE);
        assetManager.load(GAME_HEADER,              TEXTURE);
        assetManager.load(GAME_HEADER2,             TEXTURE);
        assetManager.load(GAME_FOOTER,              TEXTURE);
        assetManager.load(GAME_HANOI_1,             TEXTURE);
        assetManager.load(GAME_HANOI_2,             TEXTURE);
        assetManager.load(GAME_HANOI_3,             TEXTURE);
        assetManager.load(GAME_HANOI_4,             TEXTURE);
        assetManager.load(GAME_LEVEL_1,             TEXTURE);
        assetManager.load(GAME_LEVEL_2,             TEXTURE);
        assetManager.load(GAME_LEVEL_3,             TEXTURE);
        assetManager.load(GAME_ICON,                TEXTURE);
        assetManager.load(GAME_SONG_NAME_1,         TEXTURE);
        assetManager.load(GAME_PAUSE,               TEXTURE);
        assetManager.load(GAME_LEVEL_BORDER,        TEXTURE);
        assetManager.load(GAME_SCORE,               TEXTURE);
        assetManager.load(GAME_SCORE_1,             TEXTURE);
        assetManager.load(GAME_SCORE_2,             TEXTURE);
        assetManager.load(GAME_SCORE_3,             TEXTURE);
        assetManager.load(GAME_SCORE_4,             TEXTURE);
        assetManager.load(GAME_SCORE_5,             TEXTURE);
        assetManager.load(GAME_STATUS_BAR,          TEXTURE);

        assetManager.load(PAUSE_CONTINUE_BTN1,      TEXTURE);
        assetManager.load(PAUSE_CONTINUE_BTN2,      TEXTURE);
        assetManager.load(PAUSE_SETTING_BTN1,       TEXTURE);
        assetManager.load(PAUSE_SETTING_BTN2,       TEXTURE);
        assetManager.load(PAUSE_RETRY_BTN1,         TEXTURE);
        assetManager.load(PAUSE_RETRY_BTN2,         TEXTURE);
        assetManager.load(PAUSE_HOME_BTN1,          TEXTURE);
        assetManager.load(PAUSE_HOME_BTN2,          TEXTURE);
        assetManager.load(PAUSE_SELECTOR_ARROW,     TEXTURE);

        assetManager.load(CALIBRATE_BG,             TEXTURE);
        assetManager.load(CALIBRATE_HEADER,         TEXTURE);
        assetManager.load(CALIBRATE_FOOTER,         TEXTURE);
        assetManager.load(CALIBRATE_NUM_5,          TEXTURE);
        assetManager.load(CALIBRATE_NUM_4,          TEXTURE);
        assetManager.load(CALIBRATE_NUM_3,          TEXTURE);
        assetManager.load(CALIBRATE_NUM_2,          TEXTURE);
        assetManager.load(CALIBRATE_NUM_1,          TEXTURE);
        assetManager.load(CALIBRATE_SPEED_POINTER,  TEXTURE);
        assetManager.load(CALIBRATE_SPEED_BAR,      TEXTURE);

        assetManager.load(RESULT_RESULT_BACKGROUND, TEXTURE);
        assetManager.load(RESULT_RESULT_HEADER,     TEXTURE);
        assetManager.load(RESULT_RESULT_FOOTER,     TEXTURE);
        assetManager.load(RESULT_RESULT_GRADE_A,    TEXTURE);
        assetManager.load(RESULT_RESULT_GRADE_B,    TEXTURE);
        assetManager.load(RESULT_RESULT_GRADE_C,    TEXTURE);
        assetManager.load(RESULT_RESULT_GRADE_D,    TEXTURE);
        assetManager.load(RESULT_RESULT_GRADE_F,    TEXTURE);
        assetManager.load(RESULT_RESULT_PERFECT,    TEXTURE);
        assetManager.load(RESULT_RESULT_GOOD,       TEXTURE);
        assetManager.load(RESULT_RESULT_NICE,       TEXTURE);
        assetManager.load(RESULT_RESULT_MISS,       TEXTURE);
        assetManager.load(RESULT_RESULT_COMBO,      TEXTURE);

        assetManager.load(SETTING_BACK_BUTTON,      TEXTURE);
        assetManager.load(SETTING_CALIBRATE,        TEXTURE);
        assetManager.load(SETTING_EFFECT,           TEXTURE);
        assetManager.load(SETTING_FULLSCREEN,       TEXTURE);
        assetManager.load(SETTING_HEADER,           TEXTURE);
        assetManager.load(SETTING_MUSIC,            TEXTURE);
        assetManager.load(SETTING_SLIDE_BAR,        TEXTURE);
        assetManager.load(SETTING_VOLUME,           TEXTURE);
        assetManager.load(SETTING_VOLUME_BAR,       TEXTURE);
        assetManager.load(SETTING_WINDOW,           TEXTURE);

        assetManager.load(MUSIC_1_SONG,             MUSIC);
        assetManager.load(MUSIC_1_BG_CLEAR,         TEXTURE);
        assetManager.load(MUSIC_1_BG_OPAC,          TEXTURE);
        assetManager.load(MUSIC_1_BG_BLUR,          TEXTURE);

        assetManager.load(MUSIC_2_SONG,             MUSIC);
        assetManager.load(MUSIC_2_BG_CLEAR,         TEXTURE);
        assetManager.load(MUSIC_2_BG_OPAC,          TEXTURE);
        assetManager.load(MUSIC_2_BG_BLUR,          TEXTURE);

        assetManager.load(MUSIC_3_SONG,             MUSIC);
        assetManager.load(MUSIC_3_BG_CLEAR,         TEXTURE);
        assetManager.load(MUSIC_3_BG_OPAC,          TEXTURE);
        assetManager.load(MUSIC_3_BG_BLUR,          TEXTURE);

        assetManager.load(MUSIC_4_SONG,             MUSIC);
        assetManager.load(MUSIC_4_BG_CLEAR,         TEXTURE);
        assetManager.load(MUSIC_4_BG_OPAC,          TEXTURE);
        assetManager.load(MUSIC_4_BG_BLUR,          TEXTURE);

        assetManager.load(MUSIC_5_SONG,             MUSIC);
        assetManager.load(MUSIC_5_BG_CLEAR,         TEXTURE);
        assetManager.load(MUSIC_5_BG_OPAC,          TEXTURE);
        assetManager.load(MUSIC_5_BG_BLUR,          TEXTURE);

        assetManager.load(MUSIC_6_SONG,             MUSIC);
        assetManager.load(MUSIC_6_BG_CLEAR,         TEXTURE);
        assetManager.load(MUSIC_6_BG_OPAC,          TEXTURE);
        assetManager.load(MUSIC_6_BG_BLUR,          TEXTURE);

        assetManager.load(MUSIC_7_SONG,             MUSIC);
        assetManager.load(MUSIC_7_BG_CLEAR,         TEXTURE);
        assetManager.load(MUSIC_7_BG_OPAC,          TEXTURE);
        assetManager.load(MUSIC_7_BG_BLUR,          TEXTURE);

    }

    private void eagerLoad() {
      // Assets that will load first
          assetManager.load(SPLASH_LOGO,              TEXTURE);
    }

//    private void song1Load(){
//     // Load songs + assets for stage 1
//    }

    public Assets() {
        assetManager = new AssetManager();
        eagerLoad();
        assetManager.finishLoading();
        loadImages();
    }

    public <T> T get(String name, Class<T> type) {
        return assetManager.get(name, type);
    }

    public AssetManager assetManager;

    private TreeMap<Integer, BitmapFont> loadedFonts;

    public static Class<Texture> TEXTURE = Texture.class;
    public static Class<Music> MUSIC = Music.class;


    public static Class<BitmapFont> BITMAP_FONT = BitmapFont.class;

    private BitmapFont getFont(String name, Integer size, Color color) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(name));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.genMipMaps = true;

        return generator.generateFont(parameter);
    }

    public BitmapFont getSuperSpaceFont(Integer size, Color color) {
        return getFont("fonts/Superspace Bold ver 1.00.otf", size, color);
    }
}
