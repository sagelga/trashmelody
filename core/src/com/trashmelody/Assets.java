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
    public Assets() {
        assetManager = new AssetManager();
        loadFonts();
        loadImages();
        assetManager.finishLoading();
    }

    public <T> T get(String name, Class<T> type) {
        return assetManager.get(name, type);
    }

    private static String WARNING_SCREEN_TEXT = "warning-screen-text.png";

    public Texture getSplashScreenLogo() {
        return assetManager.get(SPLASH_SCREEN_LOGO, TEXTURE);
    }

    public Texture getWarningScreenText() {
        return assetManager.get("warning-screen-text.png", TEXTURE);
    }

    public Texture getWarningScreenLogo() {
        return assetManager.get("warning-screen-logo.png", TEXTURE);
    }

    public Texture getNameScreenBG() {
        return assetManager.get("NameScreen/bg.png", TEXTURE);
    }

    public Texture getNameScreenEnterBox() {
        return assetManager.get("NameScreen/enterbox.png", TEXTURE);
    }

    public Texture getNameScreenEnterName() {
        return assetManager.get("NameScreen/entername.png", TEXTURE);
    }

    public Texture getNameScreenCloud() {
        return assetManager.get("NameScreen/cloud.png", TEXTURE);
    }

    public Texture getNameScreenBorder() {
        return assetManager.get("NameScreen/border.png", TEXTURE);
    }

    public BitmapFont getSuperSpaceFont() {
        return assetManager.get("super-space-20px.ttf");
    }

    public Texture getMenuScreenAssets(String key) {
        switch (key) {
            case "btnStart":
                return assetManager.get("MenuScreen/p4-btn-start.png", TEXTURE);
            case "btnCollection":
                return assetManager.get("MenuScreen/p4-btn-collection.png", TEXTURE);
            case "btnSetting":
                return assetManager.get("MenuScreen/p4-btn-setting.png", TEXTURE);
            case "btnExit":
                return assetManager.get("MenuScreen/p4-btn-exit.png", TEXTURE);
            case "borderLeft":
                return assetManager.get("MenuScreen/p4-border-left.png", TEXTURE);
            case "borderRight":
                return assetManager.get("MenuScreen/p4-border-right.png", TEXTURE);
            case "bg":
                return assetManager.get("MenuScreen/p4-bg.png", TEXTURE);
            default:
                return assetManager.get("MenuScreen/p4-btn-start.png", TEXTURE);
        }
    }

    public Texture getStageSelectAssets(String stageAssetName){
        switch (stageAssetName){
            case "office":              return assetManager.get("StageSelect/Building/stage-office.png", TEXTURE);
            case "office-text":         return assetManager.get("StageSelect/Text/stage-office-text.png", TEXTURE);

            case "cinema":              return assetManager.get("StageSelect/Building/stage-cinema.png", TEXTURE);
            case "cinema-text":         return assetManager.get("StageSelect/Text/stage-cinema-text.png", TEXTURE);

            case "hospital":            return assetManager.get("StageSelect/Building/stage-hospital.png", TEXTURE);
            case "hospital-text":       return assetManager.get("StageSelect/Text/stage-hospital-text.png", TEXTURE);

            case "school":              return assetManager.get("StageSelect/Building/stage-school.png", TEXTURE);
            case "school-text":         return assetManager.get("StageSelect/Text/stage-school-text.png", TEXTURE);

            case "home":                return assetManager.get("StageSelect/Building/stage-home.png", TEXTURE);
            case "home-text":           return assetManager.get("StageSelect/Text/stage-home-text.png", TEXTURE);

            case "cafe":                return assetManager.get("StageSelect/Building/stage-cafe.png", TEXTURE);
            case "cafe-text":           return assetManager.get("StageSelect/Text/stage-cafe-text.png", TEXTURE);

            case "back-button":         return assetManager.get("StageSelect/Background/stage-backbutton.png", TEXTURE);
            case "play-button":         return assetManager.get("StageSelect/Background/stage-playbutton.png", TEXTURE);
            case "cloud":               return assetManager.get("StageSelect/Background/stage-cloud.png", TEXTURE);

            case "header":              return assetManager.get("StageSelect/Background/stage-header.png", TEXTURE);
            case "footer":              return assetManager.get("StageSelect/Background/stage-footer.png", TEXTURE);
            case "trashworld":          return assetManager.get("StageSelect/Background/trashworld.png", TEXTURE);
            case "overlay-background":  return assetManager.get("StageSelect/Background/stage-overlay-background.png", TEXTURE);
            case "select-arrow":        return assetManager.get("StageSelect/Background/stage-select-arrow.png",TEXTURE);

        default:                        return assetManager.get("StageSelect/Background/trashworld.png", TEXTURE);
        }
    }


    private AssetManager assetManager;

    private static Class<Texture> TEXTURE = Texture.class;
    private static Class<BitmapFont> BITMAP_FONT = BitmapFont.class;

    private static final String SPLASH_SCREEN_LOGO = "splash-logo.png";

    private void loadImages() {
        assetManager.load(SPLASH_SCREEN_LOGO, TEXTURE);
        assetManager.load("MenuScreen/p4-btn-start.png", TEXTURE);
        assetManager.load("MenuScreen/p4-btn-collection.png", TEXTURE);
        assetManager.load("MenuScreen/p4-btn-setting.png", TEXTURE);
        assetManager.load("MenuScreen/p4-btn-exit.png", TEXTURE);
        assetManager.load("MenuScreen/p4-bg.png", TEXTURE);
        assetManager.load("MenuScreen/p4-border-left.png", TEXTURE);
        assetManager.load("MenuScreen/p4-border-right.png", TEXTURE);
        assetManager.load("warning-screen-text.png", TEXTURE);
        assetManager.load("warning-screen-logo.png", TEXTURE);
        assetManager.load("NameScreen/bg.png", TEXTURE);
        assetManager.load("NameScreen/enterbox.png", TEXTURE);
        assetManager.load("NameScreen/entername.png", TEXTURE);
        assetManager.load("NameScreen/cloud.png", TEXTURE);
        assetManager.load("NameScreen/border.png", TEXTURE);
        assetManager.load("StageSelect/Building/stage-office.png", TEXTURE);
        assetManager.load("StageSelect/Text/stage-office-text.png", TEXTURE);
        assetManager.load("StageSelect/Building/stage-cinema.png", TEXTURE);
        assetManager.load("StageSelect/Text/stage-cinema-text.png", TEXTURE);
        assetManager.load("StageSelect/Building/stage-hospital.png", TEXTURE);
        assetManager.load("StageSelect/Text/stage-hospital-text.png", TEXTURE);
        assetManager.load("StageSelect/Building/stage-school.png", TEXTURE);
        assetManager.load("StageSelect/Text/stage-school-text.png", TEXTURE);
        assetManager.load("StageSelect/Building/stage-home.png", TEXTURE);
        assetManager.load("StageSelect/Text/stage-home-text.png", TEXTURE);
        assetManager.load("StageSelect/Building/stage-cafe.png", TEXTURE);
        assetManager.load("StageSelect/Text/stage-cafe-text.png", TEXTURE);
        assetManager.load("StageSelect/Background/stage-backbutton.png", TEXTURE);
        assetManager.load("StageSelect/Background/stage-playbutton.png", TEXTURE);
        assetManager.load("StageSelect/Background/stage-cloud.png", TEXTURE);
        assetManager.load("StageSelect/Background/stage-header.png", TEXTURE);
        assetManager.load("StageSelect/Background/stage-footer.png", TEXTURE);
        assetManager.load("StageSelect/Background/trashworld.png", TEXTURE);
        assetManager.load("StageSelect/Background/stage-overlay-background.png", TEXTURE);
        assetManager.load("StageSelect/Background/stage-select-arrow.png",TEXTURE);
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
