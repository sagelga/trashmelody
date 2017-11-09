package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.Assets;

import static com.trashmelody.Utils.drawCenterX;
import static com.trashmelody.Utils.getViewportHeight;
import static com.trashmelody.Utils.getViewportWidth;

public class MenuButtons extends Actor {
    private Texture splashScreenLogo;
    private Texture bg, btnStart, btnCollection, btnSetting, btnExit, borderLeft, borderRight;
    private Texture badLogic = new Texture(Gdx.files.internal("badlogic.jpg"));
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

    @Inject
    MenuButtons (Assets assets) {
        this.splashScreenLogo   = assets.get(Assets.SPLASH_LOGO, Assets.TEXTURE);
        this.bg                 = assets.get(Assets.MENU_BG, Assets.TEXTURE);
        this.btnStart           = assets.get(Assets.MENU_BTN_START, Assets.TEXTURE);
        this.btnCollection      = assets.get(Assets.MENU_BTN_COLLECTION, Assets.TEXTURE);
        this.btnSetting         = assets.get(Assets.MENU_BTN_SETTING, Assets.TEXTURE);
        this.btnExit            = assets.get(Assets.MENU_BTN_EXIT, Assets.TEXTURE);
        this.borderLeft         = assets.get(Assets.MENU_BORDER_LEFT, Assets.TEXTURE);
        this.borderRight        = assets.get(Assets.MENU_BORDER_RIGHT, Assets.TEXTURE);

        setBounds(getViewportWidth()/2F, getViewportHeight()/2F, 400F, 400F);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(badLogic, getX(), getY(), getWidth(), getHeight());
        Gdx.app.log("MenuButtons actor", String.valueOf(getWidth()));
        Gdx.app.log("Screen height", String.valueOf(getViewportHeight()));
//        drawCenterX(batch, splashScreenLogo, 320F, 183F, 350F);
//        drawCenterX(batch, btnStart, 320F, 56F, 400F);
//        drawCenterX(batch, btnCollection, 320F, 56F, 300F);
//        drawCenterX(batch, btnSetting, 320F, 56F, 200F);
//        drawCenterX(batch, btnExit, 320F, 56F, 100F);
    }
}
