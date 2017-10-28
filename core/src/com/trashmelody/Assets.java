package com.trashmelody;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class Assets {
    private AssetManager assetManager;

    Assets() {
        assetManager = new AssetManager();
        assetManager.load("splash-logo.png", Texture.class);
        assetManager.finishLoading();
    }

    public Texture getSplashScreenLogo() {
        return assetManager.get("splash-logo.png", TEXTURE);
    }

    private static Class<Texture> TEXTURE = Texture.class;

}
