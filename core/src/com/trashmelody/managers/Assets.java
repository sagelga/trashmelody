package com.trashmelody.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Assets extends AssetManager {

    @Inject
    Assets() {
        super();

        load(MUSIC_BG1, Music.class);
        load(SFX_VOLUME_CHECK, Music.class);
        load(PLAYER_PLAY, Texture.class);
        load(PLAYER_MUTE, Texture.class);
        finishLoading();
    }

    private BitmapFont getFont(String name, Integer size, Color color) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(name));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;

        return generator.generateFont(parameter);
    }

    public BitmapFont getSuperSpaceFont(Integer size, Color color) {
        return getFont("fonts/Superspace Bold ver 1.00.otf", size, color);
    }

    public BitmapFont get8bitFont(Integer size, Color color) {
        return getFont("fonts/8-BIT WONDER.ttf", size, color);
    }

    public static Class<Texture> TEXTURE = Texture.class;
    public static Class<Music> MUSIC = Music.class;
    public static Class<Animation> ANIMATION = Animation.class;
    public static Class<BitmapFont> BITMAP_FONT = BitmapFont.class;

    // Splash Screen Assets
    public static final String SPLASH_LOGO = "splash-logo.png";

    // Loading Screen Assets
    public static final String LOADING_LOGO = "LoadingScreen/Loading_Logo.gif";

    // Warning Screen Assets
    public static final String WARNING_TEXT = "WarningScreen/warning-screen-text.png";

    // Menu Screen Assets
    public static final String MENU_BTN_START = "MenuScreen/btn-start.png";
    public static final String MENU_BTN_COLLECTION = "MenuScreen/btn-collection.png";
    public static final String MENU_BTN_SETTING = "MenuScreen/btn-setting.png";
    public static final String MENU_BTN_EXIT = "MenuScreen/btn-exit.png";
    public static final String MENU_BG = "MenuScreen/bg.png";
    public static final String MENU_BORDER_LEFT = "MenuScreen/border-left.png";
    public static final String MENU_BORDER_RIGHT = "MenuScreen/border-right.png";
    public static final String MENU_BTN_START_HOVER = "MenuScreen/btn-start-hover.png";
    public static final String MENU_BTN_COLLECTION_HOVER = "MenuScreen/btn-collection-hover.png";
    public static final String MENU_BTN_SETTING_HOVER = "MenuScreen/btn-setting-hover.png";
    public static final String MENU_BTN_EXIT_HOVER = "MenuScreen/btn-exit-hover.png";
    public static final String MENU_CLOUD = "MenuScreen/cloud.gif";

    // Name Screen Assets
    public static final String NAME_ENTER_BOX = "NameScreen/enterbox.png";
    public static final String NAME_ENTER_NAME = "NameScreen/entername.png";
    public static final String NAME_CLOUD = "NameScreen/cloud.png";
    public static final String NAME_BORDER = "NameScreen/border.png";
    public static final String NAME_BACKGROUND = "NameScreen/bg.png";

    // Stage Selection Screen Assets
    public static final String STAGE_BD_SHOW_OFFICE = "StageSelect/Building/Show/office.png";
    public static final String STAGE_BD_SHOW_CINEMA = "StageSelect/Building/Show/cinema.png";
    public static final String STAGE_BD_SHOW_HOSPITAL = "StageSelect/Building/Show/hospital.png";
    public static final String STAGE_BD_SHOW_SCHOOL = "StageSelect/Building/Show/school.png";
    public static final String STAGE_BD_SHOW_HOME = "StageSelect/Building/Show/home.png";
    public static final String STAGE_BD_SHOW_CAFE = "StageSelect/Building/Show/cafe.png";
    public static final String STAGE_BD_HIDE_OFFICE = "StageSelect/Building/Hide/office.png";
    public static final String STAGE_BD_HIDE_CINEMA = "StageSelect/Building/Hide/cinema.png";
    public static final String STAGE_BD_HIDE_HOSPITAL = "StageSelect/Building/Hide/hospital.png";
    public static final String STAGE_BD_HIDE_SCHOOL = "StageSelect/Building/Hide/school.png";
    public static final String STAGE_BD_HIDE_HOME = "StageSelect/Building/Hide/home.png";
    public static final String STAGE_BD_HIDE_CAFE = "StageSelect/Building/Hide/cafe.png";
    public static final String STAGE_BG_CLOUD = "StageSelect/Background/stage-cloud.png";
    public static final String STAGE_BG_HEADER = "StageSelect/Background/stage-header.png";
    public static final String STAGE_BG_TRASHWORLD = "StageSelect/Background/trashworld.png";
    public static final String STAGE_BG_ARROW_L = "StageSelect/Background/stage-arrow-left.png";
    public static final String STAGE_BG_ARROW_R = "StageSelect/Background/stage-arrow-right.png";
    public static final String STAGE_BG = "StageSelect/Background/stage-bg.png";

    // Collection Stage Assets
    public static final String COLLECTION_HEADER = "CollectionScreen/Header.png";
    public static final String COLLECTION_HEADER2 = "CollectionScreen/Header2.png";
    public static final String COLLECTION_BG = "CollectionScreen/BG.gif";
    public static final String COLLECTION_FOOTER = "CollectionScreen/Footer.png";
    public static final String COLLECTION_PACK = "CollectionScreen/CardPack.png";
    public static final String COLLECTION_RIGHT = "CollectionScreen/ArrowRight.png";
    public static final String COLLECTION_LEFT = "CollectionScreen/ArrowLeft.png";
    public static final String COLLECTION_RIGHT_H = "CollectionScreen/ArrowRightHover.png";
    public static final String COLLECTION_LEFT_H = "CollectionScreen/ArrowLeftHover.png";
    public static final String COLLECTION_DANGER_1 = "trashes/dangerous/cards/DANGER - CARD-01.png";
    public static final String COLLECTION_DANGER_2 = "trashes/dangerous/cards/DANGER - CARD-02.png";
    public static final String COLLECTION_DANGER_3 = "trashes/dangerous/cards/DANGER - CARD-03.png";
    public static final String COLLECTION_DANGER_4 = "trashes/dangerous/cards/DANGER - CARD-04.png";
    public static final String COLLECTION_DANGER_5 = "trashes/dangerous/cards/DANGER - CARD-05.png";
    public static final String COLLECTION_RECYCLE_1 = "trashes/recycle/cards/RE - CARD-01.png";
    public static final String COLLECTION_RECYCLE_2 = "trashes/recycle/cards/RE - CARD-02.png";
    public static final String COLLECTION_RECYCLE_3 = "trashes/recycle/cards/RE - CARD-03.png";
    public static final String COLLECTION_RECYCLE_4 = "trashes/recycle/cards/RE - CARD-04.png";
    public static final String COLLECTION_RECYCLE_5 = "trashes/recycle/cards/RE - CARD-05.png";
    public static final String COLLECTION_WET_1 = "trashes/wet/cards/FOOD - CARD-01.png";
    public static final String COLLECTION_WET_2 = "trashes/wet/cards/FOOD - CARD-02.png";
    public static final String COLLECTION_WET_3 = "trashes/wet/cards/FOOD - CARD-03.png";
    public static final String COLLECTION_WET_4 = "trashes/wet/cards/FOOD - CARD-04.png";
    public static final String COLLECTION_WET_5 = "trashes/wet/cards/FOOD - CARD-05.png";
    public static final String COLLECTION_STORY_BG = "CollectionScreen/Story-BG.png";
    public static final String COLLECTION_GENERAL_1 = "trashes/general/cards/general cards-01.png";
    public static final String COLLECTION_GENERAL_2 = "trashes/general/cards/general cards-02.png";
    public static final String COLLECTION_GENERAL_3 = "trashes/general/cards/general cards-03.png";
    public static final String COLLECTION_GENERAL_4 = "trashes/general/cards/general cards-04.png";
    public static final String COLLECTION_GENERAL_5 = "trashes/general/cards/general cards-05.png";

    // Countdown Stage Assets
    public static final String COUNTDOWN_BACKGROUND1 = "CountdownPage/background1.png";
    public static final String COUNTDOWN_COUNT_NUM_0 = "CountdownPage/count-num-0.png";
    public static final String COUNTDOWN_COUNT_NUM_1 = "CountdownPage/count-num-1.png";
    public static final String COUNTDOWN_COUNT_NUM_2 = "CountdownPage/count-num-2.png";
    public static final String COUNTDOWN_COUNT_NUM_3 = "CountdownPage/count-num-3.png";
    public static final String COUNTDOWN_COUNT_NUM_4 = "CountdownPage/count-num-4.png";
    public static final String COUNTDOWN_COUNT_NUM_5 = "CountdownPage/count-num-5.png";
    public static final String COUNTDOWN_RING = "CountdownPage/ring.png";
    public static final String COUNTDOWN_STRIPE1 = "CountdownPage/stripe1.png";
    public static final String COUNTDOWN_STRIPE2 = "CountdownPage/stripe2.png";
    public static final String COUNTDOWN_STRIPE3 = "CountdownPage/stripe3.png";
    public static final String COUNTDOWN_STRIPE4 = "CountdownPage/stripe4.png";
    public static final String COUNTDOWN_STRIPE5 = "CountdownPage/stripe5.png";
    public static final String COUNTDOWN_STRIPE6 = "CountdownPage/stripe6.png";

    // Game Screen Assets
    public static final String GAME_BACKGROUND1 = "GameScreen/background1.png";
    public static final String GAME_BACKGROUND_FOOTER1 = "GameScreen/background-footer1.png";
    public static final String GAME_BIN_01 = "GameScreen/BIN-1.png";
    public static final String GAME_BIN_02 = "GameScreen/BIN-2.png";
    public static final String GAME_BIN_03 = "GameScreen/BIN-3.png";
    public static final String GAME_BIN_04 = "GameScreen/BIN-4.png";
    public static final String GAME_BIN_05 = "GameScreen/BIN-5.png";
    public static final String GAME_CENTER = "GameScreen/CENTER.png";
    public static final String GAME_CHECK = "GameScreen/CHECK.png";
    public static final String GAME_HEADER = "GameScreen/HEADER.png";
    public static final String GAME_HEADER2 = "GameScreen/HEADER2.png";
    public static final String GAME_FOOTER = "GameScreen/FOOTER.png";
    public static final String GAME_HANOI_1 = "GameScreen/HANOI-1.png";
    public static final String GAME_HANOI_2 = "GameScreen/HANOI-2.png";
    public static final String GAME_HANOI_3 = "GameScreen/HANOI-3.png";
    public static final String GAME_HANOI_4 = "GameScreen/HANOI-4.png";
    public static final String GAME_LEVEL_1 = "GameScreen/LEVEL-1.png";
    public static final String GAME_LEVEL_2 = "GameScreen/LEVEL-2.png";
    public static final String GAME_LEVEL_3 = "GameScreen/LEVEL-3.png";
    public static final String GAME_ICON = "GameScreen/ICON.png";
    public static final String GAME_SONG_NAME_1 = "GameScreen/Song-Name-1.png";
    public static final String GAME_PAUSE = "GameScreen/PAUSE.png";
    public static final String GAME_LEVEL_BORDER = "GameScreen/Level-Border.png";
    public static final String GAME_SCORE = "GameScreen/SCORE.png";
    public static final String MISS_ACCURACY = "GameScreen/SCORE-1.png";
    public static final String BAD_ACCURACY = "GameScreen/SCORE-2.png";
    public static final String NICE_ACCURACY = "GameScreen/SCORE-3.png";
    public static final String GOOD_ACCURACY = "GameScreen/SCORE-4.png";
    public static final String PERFECT_ACCURACY = "GameScreen/SCORE-5.png";
    public static final String GAME_STATUS_BAR = "GameScreen/STATUS-BAR.png";

    // Pause Screen Assets
    public static final String PAUSE_CONTINUE_BTN1 = "PauseScreen/PAUSE-BUTTON-01.png";
    public static final String PAUSE_CONTINUE_BTN2 = "PauseScreen/PAUSE-BUTTON-02.png";
    public static final String PAUSE_SETTING_BTN1 = "PauseScreen/PAUSE-BUTTON-03.png";
    public static final String PAUSE_SETTING_BTN2 = "PauseScreen/PAUSE-BUTTON-04.png";
    public static final String PAUSE_RETRY_BTN1 = "PauseScreen/PAUSE-BUTTON-05.png";
    public static final String PAUSE_RETRY_BTN2 = "PauseScreen/PAUSE-BUTTON-06.png";
    public static final String PAUSE_HOME_BTN1 = "PauseScreen/PAUSE-BUTTON-07.png";
    public static final String PAUSE_HOME_BTN2 = "PauseScreen/PAUSE-BUTTON-08.png";
    public static final String PAUSE_SELECTOR_ARROW = "PauseScreen/PAUSE-SIDE.png";

    // Calibrate Screen Assets
    public static final String CALIBRATE_HEADER = "CalibrateScreen/Calibrate-Header.png";
    public static final String CALIBRATE_FOOTER = "CalibrateScreen/Calibrate-Footer.png";
    public static final String CALIBRATE_NUM_5 = "CalibrateScreen/Calibrate-Num-5.png";
    public static final String CALIBRATE_NUM_4 = "CalibrateScreen/Calibrate-Num-4.png";
    public static final String CALIBRATE_NUM_3 = "CalibrateScreen/Calibrate-Num-3.png";
    public static final String CALIBRATE_NUM_2 = "CalibrateScreen/Calibrate-Num-2.png";
    public static final String CALIBRATE_NUM_1 = "CalibrateScreen/Calibrate-Num-1.png";
    public static final String CALIBRATE_SPEED_POINTER = "CalibrateScreen/Calibrate-Speed-Pointer.png";
    public static final String CALIBRATE_SPEED_BAR = "CalibrateScreen/Calibrate-Speed-Bar.png";

    // Result Screen Assets
    public static final String RESULT_RESULT_BACKGROUND = "ResultScreen/Result-Background.png";
    public static final String RESULT_RESULT_HEADER = "ResultScreen/Result-Header.png";
    public static final String RESULT_RESULT_GRADE_A = "ResultScreen/Result-Grade-A.png";
    public static final String RESULT_RESULT_GRADE_B = "ResultScreen/Result-Grade-B.png";
    public static final String RESULT_RESULT_GRADE_C = "ResultScreen/Result-Grade-C.png";
    public static final String RESULT_RESULT_GRADE_D = "ResultScreen/Result-Grade-D.png";
    public static final String RESULT_RESULT_GRADE_F = "ResultScreen/Result-Grade-F.png";
