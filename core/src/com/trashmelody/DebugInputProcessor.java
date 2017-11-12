package com.trashmelody;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.trashmelody.MusicManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.screens.GameScreen;

import static com.trashmelody.Assets.MUSIC_BG1;

@Singleton
public class DebugInputProcessor implements InputProcessor {
    private ScreenProvider screenProvider;
    private MusicManager musicManager;

    @Inject
    DebugInputProcessor(ScreenProvider screenProvider, MusicManager musicManager) {
        this.screenProvider = screenProvider;
        this.musicManager = musicManager;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.Q:
                Gdx.app.exit();
            case Input.Keys.EQUALS:
                musicManager.increaseBackgroundVolume(MUSIC_BG1);
                break;
            case Input.Keys.PLUS:
                musicManager.increaseBackgroundVolume(MUSIC_BG1);
                break;
            case Input.Keys.MINUS:
                musicManager.decreaseBackgroundVolume(MUSIC_BG1);
                break;
            case Input.Keys.G:
                screenProvider.get(GameScreen.class);
                break;
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
