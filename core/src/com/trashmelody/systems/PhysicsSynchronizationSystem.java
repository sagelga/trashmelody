package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.google.inject.Inject;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.PhysicsComponent;
import com.trashmelody.components.TransformComponent;

public class PhysicsSynchronizationSystem extends IteratingSystem {

    @Inject
    public PhysicsSynchronizationSystem() {
        super(Family.all(
                TransformComponent.class,
                PhysicsComponent.class
        ).get(), Systems.getIndex(PhysicsSynchronizationSystem.class));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicsComponent physicsComponent = Mapper.physics.get(entity);
        TransformComponent transformComponent = Mapper.transform.get(entity);

        Body body = physicsComponent.body;
        transformComponent.position.set(body.getPosition());
        transformComponent.angelRadiant = body.getAngle();
    }
}
