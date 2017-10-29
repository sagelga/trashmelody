package com.trashmelody.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.trashmelody.Assets;
import com.trashmelody.TrashMelody;
import com.trashmelody.Utils;
import static com.trashmelody.Utils.clearScreen;
import static com.trashmelody.Utils.drawCenter;

import javax.inject.Inject;
import javax.rmi.CORBA.Util;
import javax.xml.soap.Text;

public class WarningScreen extends ScreenAdapter {
    private TrashMelody game;
    private MenuScreen menuScreen;
    private Texture warningScreenLogo;
    private Texture warningScreenText;

    @Inject
    public WarningScreen(TrashMelody game, Assets assets, MenuScreen menuScreen) {
        this.game = game;
        this.menuScreen = menuScreen;
        this.warningScreenLogo = assets.getWarningScreenLogo();
        this.warningScreenText = assets.getWarningScreenText();
    }

    @Override
    public void render(float delta) {
        clearScreen();

        if (Utils.userPressSkip()) {
            game.setScreen(menuScreen);
        }

        game.batch.begin();
        game.batch.draw(warningScreenLogo, Utils.getViewportHeight()/2F, Utils.getViewportWidth()/4F,500F, 286F);
        drawCenter(game.batch, warningScreenText, 500F, 286F);
        game.font.draw(game.batch, "Splash Screen", 30, 40);
        game.batch.end();
    }
}
