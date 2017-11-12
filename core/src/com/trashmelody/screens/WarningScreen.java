package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.trashmelody.*;

import static com.trashmelody.Assets.*;
import static com.trashmelody.Utils.*;

@Singleton
public class WarningScreen extends LazyScreen {
    private TrashMelody game;
    private Provider<MenuScreen> menuScreen;
    private Texture warningScreenLogo;
    private Texture warningScreenText;

    private long time_lapsed = TimeUtils.millis();

    @Inject
    public WarningScreen(TrashMelody game, ScreenProvider screenProvider) {
        this.game = game;
        this.menuScreen = screenProvider.getProvider(MenuScreen.class);
    }

    @Override
    public void render(float delta) {
        clearScreen(253,243,255,1);
        if (TimeUtils.timeSinceMillis(time_lapsed) > 5000) {
            game.setLazyScreen(menuScreen.get());
        }

        // Start loading assets
        game.batch.begin();
        drawCenterX(game.batch, warningScreenLogo, 180F, 237F, getViewportHeight()/2);
        drawCenterX(game.batch, warningScreenText, 992F, 216F, 230F);

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Warning Screen",SplashScreen.splashScreenMusic.getVolume(),TimeUtils.timeSinceMillis(time_lapsed),1);
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
