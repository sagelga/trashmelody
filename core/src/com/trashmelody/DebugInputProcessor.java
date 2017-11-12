package com.trashmelody;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.screens.*;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;


@Singleton
public class DebugInputProcessor implements InputProcessor {
    private ScreenProvider screenProvider;
    private TrashMelody game;
    private static Map<Integer, Class<? extends Screen>> SCREEN_MAPPER;

    static {
        SCREEN_MAPPER = HashMap.of(
                Input.Keys.G, GameScreen.class,
                Input.Keys.M, MenuScreen.class,
                Input.Keys.X, SandboxScreen.class,
                Input.Keys.P, PauseScreen.class,
                Input.Keys.S, StageSelectScreen.class,
                Input.Keys.R, ResultScreen.class,
                Input.Keys.N, NameScreen.class,
                Input.Keys.L, LoadingScreen.class,
                Input.Keys.C, CollectionScreen.class
        );
    }

    @Inject
    DebugInputProcessor(TrashMelody game, ScreenProvider screenProvider) {
        this.screenProvider = screenProvider;
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.Q:
                Gdx.app.exit();
                break;
            default:
                SCREEN_MAPPER.get(keycode).map(screenProvider::get).forEach(game::setScreen);
                Gdx.app.debug("Switching to:", SCREEN_MAPPER.get(keycode).toString());
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
