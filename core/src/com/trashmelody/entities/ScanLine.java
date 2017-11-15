package com.trashmelody.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.google.inject.Inject;
import com.trashmelody.components.*;

import static com.trashmelody.constants.B2Dvars.PPM;

public class ScanLine extends Entity {
    private World world;

    public ScanLine(World world,
                    ScanLineComponent scanLineComponent,
                    float velocity) {
        this.world = world;

        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2(0F, 0F), new Vector2(0F, 20F));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.groupIndex = TypeComponent.ITEM;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        TransformComponent transformComponent = new TransformComponent(0, 540 / PPM);

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setTransform(transformComponent.position, 0F);
        body.setUserData(this);

        PhysicsComponent physics = new PhysicsComponent(body, "Scan Line");
        physics.body.setLinearVelocity(velocity, 0F);

        super.add(transformComponent);
        super.add(new PhysicsComponent(body, "Scan Line"));
        super.add(new CollisionComponent());
        super.add(new TypeComponent(TypeComponent.ITEM));
        super.add(scanLineComponent);
        shape.dispose();
    }
}