//    public static final String RESULT_RESULT_PERFECT = "ResultScreen/Result-Perfect.png";
//    public static final String RESULT_RESULT_GOOD = "ResultScreen/Result-Good.png";
//    public static final String RESULT_RESULT_NICE = "ResultScreen/Result-Nice.png";
//    public static final String RESULT_RESULT_MISS = "ResultScreen/Result-Miss.png";
//    public static final String RESULT_RESULT_COMBO = "ResultScreen/Result-Combo.png";
    public static final String RESULT_RESULT_TEXT_ALL = "ResultScreen/Result-Text-All.png";

    // Setting Screen Assets
    public static final String SETTING_BACK_BUTTON = "SettingScreen/SETTING-BACK-BUTTON.png";
    public static final String SETTING_CALIBRATE = "SettingScreen/SETTING-CALIBRATE.png";
    public static final String SETTING_EFFECT = "SettingScreen/SETTING-EFFECT.png";
    public static final String SETTING_FULLSCREEN = "SettingScreen/SETTING-FULLSCREEN.png";
    public static final String SETTING_HEADER = "SettingScreen/SETTING-HEADER.png";
    public static final String SETTING_MUSIC = "SettingScreen/SETTING-MUSIC.png";
    public static final String SETTING_SLIDE_BAR = "SettingScreen/SETTING-SLIDE-BAR.png";
    public static final String SETTING_VOLUME = "SettingScreen/SETTING-VOLUME.png";
    public static final String SETTING_VOLUME_BAR = "SettingScreen/SETTING-VOLUME-BAR.png";
    public static final String SETTING_WINDOW = "SettingScreen/SETTING-WINDOW.png";

    // Background Music Assets
    public static final String MUSIC_BG1 = "Song/Background Music/Background_Music1.mp3";
    public static final String MUSIC_BG2 = "Song/Background Music/Background_Music2.mp3";

    // Game Music Assets
    public static final String MUSIC_1_SONG = "Song/1HITORIGOTO/AUDIO.mp3";
    public static final String MUSIC_1_BG_CLEAR = "Song/1HITORIGOTO/BG1_CLEAR.png";
    public static final String MUSIC_1_BG_OPAC = "Song/1HITORIGOTO/BG1_OPAC70.png";
    public static final String MUSIC_1_BG_BLUR = "Song/1HITORIGOTO/BG1_BLUR.png";
    public static final String MUSIC_2_SONG = "Song/2MARBLE SODA/AUDIO.mp3";
    public static final String MUSIC_2_BG_CLEAR = "Song/2MARBLE SODA/BG1_CLEAR.jpg";
    public static final String MUSIC_2_BG_OPAC = "Song/2MARBLE SODA/BG1_OPAC70.png";
    public static final String MUSIC_2_BG_BLUR = "Song/2MARBLE SODA/BG1_BLUR.png";
    public static final String MUSIC_3_SONG = "Song/3REUNIOUN/AUDIO.mp3";
    public static final String MUSIC_3_BG_CLEAR = "Song/3REUNIOUN/BG1_CLEAR.jpg";
    public static final String MUSIC_3_BG_OPAC = "Song/3REUNIOUN/BG1_OPAC70.jpg";
    public static final String MUSIC_3_BG_BLUR = "Song/3REUNIOUN/BG1_BLUR.png";
    public static final String MUSIC_4_SONG = "Song/4KANASHII URESHII/AUDIO.mp3";
    public static final String MUSIC_4_BG_CLEAR = "Song/4KANASHII URESHII/BG1_CLEAR.jpg";
    public static final String MUSIC_4_BG_OPAC = "Song/4KANASHII URESHII/BG1_OPAC70.png";
    public static final String MUSIC_4_BG_BLUR = "Song/4KANASHII URESHII/BG1_BLUR.png";
    public static final String MUSIC_5_SONG = "Song/5ORANGE/AUDIO.mp3";
    public static final String MUSIC_5_BG_CLEAR = "Song/5ORANGE/BG1_CLEAR.jpg";
    public static final String MUSIC_5_BG_OPAC = "Song/5ORANGE/BG1_OPAC70.png";
    public static final String MUSIC_5_BG_BLUR = "Song/5ORANGE/BG1_BLUR.png";
    public static final String MUSIC_6_SONG = "Song/6TELL YOUR WORLD/AUDIO.mp3";
    public static final String MUSIC_6_BG_CLEAR = "Song/6TELL YOUR WORLD/BG1_CLEAR.jpg";
    public static final String MUSIC_6_BG_OPAC = "Song/6TELL YOUR WORLD/BG1_OPAC70.jpg";
    public static final String MUSIC_6_BG_BLUR = "Song/6TELL YOUR WORLD/BG1_BLUR.png";

    // Sound Effects Assets
    public static final String SFX_VOLUME_CHECK = "SFx/VolumeCheck.wav";
    public static final String SFX_STAGE_CHANGE = "SFx/StageChange.wav";

    // Music change block Assets
    public static final String PLAYER_MUTE = "SFx/vol - mute.png";
    public static final String PLAYER_PLAY = "SFX/vol - template.png";

    // Hit Objects
    public static final String CIGARETTE_HIT_OBJECT = "trashes/dangerous/buttons/DANGER - BUT-03.png";
    public static final String HAIR_SPRAY_HIT_OBJECT = "trashes/dangerous/buttons/DANGER - BUT-02.png";
    public static final String OIL_CAN_HIT_OBJECT = "trashes/dangerous/buttons/DANGER - BUT-05.png";
    public static final String PLASTIC_BAG_HIT_OBJECT = "trashes/dangerous/buttons/DANGER - BUT-01.png";
    public static final String THINNER_HIT_OBJECT = "trashes/dangerous/buttons/DANGER - BUT-04.png";
    public static final String CARD_BOARD__HIT_OBJECT = "trashes/recycle/buttons/RE - BUT-05.png";
    public static final String GLASS_HIT_OBJECT = "trashes/recycle/buttons/RE - BUT-04.png";
    public static final String NOTE_HIT_OBJECT = "trashes/recycle/buttons/RE - BUT-02.png";
    public static final String PAPER_HIT_OBJECT = "trashes/recycle/buttons/RE - BUT-01.png";
    public static final String PLASTIC_BOX_HIT_OBJECT = "trashes/recycle/buttons/RE - BUT-03.png";
    public static final String CURRY_HIT_OBJECT = "trashes/wet/buttons/FOOD - BUT-03.png";
    public static final String DONUT_HIT_OBJECT = "trashes/wet/buttons/FOOD - BUT-02.png";
    public static final String ICE_CREAM_HIT_OBJECT = "trashes/wet/buttons/FOOD - BUT-05.png";
    public static final String MATCHA_HIT_OBJECT = "trashes/wet/buttons/FOOD - BUT-04.png";
    public static final String POPCORN_HIT_OBJECT = "trashes/wet/buttons/FOOD - BUT-01.png";
    public static final String PENCIL_HIT_OBJECT = "trashes/general/buttons/general - button-1.png";
    public static final String PLATE_HIT_OBJECT = "trashes/general/buttons/general - button-3.png";
    public static final String TOOTHPASTE_HIT_OBJECT = "trashes/general/buttons/general - button-4.png";
    public static final String RAG_HIT_OBJECT = "trashes/general/buttons/general - button-5.png";

    // Hit Objects
    public static final String CIGARETTE_TRASH = "trashes/dangerous/without-border/DANGER - BUT-03.png";
    public static final String HAIR_SPRAY_TRASH = "trashes/dangerous/without-border/DANGER - BUT-02.png";
    public static final String OIL_CAN_TRASH = "trashes/dangerous/without-border/DANGER - BUT-05.png";
    public static final String PLASTIC_BAG_TRASH = "trashes/dangerous/without-border/DANGER - BUT-01.png";
    public static final String THINNER_TRASH = "trashes/dangerous/without-border/DANGER - BUT-04.png";
    public static final String CARD_BOARD__TRASH = "trashes/recycle/without-border/RE - BUT-05.png";
    public static final String GLASS_TRASH = "trashes/recycle/without-border/RE - BUT-04.png";
    public static final String NOTE_TRASH = "trashes/recycle/without-border/RE - BUT-02.png";
    public static final String PAPER_TRASH = "trashes/recycle/without-border/RE - BUT-01.png";
    public static final String PLASTIC_BOX_TRASH = "trashes/recycle/without-border/RE - BUT-03.png";
    public static final String CURRY_TRASH = "trashes/wet/without-border/FOOD - BUT-03.png";
    public static final String DONUT_TRASH = "trashes/wet/without-border/FOOD - BUT-02.png";
    public static final String ICE_CREAM_TRASH = "trashes/wet/without-border/FOOD - BUT-05.png";
    public static final String MATCHA_TRASH = "trashes/wet/without-border/FOOD - BUT-04.png";
    public static final String POPCORN_TRASH = "trashes/wet/without-border/FOOD - BUT-01.png";
    public static final String PENCIL_TRASH = "trashes/general/without-border/general - button-1.png";
    public static final String PLATE_TRASH = "trashes/general/without-border/general - button-3.png";
    public static final String TOOTHPASTE_TRASH = "trashes/general/without-border/general - button-4.png";
    public static final String RAG_TRASH = "trashes/general/without-border/general - button-5.png";

    // Globally-Reusable Assets
    public static final String GLOBAL_ICON_BACK = "Global/icon-back.png";
    public static final String GLOBAL_ICON_PLAY = "Global/icon-play.png";
    public static final String GLOBAL_ICON_CONTINUE = "Global/icon-continue.png";
    public static final String GLOBAL_ICON_PAUSE = "Global/icon-pause.png";
    public static final String GLOBAL_FOOTER_BAR = "Global/footer-bar.png";
    public static final String GLOBAL_FOOTER_METAL_BAR = "Global/footer-metal-bar.png";

    //Game Guide Screen
    public static final String GUIDE_ICON_D = "KeyboardButtons/D.png";
    public static final String GUIDE_ICON_F = "KeyboardButtons/F.png";
    public static final String GUIDE_ICON_J = "KeyboardButtons/J.png";
    public static final String GUIDE_ICON_K = "KeyboardButtons/K.png";
    public static final String GUIDE_ANYKEY = "GuideScreen/anykey.gif";

}
