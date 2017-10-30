package com.trashmelody.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trashmelody.Assets;
import com.trashmelody.TrashMelody;
import com.trashmelody.Utils;

import javax.inject.Inject;
import javax.rmi.CORBA.Util;

import static com.trashmelody.Utils.clearScreen;
import static com.trashmelody.Utils.drawCenterX;

public class MenuScreen extends ScreenAdapter {
    private TrashMelody game;
    private Texture splashScreenLogo;
    private Texture btnStart, btnCollection, btnSetting, btnExit;
    private float vh = Utils.getViewportHeight();

    @Inject
    public MenuScreen(TrashMelody game, Assets assets) {
        this.game = game;
        this.splashScreenLogo = assets.getSplashScreenLogo();
        this.btnStart = assets.getMenuScreenBtnStart();
        this.btnCollection = assets.getMenuScreenBtnCollection();
        this.btnSetting = assets.getMenuScreenBtnSetting();
        this.btnExit = assets.getMenuScreenBtnExit();
    }

    @Override
    public void render(float delta) {
        clearScreen();

        game.batch.begin();
        drawCenterX(game.batch, splashScreenLogo, 500F, 286F, 520F);
        drawCenterX(game.batch, btnStart, 320F, 56F, 400F);
        drawCenterX(game.batch, btnCollection, 320F, 56F, 300F);
        drawCenterX(game.batch, btnSetting, 320F, 56F, 200F);
        drawCenterX(game.batch, btnExit, 320F, 56F, 100F);
        game.font.draw(game.batch, "Menu Screen", 30, 40);
        game.batch.end();
    }

    private void update(float delta) {

    }
}
