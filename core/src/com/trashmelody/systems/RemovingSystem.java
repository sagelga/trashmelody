package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.PhysicsComponent;
import com.trashmelody.components.RemovingComponent;
import com.trashmelody.components.TextureComponent;

public class RemovingSystem extends IteratingSystem {
    private World world;

    @Inject
    RemovingSystem(World world) {
        super(Family.all(RemovingComponent.class).get(), Systems.getIndex(RemovingSystem.class));

        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RemovingComponent removing = Mapper.removing.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);
        removing.lifeTime -= deltaTime * 1000;

        if (removing.lifeTime <= 0) {
            entity.remove(TextureComponent.class);
            getEngine().removeEntity(entity);
            if (physics != null) {
                world.destroyBody(physics.body);
            }
        }
    }
}
