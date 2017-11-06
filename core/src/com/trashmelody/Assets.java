package com.trashmelody;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
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
    public static final String SPLASH_LOGO                  = "splash-logo.png";

    // Warning Screen Assets
    public static final String WARNING_TEXT                 = "WarningScreen/warning-screen-text.png";
    public static final String WARNING_LOGO                 = "WarningScreen/warning-screen-logo.png";

    // Menu Screen Assets
    public static final String MENU_BTN_START               = "MenuScreen/p4-btn-start.png";
    public static final String MENU_BTN_COLLECTION          = "MenuScreen/p4-btn-collection.png";
    public static final String MENU_BTN_SETTING             = "MenuScreen/p4-btn-setting.png";
    public static final String MENU_BTN_EXIT                = "MenuScreen/p4-btn-exit.png";
    public static final String MENU_BG                      = "MenuScreen/p4-bg.png";
    public static final String MENU_BORDER_LEFT             = "MenuScreen/p4-border-left.png";
    public static final String MENU_BORDER_RIGHT            = "MenuScreen/p4-border-right.png";

    // Name Screen Assets
    public static final String NAME_ENTER_BOX               = "NameScreen/enterbox.png";
    public static final String NAME_ENTER_NAME              = "NameScreen/entername.png";
    public static final String NAME_CLOUD                   = "NameScreen/cloud.png";
    public static final String NAME_BORDER                  = "NameScreen/border.png";
    public static final String NAME_BACKGROUND              = "NameScreen/bg.png";

    // Stage Selection Screen Assets
    public static final String STAGE_BUILDING_OFFICE        = "StageSelect/Building/stage-office.png";
    public static final String STAGE_BUILDING_CINEMA        = "StageSelect/Building/stage-cinema.png";
    public static final String STAGE_BUILDING_HOSPITAL      = "StageSelect/Building/stage-hospital.png";
    public static final String STAGE_BUILDING_SCHOOL        = "StageSelect/Building/stage-school.png";
    public static final String STAGE_BUILDING_HOME          = "StageSelect/Building/stage-home.png";
    public static final String STAGE_BUILDING_CAFE          = "StageSelect/Building/stage-cafe.png";
    public static final String STAGE_TEXT_OFFICE            = "StageSelect/Text/stage-office-text.png";
    public static final String STAGE_TEXT_CINEMA            = "StageSelect/Text/stage-cinema-text.png";
    public static final String STAGE_TEXT_HOSPITAL          = "StageSelect/Text/stage-hospital-text.png";
    public static final String STAGE_TEXT_SCHOOL            = "StageSelect/Text/stage-school-text.png";
    public static final String STAGE_TEXT_HOME              = "StageSelect/Text/stage-home-text.png";
    public static final String STAGE_TEXT_CAFE              = "StageSelect/Text/stage-cafe-text.png";
    public static final String STAGE_BG_BACKBUTTON          = "StageSelect/Background/stage-backbutton.png";
    public static final String STAGE_BG_PLAYBUTTON          = "StageSelect/Background/stage-playbutton.png";
    public static final String STAGE_BG_CLOUD               = "StageSelect/Background/stage-cloud.png";
    public static final String STAGE_BG_HEADER              = "StageSelect/Background/stage-header.png";
    public static final String STAGE_BG_FOOTER              = "StageSelect/Background/stage-footer.png";
    public static final String STAGE_BG_TRASHWORLD          = "StageSelect/Background/trashworld.png";
    public static final String STAGE_BG_OVERLAY_BACKGROUND  = "StageSelect/Background/stage-overlay-background.png";
    public static final String STAGE_BG_ARROW               = "StageSelect/Background/stage-select-arrow.png";

    // Collection Stage Assets
    public static final String COLLECTION_SCREEN_TITLE      = "CollectionScreen/screen-title.png";
    public static final String COLLECTION_BG                = "CollectionScreen/bg.jpg";

    // Countdown Stage Assets
    public static final String COUNTDOWN_BACKGROUND1        = "CountdownPage/background1.png";
    public static final String COUNTDOWN_COUNT_NUM_0        = "CountdownPage/count-num-0.png";
    public static final String COUNTDOWN_COUNT_NUM_1        = "CountdownPage/count-num-1.png";
    public static final String COUNTDOWN_COUNT_NUM_2        = "CountdownPage/count-num-2.png";
    public static final String COUNTDOWN_COUNT_NUM_3        = "CountdownPage/count-num-3.png";
    public static final String COUNTDOWN_COUNT_NUM_4        = "CountdownPage/count-num-4.png";
    public static final String COUNTDOWN_COUNT_NUM_5        = "CountdownPage/count-num-5.png";
    public static final String COUNTDOWN_RING               = "CountdownPage/ring.png";
    public static final String COUNTDOWN_STRIPE1            = "CountdownPage/stripe1.png";
    public static final String COUNTDOWN_STRIPE2            = "CountdownPage/stripe2.png";
    public static final String COUNTDOWN_STRIPE3            = "CountdownPage/stripe3.png";
    public static final String COUNTDOWN_STRIPE4            = "CountdownPage/stripe4.png";
    public static final String COUNTDOWN_STRIPE5            = "CountdownPage/stripe5.png";

    public static final String GAME_BACKGROUND1             = "GameScreen/background1.png";
    public static final String GAME_BACKGROUND_FOOTER1      = "GameScreen/background-footer1.png";
    public static final String GAME_BIN_01                  = "GameScreen/BIN-01.png";
    public static final String GAME_BIN_02                  = "GameScreen/BIN-02.png";
    public static final String GAME_BIN_03                  = "GameScreen/BIN-03.png";
    public static final String GAME_BIN_04                  = "GameScreen/BIN-04.png";
    public static final String GAME_CENTER                  = "GameScreen/CENTER.png";
    public static final String GAME_CHECK                   = "GameScreen/CHECK.png";
    public static final String GAME_HEADER                  = "GameScreen/HEADER.png";
    public static final String GAME_HEADER2                 = "GameScreen/HEADER2.png";
    public static final String GAME_FOOTER                  = "GameScreen/FOOTER.png";
    public static final String GAME_HANOI_1                 = "GameScreen/HANOI-1.png";
    public static final String GAME_HANOI_2                 = "GameScreen/HANOI-2.png";
    public static final String GAME_HANOI_3                 = "GameScreen/HANOI-3.png";
    public static final String GAME_HANOI_4                 = "GameScreen/HANOI-4.png";
    public static final String GAME_LEVEL_1                 = "GameScreen/LEVEL-1.png";
    public static final String GAME_LEVEL_2                 = "GameScreen/LEVEL-2.png";
    public static final String GAME_LEVEL_3                 = "GameScreen/LEVEL-3.png";
    public static final String GAME_ICON                    = "GameScreen/ICON.png";
    public static final String GAME_NAME_SONG_EXAMPLE       = "GameScreen/NAME-SONG-EXAMPLE.png";
    public static final String GAME_PAUSE                   = "GameScreen/PAUSE.png";
    public static final String GAME_RIGHT_LEVEL_LEFT        = "GameScreen/RIGHT-LEVEL-LEFT.png";
    public static final String GAME_SCORE                   = "GameScreen/SCORE.png";
    public static final String GAME_SCORE_1                 = "GameScreen/SCORE-1.png";
    public static final String GAME_SCORE_2                 = "GameScreen/SCORE-2.png";
    public static final String GAME_SCORE_3                 = "GameScreen/SCORE-3.png";
    public static final String GAME_SCORE_4                 = "GameScreen/SCORE-4.png";
    public static final String GAME_SCORE_5                 = "GameScreen/SCORE-5.png";
    public static final String GAME_STATUS_BAR              = "GameScreen/STATUS-BAR.png";


    public Assets() {
        assetManager = new AssetManager();
        loadImages();
        assetManager.finishLoading();
    }

    public <T> T get(String name, Class<T> type) {
        return assetManager.get(name, type);
    }

    private AssetManager assetManager;

    private TreeMap<Integer, BitmapFont> loadedFonts;
    public static Class<Texture> TEXTURE = Texture.class;

    public static Class<BitmapFont> BITMAP_FONT = BitmapFont.class;
    private void loadImages() {
        assetManager.load(SPLASH_LOGO, TEXTURE);

        assetManager.load(WARNING_TEXT, TEXTURE);
        assetManager.load(WARNING_LOGO, TEXTURE);

        assetManager.load(MENU_BTN_START, TEXTURE);
        assetManager.load(MENU_BTN_COLLECTION, TEXTURE);
        assetManager.load(MENU_BTN_SETTING, TEXTURE);
        assetManager.load(MENU_BTN_EXIT, TEXTURE);
        assetManager.load(MENU_BG, TEXTURE);
        assetManager.load(MENU_BORDER_LEFT, TEXTURE);
        assetManager.load(MENU_BORDER_RIGHT, TEXTURE);

        assetManager.load(NAME_BACKGROUND, TEXTURE);
        assetManager.load(NAME_ENTER_BOX, TEXTURE);
        assetManager.load(NAME_ENTER_NAME, TEXTURE);
        assetManager.load(NAME_CLOUD, TEXTURE);
        assetManager.load(NAME_BORDER, TEXTURE);

        assetManager.load(STAGE_BUILDING_OFFICE, TEXTURE);
        assetManager.load(STAGE_TEXT_OFFICE, TEXTURE);
        assetManager.load(STAGE_BUILDING_CINEMA, TEXTURE);
        assetManager.load(STAGE_TEXT_CINEMA, TEXTURE);
        assetManager.load(STAGE_BUILDING_HOSPITAL, TEXTURE);
        assetManager.load(STAGE_TEXT_HOSPITAL, TEXTURE);
        assetManager.load(STAGE_BUILDING_SCHOOL, TEXTURE);
        assetManager.load(STAGE_TEXT_SCHOOL, TEXTURE);
        assetManager.load(STAGE_BUILDING_HOME, TEXTURE);
        assetManager.load(STAGE_TEXT_HOME, TEXTURE);
        assetManager.load(STAGE_BUILDING_CAFE, TEXTURE);
        assetManager.load(STAGE_TEXT_CAFE, TEXTURE);
        assetManager.load(STAGE_BG_BACKBUTTON, TEXTURE);
        assetManager.load(STAGE_BG_PLAYBUTTON, TEXTURE);
        assetManager.load(STAGE_BG_CLOUD, TEXTURE);
        assetManager.load(STAGE_BG_HEADER, TEXTURE);
        assetManager.load(STAGE_BG_FOOTER, TEXTURE);
        assetManager.load(STAGE_BG_TRASHWORLD, TEXTURE);
        assetManager.load(STAGE_BG_OVERLAY_BACKGROUND, TEXTURE);
        assetManager.load(STAGE_BG_ARROW,TEXTURE);

        assetManager.load(COLLECTION_BG, TEXTURE);
        assetManager.load(COLLECTION_SCREEN_TITLE, TEXTURE);

        assetManager.load(COUNTDOWN_BACKGROUND1,TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_0,TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_1,TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_2,TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_3,TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_4,TEXTURE);
        assetManager.load(COUNTDOWN_COUNT_NUM_5,TEXTURE);
        assetManager.load(COUNTDOWN_RING,TEXTURE);
        assetManager.load(COUNTDOWN_STRIPE1,TEXTURE);
        assetManager.load(COUNTDOWN_STRIPE2,TEXTURE);
        assetManager.load(COUNTDOWN_STRIPE3,TEXTURE);
        assetManager.load(COUNTDOWN_STRIPE4,TEXTURE);
        assetManager.load(COUNTDOWN_STRIPE5,TEXTURE);

        assetManager.load(GAME_BACKGROUND1, TEXTURE);
        assetManager.load(GAME_BACKGROUND_FOOTER1, TEXTURE);
        assetManager.load(GAME_BIN_01, TEXTURE);
        assetManager.load(GAME_BIN_02, TEXTURE);
        assetManager.load(GAME_BIN_03, TEXTURE);
        assetManager.load(GAME_BIN_04, TEXTURE);
        assetManager.load(GAME_CENTER, TEXTURE);
        assetManager.load(GAME_CHECK, TEXTURE);
        assetManager.load(GAME_HEADER, TEXTURE);
        assetManager.load(GAME_HEADER2, TEXTURE);
        assetManager.load(GAME_FOOTER, TEXTURE);
        assetManager.load(GAME_HANOI_1, TEXTURE);
        assetManager.load(GAME_HANOI_2, TEXTURE);
        assetManager.load(GAME_HANOI_3, TEXTURE);
        assetManager.load(GAME_HANOI_4, TEXTURE);
        assetManager.load(GAME_LEVEL_1, TEXTURE);
        assetManager.load(GAME_LEVEL_2, TEXTURE);
        assetManager.load(GAME_LEVEL_3, TEXTURE);
        assetManager.load(GAME_ICON, TEXTURE);
        assetManager.load(GAME_NAME_SONG_EXAMPLE, TEXTURE);
        assetManager.load(GAME_PAUSE, TEXTURE);
        assetManager.load(GAME_RIGHT_LEVEL_LEFT, TEXTURE);
        assetManager.load(GAME_SCORE, TEXTURE);
        assetManager.load(GAME_SCORE_1, TEXTURE);
        assetManager.load(GAME_SCORE_2, TEXTURE);
        assetManager.load(GAME_SCORE_3, TEXTURE);
        assetManager.load(GAME_SCORE_4, TEXTURE);
        assetManager.load(GAME_SCORE_5, TEXTURE);
        assetManager.load(GAME_STATUS_BAR, TEXTURE);

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
}
