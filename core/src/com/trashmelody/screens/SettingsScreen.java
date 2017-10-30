package com.trashmelody.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.google.inject.Inject;
import com.trashmelody.TrashMelody;

public class SettingsScreen extends ScreenAdapter {
    private TrashMelody game;

    @Inject
    public SettingsScreen(TrashMelody game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.font.draw(game.batch, "Settings Screen", 30, 40);
        game.batch.end();
    }

}
