package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.google.inject.Inject;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.PhysicsComponent;
import com.trashmelody.components.ScanLineComponent;
import com.trashmelody.components.ScanLineComponent.State;

import java.util.function.Predicate;

import static com.trashmelody.constants.B2Dvars.PPM;
import static com.trashmelody.utils.Functional.isBetween;
import static com.trashmelody.utils.Functional.isLessThan;
import static com.trashmelody.utils.Functional.isMoreThan;

public class ScanLineSystem extends IteratingSystem {
    private static final float leftBorderX = 0;
    private static final float rightBorderX = 1920 / PPM;
    private static final Predicate<Float> isInBound = isBetween.apply(leftBorderX, rightBorderX);
    private static final Predicate<Float> isOverBound = isMoreThan.apply(rightBorderX);
    private static final Predicate<Float> isUnderBound = isLessThan.apply(leftBorderX);

    @Inject
    public ScanLineSystem() {
        super(Family.all(ScanLineComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicsComponent physics = Mapper.physics.get(entity);
        ScanLineComponent scanLine = Mapper.scanLine.get(entity);

        scanLine.elapsedTime += deltaTime * 1000;

        if (scanLine.state == State.Ready) {
            if (scanLine.elapsedTime > 0) {
                System.out.println("Music Started");
                scanLine.state = State.Playing;
                scanLine.music.play();
                scanLine.music.setVolume(0.3F);
                physics.body.setLinearVelocity(new Vector2(scanLine.velocity, 0F));
                return;
            } else {
                return;
            }
        }

        Vector2 velocity = physics.body.getLinearVelocity();
        Vector2 position = physics.body.getPosition();

        if (!isInBound.test(position.x)) {
            if (isUnderBound.test(position.x)) {
                physics.body.setLinearVelocity(scanLine.velocity, velocity.y);
            } else {
                physics.body.setLinearVelocity(-scanLine.velocity, velocity.y);
            }
        }
    }
}
