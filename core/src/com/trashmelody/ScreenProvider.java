package com.trashmelody;

import com.badlogic.gdx.Screen;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.trashmelody.screens.*;
import io.vavr.collection.List;
import io.vavr.collection.Map;

public class ScreenProvider {
    public static List<Class<? extends Screen>> screenClassList;
    private Map<Class<? extends Screen>, Provider<? extends Screen>> MAPPER;

    static {
        screenClassList = List.of(
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
                ResultScreen.class
        );
    }

    @Inject
    ScreenProvider(TrashMelody game) {
        MAPPER = ScreenProvider.screenClassList.toMap(screen -> screen, screen -> game.injector.getProvider(screen));
    }

    @SuppressWarnings("unchecked")
    public <T extends Screen> Provider<T> getProvider(Class<T> screenClass) {
        return (Provider<T>) MAPPER.get(screenClass).getOrElseThrow(() -> new RuntimeException("Screen not found"));
    }

    public <T extends Screen> T get(Class<T> screenClass) {
        return getProvider(screenClass).get();
    }
}
