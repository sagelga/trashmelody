package com.trashmelody;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Binder;
import com.google.inject.Module;

public class GameModule implements Module {
    private TrashMelody trashMelody;

    GameModule(TrashMelody trashMelody) {
        this.trashMelody = trashMelody;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(SpriteBatch.class).toInstance(trashMelody.batch);
    }
}
