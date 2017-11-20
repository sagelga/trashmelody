package com.trashmelody.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.trashmelody.components.*;
import com.trashmelody.models.trashes.Trash;

import java.util.Random;

import static com.trashmelody.constants.B2Dvars.PPM;
import static com.trashmelody.constants.Constants.yUpperBound;

/* Beatmap Position
*  x (Integer) ranges from 0 to 512 (inclusive) and y (Integer) ranges from 0 to 384 (inclusive).
* */
public class HitObjectEntity extends Entity {
    private Random random = new Random();

    public HitObjectEntity(World world,
                           HitObjectComponent hitObjectComponent,
                           TypeComponent type,
                           TextureComponent textureComponent,
                           TimerComponent timerComponent,
                           Trash trash,
                           float dispatcherX) {

        Vector2 hitObjectPosition = hitObjectComponent.hitObject.getPosition().toGdxVector();
        float hitObjectX = dispatcherX;
        float hitObjectY = (hitObjectPosition.y / yUpperBound * 300 + 640) / PPM;
//        float hitObjectY = (random.nextFloat() * 240F + 650) / PPM; random y-position
        TransformComponent transformComponent = new TransformComponent(hitObjectX, hitObjectY, 0.8F, 0.8F);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.3F);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.groupIndex = type.getGroup();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setTransform(transformComponent.position, 0F);

//        TextureRegion textureRegion = new TextureRegion(texture, 250, 140);

        super.add(transformComponent);
        super.add(new PhysicsComponent(body, "HitObjectEntity"));
        super.add(hitObjectComponent);
        super.add(textureComponent);
        super.add(new StateComponent());
        super.add(timerComponent);

        shape.dispose();
    }
}
