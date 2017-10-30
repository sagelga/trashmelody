package com.trashmelody;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class Assets {
    private AssetManager assetManager;

    Assets() {
        assetManager = new AssetManager();
        assetManager.load("splash-logo.png", Texture.class);
        assetManager.load("MenuScreen/p4-btn-start.png", Texture.class);
        assetManager.load("MenuScreen/p4-btn-collection.png", Texture.class);
        assetManager.load("MenuScreen/p4-btn-setting.png", Texture.class);
        assetManager.load("MenuScreen/p4-btn-exit.png", Texture.class);
        assetManager.finishLoading();
    }

    public Texture getSplashScreenLogo() {
        return assetManager.get("splash-logo.png", TEXTURE);
    }

    public Texture getMenuScreenBtnStart() {
        return assetManager.get("MenuScreen/p4-btn-start.png", TEXTURE);
    }

    public Texture getMenuScreenBtnCollection() {
        return assetManager.get("MenuScreen/p4-btn-collection.png", TEXTURE);
    }

    public Texture getMenuScreenBtnSetting() {
        return assetManager.get("MenuScreen/p4-btn-setting.png", TEXTURE);
    }

    public Texture getMenuScreenBtnExit() {
        return assetManager.get("MenuScreen/p4-btn-exit.png", TEXTURE);
    }

    private static Class<Texture> TEXTURE = Texture.class;

}
