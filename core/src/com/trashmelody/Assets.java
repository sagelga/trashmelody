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
