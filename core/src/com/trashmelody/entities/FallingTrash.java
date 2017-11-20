package com.trashmelody.entities;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.trashmelody.components.*;

import static com.trashmelody.constants.B2Dvars.PPM;
import static com.trashmelody.constants.Constants.yUpperBound;

public class FallingTrash extends Entity {

    public FallingTrash(World world,
                        Vector2 position,
                        TextureComponent texture,
                        TypeComponent type) {

        TransformComponent transformComponent = new TransformComponent(position.x, position.y, 0.8F, 0.8F);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.3F);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.groupIndex = type.getGroup();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setTransform(transformComponent.position, 0F);

        super.add(transformComponent);
        super.add(texture);
        super.add(new PhysicsComponent(body, "Falling trash"));
        super.add(new StateComponent());

        shape.dispose();
    }

}
