package com.trashmelody.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;
import com.trashmelody.components.HitObjectComponent;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.PhysicsComponent;

public class HitObjectSystem extends IteratingSystem {
    private Engine engine;
    private World world;

    @Inject
    public HitObjectSystem(Engine engine, World world) {
        super(Family.all(HitObjectComponent.class).get());

        this.engine = engine;
        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HitObjectComponent hitObject = Mapper.hitObject.get(entity);
        PhysicsComponent physicsComponent = Mapper.physics.get(entity);

        if (hitObject.state == HitObjectComponent.State.Died) {
            engine.removeEntity(entity);
            world.destroyBody(physicsComponent.body);
        }
    }
}
