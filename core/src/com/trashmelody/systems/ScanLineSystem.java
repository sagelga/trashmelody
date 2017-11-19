package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.components.ScanLineComponent.State;
import com.trashmelody.screens.GameScreen;

import static com.trashmelody.constants.Constants.*;

public class ScanLineSystem extends IteratingSystem {
    @Inject
    public ScanLineSystem()  {
        super(Family.all(ScanLineComponent.class).get(), Systems.getIndex(ScanLineSystem.class));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicsComponent physics = Mapper.physics.get(entity);
        ScanLineComponent scanLine = Mapper.scanLine.get(entity);
        TransformComponent transform = Mapper.transform.get(entity);

        if (scanLine.state == State.Ready) {
            if (scanLine.elapsedTime > 0) {
                scanLine.state = State.Playing;
                scanLine.music.play();
                scanLine.music.setVolume(0.3F);
                scanLine.elapsedTime = scanLine.music.getPosition() * 1000;
                physics.body.setLinearVelocity(new Vector2(scanLine.velocity, 0F));
                return;
            } else {
                scanLine.elapsedTime += deltaTime * 1000;
                return;
            }
        }

        if (scanLine.state == State.Pause) {
            scanLine.state = State.Playing;
            scanLine.music.play();
            scanLine.elapsedTime = scanLine.music.getPosition() * 1000;
        }

        scanLine.activeHitObjects
            .dequeueOption()
            .map(tuple -> tuple._1)
            .filter(e -> scanLine.elapsedTime - Mapper.hitObject.get(e).hitObject.getStartTime() > HIT_OBJECT_LIFE_TIME)
            .peek(hitObjectEntity -> {
                HitObjectComponent hitObject = Mapper.hitObject.get(hitObjectEntity);
                hitObjectEntity.add(new ScoringComponent(ControlSystem.calculateDelta(scanLine, hitObject)));
                scanLine.activeHitObjects = scanLine.activeHitObjects.tail();
                scanLine.elapsedTime = scanLine.music.getPosition() * 1000;
            });

        scanLine.elapsedTime += deltaTime * 1000;
        setScanLineVelocity(scanLine, physics, transform);
    }

    private void setScanLineVelocity(ScanLineComponent scanLine, PhysicsComponent physics, TransformComponent transform) {
        if (!isInBound.test(physics.body.getPosition().x)) {
            if (isUnderBound.test(physics.body.getPosition().x)) {
                physics.body.setLinearVelocity(scanLine.velocity, 0F);
                transform.align = TransformComponent.Align.UpperRight;
                transform.flipX = true;
            } else {
                physics.body.setLinearVelocity(-scanLine.velocity, 0F);
                transform.align = TransformComponent.Align.UpperLeft;
                transform.flipX = false;
            }
        }
    }
}
