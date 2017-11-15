package com.trashmelody.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.ScanLineComponent;
import com.trashmelody.components.TransformComponent;

public class ScanLineSystem extends IteratingSystem {
    @Inject
    public ScanLineSystem() {
        super(Family.all(ScanLineComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScanLineComponent scanLine = Mapper.scanLine.get(entity);
        TransformComponent transform = Mapper.transform.get(entity);


    }
}
