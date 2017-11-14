package com.trashmelody.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.trashmelody.managers.Assets;

public abstract class LazyScreen extends ScreenAdapter {
    private boolean loaded = false;

    public final void load(Assets assets) {
        if (loaded) return;
        loadAssets(assets);
    }

    public final boolean isLoaded() {
        return loaded;
    }

    public final void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    protected void loadAssets(Assets assets) {
    }

    public void afterLoad(Assets assets) {
    }
}
