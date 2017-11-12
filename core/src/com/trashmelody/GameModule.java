package com.trashmelody;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

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
//        ScreenProvider.screenClasses.forEach(screen -> binder.bind(screen).in(Singleton.class));
    }

    @Provides @Singleton
    public Camera provideCamera() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, getViewportWidth(), getViewportHeight());
        camera.update();
        return camera;
    }

    @Provides @Singleton
    public Viewport provideViewport(Camera camera) {
        return new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
    }
}
