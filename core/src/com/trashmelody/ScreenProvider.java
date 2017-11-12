package com.trashmelody;

import com.badlogic.gdx.Screen;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.trashmelody.screens.*;
import io.vavr.collection.List;
import io.vavr.collection.Map;

@Singleton
public class ScreenProvider {
    public static List<Class<? extends Screen>> screenClasses;
    private Map<Class<? extends Screen>, Provider<? extends Screen>> MAPPER;

    static {
        screenClasses = List.of(
                SplashScreen.class,
                LoadingScreen.class,
                WarningScreen.class,
                NameScreen.class,
                MenuScreen.class,
                SettingsScreen.class,
                StageSelectScreen.class,
                CollectionScreen.class,
                GameScreen.class,
                SandboxScreen.class,
                ResultScreen.class,
                PauseScreen.class
        );
    }

    @Inject
    ScreenProvider(TrashMelody game) {
        MAPPER = ScreenProvider.screenClasses.toMap(screen -> screen, screen -> game.injector.getProvider(screen));
    }

    @SuppressWarnings("unchecked")
    public <T extends Screen> Provider<T> getProvider(Class<T> screenClass) {
        return (Provider<T>) MAPPER.get(screenClass).getOrElseThrow(() -> new RuntimeException("Screen not found"));
    }

    public <T extends Screen> T get(Class<T> screenClass) {
        return getProvider(screenClass).get();
    }
}
