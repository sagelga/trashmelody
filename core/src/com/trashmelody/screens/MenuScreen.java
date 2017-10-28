package com.trashmelody.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trashmelody.TrashMelody;

import javax.inject.Inject;

import static com.trashmelody.Utils.clearScreen;

public class MenuScreen extends ScreenAdapter {
    private TrashMelody game;

    @Inject
    public MenuScreen(TrashMelody game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        clearScreen();

        game.batch.begin();
        game.font.draw(game.batch, "Menu Screen", 30, 40);
        game.batch.end();
    }

    private void update(float delta) {

    }
}
