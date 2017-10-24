package com.trashmelody;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class GameModule implements Module {
    private TrashMelody trashMelody;

    GameModule(TrashMelody trashMelody) {
        this.trashMelody = trashMelody;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(SpriteBatch.class).toInstance(trashMelody.batch);
    }

    @Provides
    @Singleton
    public Assets provideAssetManager() {
        return new Assets();
    }

}
