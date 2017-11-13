package com.trashmelody.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.*;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.utils.Debugger;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class WarningScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screens;
    private Texture warningScreenLogo;
    private Texture warningScreenText;
    private long timeLapsed;

    @Inject
    WarningScreen(TrashMelody game, ScreenProvider screens) {
        this.game = game;
        this.screens = screens;
    }

    @Override
    public void show() {
        timeLapsed = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        clearScreen(253, 243, 255, 1);

        if (TimeUtils.timeSinceMillis(timeLapsed) > 5000) {
            game.setLazyScreen(screens.get(MenuScreen.class));
        }

        // Start loading assets
        game.batch.begin();
        drawCenterX(game.batch, warningScreenLogo, 180F, 237F, getViewportHeight()/2);
        drawCenterX(game.batch, warningScreenText, 992F, 216F, 230F);

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Warning Screen",TimeUtils.timeSinceMillis(timeLapsed));
        // Debug zone

        game.batch.end();
    }

    @Override
    protected void loadAssets(Assets assets) {
        assets.load(WARNING_LOGO, TEXTURE);
        assets.load(WARNING_TEXT, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.warningScreenLogo = assets.get(WARNING_LOGO, TEXTURE);
        this.warningScreenText = assets.get(WARNING_TEXT, TEXTURE);
    }
}
