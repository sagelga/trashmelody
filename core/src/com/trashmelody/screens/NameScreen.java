package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.trashmelody.Utils.clearScreen;
import static com.trashmelody.Utils.drawCenterX;

@Singleton
public class NameScreen extends ScreenAdapter {
    private TrashMelody game;
    private Texture splashScreenLogo, nameScreenBG, nameScreenEnterBox, nameScreenEnterName, nameScreenCloud,
            nameScreenBorderLeft, nameScreenBorderRight;

    @Inject
    public NameScreen(TrashMelody game, Assets assets) {
        this.game = game;
        this.splashScreenLogo       = assets.get(Assets.SPLASH_LOGO, Assets.TEXTURE);
        this.nameScreenBG           = assets.get(Assets.NAME_BACKGROUND, Assets.TEXTURE);
        this.nameScreenEnterBox     = assets.get(Assets.NAME_ENTER_BOX, Assets.TEXTURE);
        this.nameScreenEnterName    = assets.get(Assets.NAME_ENTER_NAME, Assets.TEXTURE);
        this.nameScreenCloud        = assets.get(Assets.NAME_CLOUD, Assets.TEXTURE);
        this.nameScreenBorderLeft   = assets.get(Assets.NAME_BORDER, Assets.TEXTURE);
        this.nameScreenBorderRight  = assets.get(Assets.NAME_BORDER, Assets.TEXTURE);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        game.batch.begin();
        drawCenterX(game.batch, nameScreenBG, 1000F, 600F, 0F);
        drawCenterX(game.batch, splashScreenLogo, 325F, 193F, 275F);
        drawCenterX(game.batch, nameScreenEnterBox, 300F, 35F, 215F);
        drawCenterX(game.batch, nameScreenEnterName, 200F, 35F, 170F);
        drawCenterX(game.batch, nameScreenCloud, 750F, 300F, 160F);
        game.batch.draw(nameScreenBorderLeft,0,0,50,500);
        game.batch.draw(nameScreenBorderRight,855,0,-50,500);
        game.font.draw(game.batch, "Name Screen", 30, 40);


        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Name Screen");
        // Debug zone

        game.batch.end();
    }
}
