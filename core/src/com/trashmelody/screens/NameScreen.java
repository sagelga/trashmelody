package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;
import com.trashmelody.Utils;

import javax.inject.Inject;

import static com.trashmelody.Utils.clearScreen;
import static com.trashmelody.Utils.drawCenterX;

public class NameScreen extends ScreenAdapter {
    private TrashMelody game;
    private Texture splashScreenLogo, nameScreenBG, nameScreenEnterBox, nameScreenEnterName, nameScreenCloud,
            nameScreenBorderLeft, nameScreenBorderRight;

    @Inject
    public NameScreen(TrashMelody game, Assets assets) {
        this.game = game;
        this.splashScreenLogo = assets.getSplashScreenLogo();
        this.nameScreenBG = assets.getNameScreenBG();
        this.nameScreenEnterBox = assets.getNameScreenEnterBox();
        this.nameScreenEnterName = assets.getNameScreenEnterName();
        this.nameScreenCloud = assets.getNameScreenCloud();
        this.nameScreenBorderLeft = assets.getNameScreenBorder();
        this.nameScreenBorderRight = assets.getNameScreenBorder();
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

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            Debugger.debug_mode = !Debugger.debug_mode;
        }
        if (Debugger.debug_mode){
            Debugger.runDebugger(game.batch, game.font,"Naming Screen");
            Debugger.runAdvancedDebugger(game.batch,game.font,0,0);
        }
        // Debug zone

        game.batch.end();
    }

    private void update(float delta) {

    }
}
