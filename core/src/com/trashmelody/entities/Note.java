package com.trashmelody.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.trashmelody.components.*;

import static com.trashmelody.constants.B2Dvars.PPM;

public class Note extends Entity {
    private World world;

    public Note(World world,
                NoteComponent noteComponent,
                TypeComponent type,
                TransformComponent transformComponent) {
        this.world = world;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.KinematicBody;
        Body body = world.createBody(bodyDef);

        shape.setRadius(1);
        fixtureDef.shape = shape;
        fixtureDef.filter.groupIndex = type.getGroup();
        body.createFixture(fixtureDef);
        shape.dispose();

        body.setTransform(transformComponent.position, 0F);

        super.add(transformComponent);
        super.add(new PhysicsComponent(body, "Note"));
        super.add(noteComponent);
        super.add(new StateComponent());
    }
}
