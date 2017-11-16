package com.trashmelody.managers;

import com.badlogic.gdx.Screen;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.trashmelody.TrashMelody;
import com.trashmelody.screens.*;
import com.trashmelody.utils.Environment;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;

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
//        screens.forEach(screen -> MAPPER = MAPPER.put(screen.getClass(), screen));
    }

    @SuppressWarnings("unchecked")
    public <T extends Screen> Provider<T> getProvider(Class<T> screenClass) {
        return (Provider<T>) MAPPER.get(screenClass).getOrElseThrow(() -> new RuntimeException("Screen not found"));
    }

    public <T extends Screen> T get(Class<T> screenClass) {
        return getProvider(screenClass).get();
    }

    @SuppressWarnings("unchecked")
    public static Option<Class<? extends LazyScreen>> getScreenFromEnv() {
        Option<Class<? extends LazyScreen>> mayBeScreen = Environment.getClassFromEnv("com.trashmelody.screens.", "ENTRY_SCREEN").map(screen -> (Class<? extends LazyScreen>) screen);
        return mayBeScreen.filter(LazyScreen.class::isAssignableFrom);
    }
}
