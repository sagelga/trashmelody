package com.trashmelody;

import com.google.inject.*;
import com.trashmelody.screens.MenuScreen;
import com.trashmelody.screens.SettingsScreen;
import com.trashmelody.screens.SplashScreen;
import com.google.inject.Module;
import com.trashmelody.screens.WarningScreen;

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
    public SplashScreen provideSplashScreen(Assets assets, SettingsScreen settingsScreen,
                                            WarningScreen warningScreen, MenuScreen menuScreen) {
        return new SplashScreen(game, assets, menuScreen, settingsScreen, warningScreen);
    }

    @Provides
    @Singleton
    public WarningScreen provideWarningScreen(MenuScreen menuScreen, Assets assets) {
        return new WarningScreen(game, assets, menuScreen);
    }

    @Provides
    @Singleton
    public Assets provideAssetManager() {
        return new Assets();
    }

}
