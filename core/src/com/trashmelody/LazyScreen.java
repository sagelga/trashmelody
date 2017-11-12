package com.trashmelody;

import com.badlogic.gdx.ScreenAdapter;
import io.vavr.Tuple2;

import java.util.stream.Stream;

public abstract class LazyScreen extends ScreenAdapter {
    private boolean loaded = false;
    protected Stream<Tuple2<String, Class<?>>> fileNames;

    public final void load(Assets assets) {
        if (loaded) return;
        loadAssets(assets);
        loaded = true;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    protected void loadAssets(Assets assets) {
    }

    public void afterLoad(Assets assets) {
    }
}
