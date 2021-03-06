package com.trashmelody.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.trashmelody.components.*;
import com.trashmelody.components.TransformComponent.Align;
import com.trashmelody.constants.Constants;

import static com.trashmelody.constants.B2Dvars.PPM;
import static com.trashmelody.constants.Constants.scoreMap;

public class ScanLine extends Entity {

    public ScanLine(World world,
                    ScanLineComponent scanLineComponent,
                    TextureComponent textureComponent,
                    HealthComponent healthComponent) {

        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2(0F, 0F), new Vector2(0F, 20F));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.groupIndex = TypeComponent.ITEM;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        TransformComponent transformComponent = new TransformComponent(Constants.START_POSITION, 540 / PPM, - 457 / PPM, 538 / PPM);
        transformComponent.flipX = true;
        transformComponent.align = Align.UpperRight;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setTransform(transformComponent.position, 0F);
        body.setUserData(this);

        PhysicsComponent physics = new PhysicsComponent(body, "Scan Line");
        physics.body.setLinearVelocity(0F, 0F);

        scanLineComponent.endTime = scanLineComponent.hitObjects.last().getStartTime();
        scanLineComponent.score.fullScore = scanLineComponent.hitObjects.length() * scoreMap.get(ScoringComponent.Accuracy.Perfect).get();

        super.add(transformComponent);
        super.add(textureComponent);
        super.add(new PhysicsComponent(body, "Scan Line"));
        super.add(new CollisionComponent());
        super.add(new TypeComponent(TypeComponent.ITEM));
        super.add(scanLineComponent);
        super.add(healthComponent);

        shape.dispose();
    }
}
