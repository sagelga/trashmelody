package com.trashmelody.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.google.inject.Inject;
import com.trashmelody.components.*;

import static com.trashmelody.constants.B2Dvars.PPM;

public class ScanLine extends Entity {
    private World world;

    @Inject
    ScanLine(World world) {
        this.world = world;

        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2(0F, 0F), new Vector2(0F, 0F));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.groupIndex = TypeComponent.ITEM;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        TransformComponent transformComponent = new TransformComponent(new Vector2(640 / PPM, 360 / PPM));

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setTransform(transformComponent.position, 0F);
        body.setUserData(this);

        super.add(transformComponent);
        super.add(new CollisionComponent());
        super.add(new TypeComponent(TypeComponent.ITEM));
        super.add(new ScanLineComponent());
        shape.dispose();
    }
}
