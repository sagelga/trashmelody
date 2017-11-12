package com.trashmelody;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.trashmelody.MusicManager;
import com.badlogic.gdx.Screen;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.screens.*;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

import static com.trashmelody.Assets.MUSIC_BG1;

@Singleton
public class DebugInputProcessor implements InputProcessor {
    private ScreenProvider screenProvider;
    private MusicManager musicManager;

    private TrashMelody game;
    private static Map<Integer, Class<? extends Screen>> MAPPER;

    static {
        MAPPER = HashMap.of(
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
    DebugInputProcessor(TrashMelody game, ScreenProvider screenProvider, MusicManager musicManager) {
        this.screenProvider = screenProvider;
        this.musicManager = musicManager;
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        Option<Screen> maybeScreen = MAPPER.get(keycode).map(screenProvider::get);
        switch (keycode) {
            case Input.Keys.Q:
                Gdx.app.exit();
            case Input.Keys.EQUALS:
                musicManager.increaseBackgroundVolume();
                break;
            case Input.Keys.MINUS:
                musicManager.decreaseBackgroundVolume();
                break;
            default:
                maybeScreen.forEach(screen -> Gdx.app.log("Switching to", screen.toString()));
                maybeScreen.forEach(game::setScreen);
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
