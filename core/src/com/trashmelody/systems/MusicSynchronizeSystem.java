package com.trashmelody.systems;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.Gdx;
import com.google.inject.Inject;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.ScanLineComponent;

public class MusicSynchronizeSystem extends IntervalSystem {
    @Inject
    public MusicSynchronizeSystem() {
        super(5, Systems.getIndex(MusicSynchronizeSystem.class));
    }

    @Override
    protected void updateInterval() {
        ScanLineComponent scanLine = getScanLineComponent();
        scanLine.elapsedTime = scanLine.music.getPosition() * 1000;
        Gdx.app.log(this.getClass().getName(), "Sync music with scan line");
    }

    private ScanLineComponent getScanLineComponent() {
        return Mapper.scanLine.get(getEngine().getEntitiesFor(Family.all(ScanLineComponent.class).get()).first());
    }
}
