package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;
import com.trashmelody.components.FallingTrashComponent;

public class FallingTrashSystem extends IteratingSystem {

    @Inject
    FallingTrashSystem() {
        super(Family.all(FallingTrashComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
    }

}
