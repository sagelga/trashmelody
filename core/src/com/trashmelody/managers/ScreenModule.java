package com.trashmelody.managers;

import com.badlogic.gdx.Screen;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.trashmelody.screens.*;
import io.vavr.collection.List;

public class ScreenModule extends AbstractModule {
    protected void configure() {
        Multibinder screenBinder = Multibinder.newSetBinder(binder(), Screen.class);
        screenBinder.addBinding().to(SplashScreen.class);
        screenBinder.addBinding().to(LoadingScreen.class);
//        List.of(
//                SplashScreen.class,
//                LoadingScreen.class,
//                WarningScreen.class,
//                NameScreen.class,
//                MenuScreen.class,
//                SettingsScreen.class,
//                StageSelectScreen.class,
//                CollectionScreen.class,
//                GameScreen.class,
//                SandboxScreen.class,
//                ResultScreen.class,
//                PauseScreen.class
//        ).map(screenBinder.addBinding()::to);
    }
}
