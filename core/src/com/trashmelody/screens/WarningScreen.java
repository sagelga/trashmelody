package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.google.inject.Provider;
import com.trashmelody.*;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.trashmelody.Utils.*;

@Singleton
public class WarningScreen extends ScreenAdapter {
    private TrashMelody game;
    private Provider<MenuScreen> menuScreen;
    private Texture warningScreenLogo;
    private Texture warningScreenText;

    private long time_lapsed = TimeUtils.millis();

    @Inject
    public WarningScreen(TrashMelody game, Assets assets, ScreenProvider screenProvider) {
        this.game = game;
        this.menuScreen = screenProvider.getProvider(MenuScreen.class);

        this.warningScreenLogo = assets.get(Assets.WARNING_LOGO, Assets.TEXTURE);
        this.warningScreenText = assets.get(Assets.WARNING_TEXT, Assets.TEXTURE);
    }

    @Override
    public void render(float delta) {
        clearScreen(253,243,255,1);
        if (TimeUtils.timeSinceMillis(time_lapsed) > 5000) {
            game.setScreen(menuScreen.get());
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
}
