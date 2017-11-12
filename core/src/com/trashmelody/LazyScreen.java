package com.trashmelody;

import com.badlogic.gdx.ScreenAdapter;

public abstract class LazyScreen extends ScreenAdapter {
    public abstract void loadLazyAssets(Assets assets);
    public abstract void getLazyAssets(Assets assets);
}
