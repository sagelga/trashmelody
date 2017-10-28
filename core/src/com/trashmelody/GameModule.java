package com.trashmelody;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.*;
import com.trashmelody.screens.MenuScreen;
import com.trashmelody.screens.SplashScreen;

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
    public SplashScreen provideSplashScreen(Assets assets) {
        return new SplashScreen(game, assets, new MenuScreen(game));
    }

    @Provides
    @Singleton
    public Assets provideAssetManager() {
        return new Assets();
    }

}
