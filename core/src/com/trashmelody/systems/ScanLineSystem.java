package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.google.inject.Inject;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.PhysicsComponent;
import com.trashmelody.components.ScanLineComponent;
import com.trashmelody.components.TransformComponent;

import static com.trashmelody.components.ScanLineComponent.Direction;
import static com.trashmelody.constants.B2Dvars.PPM;

public class ScanLineSystem extends IteratingSystem {
    private static final float leftBorderX = 0;
    private static final float rightBorderX = 1920 / PPM;

    @Inject
    public ScanLineSystem() {
        super(Family.all(ScanLineComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScanLineComponent scanLine = Mapper.scanLine.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);

        Vector2 velocity = physics.body.getLinearVelocity();
        Vector2 position = physics.body.getPosition();

        if (position.x < leftBorderX || position.x > rightBorderX) {
            physics.body.setLinearVelocity(-velocity.x, velocity.y);
        }
    }
}
