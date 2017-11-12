package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.LazyScreen;
import com.trashmelody.TrashMelody;

import static com.trashmelody.Assets.*;
import static com.trashmelody.Utils.clearScreen;
import static com.trashmelody.Utils.drawCenterX;

@Singleton
public class NameScreen extends LazyScreen {
    private TrashMelody game;
    private Texture splashScreenLogo, nameScreenBG, nameScreenEnterBox, nameScreenEnterName, nameScreenCloud,
            nameScreenBorderLeft, nameScreenBorderRight;

    @Inject
    public NameScreen(TrashMelody game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        clearScreen();

        game.batch.begin();
        drawCenterX(game.batch, splashScreenLogo, 325F, 193F, 275F);
        drawCenterX(game.batch, nameScreenBG, 1000F, 600F, 0F);
        drawCenterX(game.batch, nameScreenEnterBox, 300F, 35F, 215F);
        drawCenterX(game.batch, nameScreenEnterName, 200F, 35F, 170F);
        drawCenterX(game.batch, nameScreenCloud, 750F, 300F, 160F);
        game.batch.draw(nameScreenBorderLeft,0,0,50,500);
        game.batch.draw(nameScreenBorderRight,855,0,-50,500);

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Name Screen");
        // Debug zone

        game.batch.end();
    }

    @Override
    protected void loadAssets(Assets assets) {
        assets.load(SPLASH_LOGO, TEXTURE);
        assets.load(NAME_BACKGROUND, TEXTURE);
        assets.load(NAME_ENTER_BOX, TEXTURE);
        assets.load(NAME_ENTER_NAME, TEXTURE);
        assets.load(NAME_CLOUD, TEXTURE);
        assets.load(NAME_BORDER, TEXTURE);
        assets.load(NAME_BORDER, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.splashScreenLogo = assets.get(SPLASH_LOGO, TEXTURE);
        this.nameScreenBG = assets.get(NAME_BACKGROUND, TEXTURE);
        this.nameScreenEnterBox = assets.get(NAME_ENTER_BOX, TEXTURE);
        this.nameScreenEnterName = assets.get(NAME_ENTER_NAME, TEXTURE);
        this.nameScreenCloud = assets.get(NAME_CLOUD, TEXTURE);
        this.nameScreenBorderLeft = assets.get(NAME_BORDER, TEXTURE);
        this.nameScreenBorderRight = assets.get(NAME_BORDER, TEXTURE);
    }
}
