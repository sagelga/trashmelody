package com.trashmelody;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.*;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.trashmelody.screens.*;

import static com.trashmelody.Utils.getViewportHeight;
import static com.trashmelody.Utils.getViewportWidth;

public class GameModule implements Module {
    private TrashMelody game;

    GameModule(TrashMelody trashMelody) {
        this.game = trashMelody;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(TrashMelody.class).toInstance(game);
        binder.bind(MenuScreen.class).in(Singleton.class);
        binder.bind(NameScreen.class).in(Singleton.class);
        binder.bind(SettingsScreen.class).in(Singleton.class);
        binder.bind(SplashScreen.class).in(Singleton.class);
        binder.bind(WarningScreen.class).in(Singleton.class);
        binder.bind(StageSelectScreen.class).in(Singleton.class);
        binder.bind(CollectionScreen.class).in(Singleton.class);
        binder.bind(SandboxScreen.class).in(Singleton.class);
    }

    @Provides @Singleton
    public SpriteBatch provideSpriteBatch() {
        return new SpriteBatch();
    }

    @Provides @Singleton
    public Camera provideCamera() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, getViewportWidth(), getViewportHeight());
        camera.update();
        return camera;
    }

    @Provides @Singleton
    public Viewport provideViewport(OrthographicCamera camera) {
        return new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
//        return new ScreenViewport(camera);
    }

    @Provides @Singleton
    public Assets provideAssetManager() {
        return new Assets();
    }

}
