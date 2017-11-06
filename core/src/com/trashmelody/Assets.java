package com.trashmelody;

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


    public Assets() {
        assetManager = new AssetManager();
        loadFonts();
        loadImages();
        assetManager.finishLoading();
    }

    public <T> T get(String name, Class<T> type) {
        return assetManager.get(name, type);
    }


    public BitmapFont getSuperSpaceFont() {
        return assetManager.get("super-space-20px.ttf");
    }

    private AssetManager assetManager;

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
    }

    private void loadFonts() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BITMAP_FONT, ".ttf", new FreetypeFontLoader(resolver));

        assetManager.load("super-space-20px.ttf", BITMAP_FONT, getSuperSpaceFontParameter());
    }

    private static FreeTypeFontLoaderParameter getSuperSpaceFontParameter() {
        FreeTypeFontLoaderParameter loader = new FreeTypeFontLoaderParameter();
        loader.fontFileName = "fonts/Superspace Light ver 1.00.otf";
        loader.fontParameters.size = 20;
        loader.fontParameters.color = Color.BLACK;

        return loader;
    }

}
