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
        assetManager.load("warning-screen-text.png", Texture.class);
        assetManager.load("warning-screen-logo.png", Texture.class);
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
            default:
                return assetManager.get("MenuScreen/p4-btn-start.png", TEXTURE);
        }
    }

    private static Class<Texture> TEXTURE = Texture.class;

}
