package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.PhysicsComponent;
import com.trashmelody.components.RemovingComponent;

public class RemovingSystem extends IteratingSystem {
    World world;

    @Inject
    RemovingSystem(World world) {
        super(Family.all(RemovingComponent.class).get());

        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RemovingComponent removing = Mapper.removing.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);
        removing.lifeTime -= deltaTime;

        if (removing.lifeTime <= 0) {
            if (physics != null) {
                world.destroyBody(physics.body);
            }
            getEngine().removeEntity(entity);
        }
    }
}
