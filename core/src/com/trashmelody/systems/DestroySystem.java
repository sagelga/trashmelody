package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.PhysicsComponent;
import com.trashmelody.components.DestroyComponent;

public class DestroySystem extends IteratingSystem {
    private World world;

    @Inject
    DestroySystem(World world) {
        super(Family.all(DestroyComponent.class).get(), Systems.getIndex(DestroySystem.class));

        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicsComponent physics = Mapper.physics.get(entity);

        getEngine().removeEntity(entity);
        if (physics != null) {
            world.destroyBody(physics.body);
        }
    }
}
