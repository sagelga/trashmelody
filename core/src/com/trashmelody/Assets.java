package com.trashmelody;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    private AssetManager assetManager;

    Assets() {
        assetManager = new AssetManager();
        // Default missing texture file
        assetManager.load("badlogic.png",Texture.class);

        //. Splash Logo Assets
        assetManager.load("splash-logo.png", Texture.class);

        // Warning Screen Assets
        assetManager.load("warning-screen-text.png", Texture.class);
        assetManager.load("warning-screen-logo.png", Texture.class);

        // Menu Screen Assets
        assetManager.load("MenuScreen/p4-btn-start.png", Texture.class);
        assetManager.load("MenuScreen/p4-btn-collection.png", Texture.class);
        assetManager.load("MenuScreen/p4-btn-setting.png", Texture.class);
        assetManager.load("MenuScreen/p4-btn-exit.png", Texture.class);
        assetManager.load("MenuScreen/p4-bg.png", Texture.class);
        assetManager.load("MenuScreen/p4-border-left.png", Texture.class);
        assetManager.load("MenuScreen/p4-border-right.png", Texture.class);

        // Stage Select Assets
        assetManager.load("Stage Select/stage-cinema.png",Texture.class);
        assetManager.load("Stage Select/stage-cinema-text.png",Texture.class);

        assetManager.load("Stage Select/stage-hospital.png",Texture.class);
        assetManager.load("Stage Select/stage-hospital-text.png",Texture.class);

        assetManager.load("Stage Select/stage-home.png",Texture.class);
        assetManager.load("Stage Select/stage-home-text.png",Texture.class);

        assetManager.load("Stage Select/stage-office.png",Texture.class);
        assetManager.load("Stage Select/stage-office-text.png",Texture.class);

        assetManager.load("Stage Select/stage-school.png",Texture.class);
        assetManager.load("Stage Select/stage-school-text.png",Texture.class);

        assetManager.load("Stage Select/stage-cafe.png",Texture.class);
        assetManager.load("Stage Select/stage-cafe-text.png",Texture.class);

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

    public Texture getStageSelectAssets(String stageAssetName){
        switch (stageAssetName){
            case "office": return assetManager.get("Stage Select/stage-office.png", TEXTURE);
            case "office-text": return assetManager.get("Stage Select/stage-office-text.png", TEXTURE);

            case "cinema": return assetManager.get("Stage Select/stage-cinema.png", TEXTURE);
            case "cinema-text": return assetManager.get("Stage Select/stage-cinema-text.png", TEXTURE);

            case "hospital": return assetManager.get("Stage Select/stage-hospital.png", TEXTURE);
            case "hospital-text": return assetManager.get("Stage Select/stage-hospital-text.png", TEXTURE);

            case "school": return assetManager.get("Stage Select/stage-school.png", TEXTURE);
            case "school-text": return assetManager.get("Stage Select/stage-school-text.png", TEXTURE);

            case "home": return assetManager.get("Stage Select/stage-home.png", TEXTURE);
            case "home-text": return assetManager.get("Stage Select/stage-home-text.png", TEXTURE);

            case "cafe": return assetManager.get("Stage Select/stage-cafe.png", TEXTURE);
            case "cafe-text": return assetManager.get("Stage Select/stage-cafe-text.png", TEXTURE);

        default: return assetManager.get("badlogic.png", TEXTURE);
        }
    }

    private static Class<Texture> TEXTURE = Texture.class;

}
