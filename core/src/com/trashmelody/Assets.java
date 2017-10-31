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
    private AssetManager assetManager;

    Assets() {
        assetManager = new AssetManager();

        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        assetManager.load("super-space-20px.ttf", BitmapFont.class, getSuperSpaceFontParameter());
        assetManager.load("splash-logo.png", Texture.class);
        assetManager.load("MenuScreen/p4-btn-start.png", Texture.class);
        assetManager.load("MenuScreen/p4-btn-collection.png", Texture.class);
        assetManager.load("MenuScreen/p4-btn-setting.png", Texture.class);
        assetManager.load("MenuScreen/p4-btn-exit.png", Texture.class);
        assetManager.load("MenuScreen/p4-bg.png", Texture.class);
        assetManager.load("MenuScreen/p4-border-left.png", Texture.class);
        assetManager.load("MenuScreen/p4-border-right.png", Texture.class);
        assetManager.load("warning-screen-text.png", Texture.class);
        assetManager.load("warning-screen-logo.png", Texture.class);
        assetManager.load("NameScreen/bg.png", Texture.class);
        assetManager.load("NameScreen/enterbox.png", Texture.class);
        assetManager.load("NameScreen/entername.png", Texture.class);
        assetManager.load("NameScreen/cloud.png", Texture.class);
        assetManager.load("NameScreen/border.png", Texture.class);
        assetManager.finishLoading();
    }

    public Texture getSplashScreenLogo() {
        return assetManager.get("splash-logo.png", TEXTURE);
    }
    public Texture getWarningScreenText() {
        return assetManager.get("warning-screen-text.png", TEXTURE);
    }
    public Texture getWarningScreenLogo() {
        return assetManager.get("warning-screen-logo.png", TEXTURE);
    }

    public Texture getMenuScreenAssets(String what) {
        switch (what) {
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


    private static Class<Texture> TEXTURE = Texture.class;

    private static FreeTypeFontLoaderParameter getSuperSpaceFontParameter() {
        FreeTypeFontLoaderParameter loader = new FreeTypeFontLoaderParameter();
        loader.fontFileName = "fonts/Superspace Light ver 1.00.otf";
        loader.fontParameters.size = 20;
        loader.fontParameters.color = Color.BLACK;

        return loader;
    }

}
