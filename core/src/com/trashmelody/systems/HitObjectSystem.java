package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;
import com.trashmelody.components.HitObjectComponent;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.ScanLineComponent;

public class HitObjectSystem extends IteratingSystem {
    @Inject
    public HitObjectSystem() {
        super(Family.all(HitObjectComponent.class).get(), Systems.getIndex(HitObjectSystem.class));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
    }
}
