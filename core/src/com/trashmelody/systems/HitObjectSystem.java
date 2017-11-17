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
    private World world;

    @Inject
    public HitObjectSystem(World world) {
        super(Family.all(HitObjectComponent.class).get(), Systems.getIndex(HitObjectSystem.class));

        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
    }
}
