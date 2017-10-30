package com.trashmelody;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.*;
import com.google.inject.Module;
import com.trashmelody.screens.*;

public class GameModule implements Module {
    private TrashMelody game;

    GameModule(TrashMelody trashMelody) {
        this.game = trashMelody;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(TrashMelody.class).toInstance(game);
    }

    @Provides
    @Singleton
    public SplashScreen provideSplashScreen(Assets assets, WarningScreen warningScreen) {
        return new SplashScreen(game, assets, warningScreen);
    }

    @Provides
    @Singleton
    public WarningScreen provideWarningScreen(Assets assets) {
        return new WarningScreen(game, assets, new MenuScreen(game));
    }

    @Provides
    @Singleton
    public Assets provideAssetManager() {
        return new Assets();
    }

}
