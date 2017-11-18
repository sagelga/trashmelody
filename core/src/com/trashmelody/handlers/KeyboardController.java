package com.trashmelody.handlers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.TrashMelody;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.screens.MenuScreen;
import com.trashmelody.screens.PauseScreen;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Singleton
public class KeyboardController implements InputProcessor {
    TrashMelody game;
    ScreenProvider screens;
    //    public static final Map<String, Integer> ALL_KEYS = Keys
    public boolean left, right, up, down;
    public Map<Integer, Boolean> keyMap =
            IntStream.range(0, 256)
                    .boxed()
                    .collect(Collectors.toMap(Function.identity(), i -> false));
    public boolean isMouse1Down, isMouse2Down, isMouse3Down;
    public boolean isDragged;
    public Vector2 mouseLocation = new Vector2(0, 0);
    public boolean keyJustPressed(int keycode) {
        if(keyMap.get(keycode)) {
            keyMap.put(keycode, false);
            return true;
        }

        return false;
    }

    @Inject
    KeyboardController(TrashMelody game, ScreenProvider screens) {
        this.game = game;
        this.screens = screens;
    }

    @Override
    public boolean keyDown(int keycode) {
        keyMap.put(keycode, true);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keyMap.put(keycode, false);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
//        switch (character) {
//            case 'z':
//                game.setLazyScreen(screens.get(PauseScreen.class));
//        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == 0) {
            isMouse1Down = true;
        } else if (button == 1) {
            isMouse2Down = true;
        } else if (button == 2) {
            isMouse3Down = true;
        }
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;

        System.out.println(screenX);
        System.out.println(screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        isDragged = false;
        //System.out.println(button);
        if (button == 0) {
            isMouse1Down = false;
        } else if (button == 1) {
            isMouse2Down = false;
        } else if (button == 2) {
            isMouse3Down = false;
        }
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        isDragged = true;
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
