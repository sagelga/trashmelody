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
    public Assets() {
        assetManager = new AssetManager();
        loadImages();
        assetManager.finishLoading();
    }

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

    public BitmapFont getSuperSpaceFont(Integer size, Color color) {
        return getFont("fonts/Superspace Bold ver 1.00.otf", size, color);
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
            case "office":          return assetManager.get("StageSelect/Building/stage-office.png", TEXTURE);
            case "office-text":     return assetManager.get("StageSelect/Text/stage-office-text.png", TEXTURE);

            case "cinema":          return assetManager.get("StageSelect/Building/stage-cinema.png", TEXTURE);
            case "cinema-text":     return assetManager.get("StageSelect/Text/stage-cinema-text.png", TEXTURE);

            case "hospital":        return assetManager.get("StageSelect/Building/stage-hospital.png", TEXTURE);
            case "hospital-text":   return assetManager.get("StageSelect/Text/stage-hospital-text.png", TEXTURE);

            case "school":          return assetManager.get("StageSelect/Building/stage-school.png", TEXTURE);
            case "school-text":     return assetManager.get("StageSelect/Text/stage-school-text.png", TEXTURE);

            case "home":            return assetManager.get("StageSelect/Building/stage-home.png", TEXTURE);
            case "home-text":       return assetManager.get("StageSelect/Text/stage-home-text.png", TEXTURE);

            case "cafe":            return assetManager.get("StageSelect/Building/stage-cafe.png", TEXTURE);
            case "cafe-text":       return assetManager.get("StageSelect/Text/stage-cafe-text.png", TEXTURE);

            case "back-button":     return assetManager.get("StageSelect/Background/stage-backbutton.png", TEXTURE);
            case "play-button":     return assetManager.get("StageSelect/Background/stage-playbutton.png", TEXTURE);
            case "cloud":           return assetManager.get("StageSelect/Background/stage-cloud.png", TEXTURE);

            case "header":          return assetManager.get("StageSelect/Background/stage-header.png", TEXTURE);
            case "footer":          return assetManager.get("StageSelect/Background/stage-footer.png", TEXTURE);
            case "trash-world":     return assetManager.get("StageSelect/Background/trashworld.png", TEXTURE);

        default:                    return assetManager.get("StageSelect/Background/trashworld.png", TEXTURE);
        }
    }

    public Texture getCollectionScreenAssets(String key) {
        switch (key) {
            case "bg": return assetManager.get("CollectionScreen/bg.jpg", TEXTURE);
            case "screenTitle": return assetManager.get("CollectionScreen/screen-title.png", TEXTURE);
            default: return assetManager.get("CollectionScreen/bg.jpg", TEXTURE);
        }
    }


    private AssetManager assetManager;
    private TreeMap<Integer, BitmapFont> loadedFonts;

    private static Class<Texture> TEXTURE = Texture.class;

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
        assetManager.load("CollectionScreen/bg.jpg", TEXTURE);
        assetManager.load("CollectionScreen/screen-title.png", TEXTURE);
    }

    private BitmapFont getFont(String name, Integer size, Color color) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(name));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;

        return generator.generateFont(parameter);
    }
}
