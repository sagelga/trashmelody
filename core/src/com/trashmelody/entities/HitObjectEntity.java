package com.trashmelody.entities;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.managers.Assets;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import static com.trashmelody.constants.B2Dvars.PPM;
import static com.trashmelody.managers.Assets.CIGARETTE_HIT_OBJECT;
import static com.trashmelody.managers.Assets.TEXTURE;

public class HitObjectEntity extends Entity {
    public HitObject hitObject;

    public HitObjectEntity(World world,
                           HitObject hitObject,
                           HitObjectComponent hitObjectComponent,
                           TypeComponent type,
                           Engine engine,
                           Assets assets) {
        this.hitObject = hitObject;

        Vector2 hitObjectPosition = hitObject.getPosition().toGdxVector();
        float hitObjectX = 200 * 2 / PPM;
        float hitObjectY = (hitObjectPosition.y + 120) / PPM;
        TransformComponent transformComponent = new TransformComponent(hitObjectX, hitObjectY, 0.6F, 0.6F);

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

        Texture texture = assets.get(CIGARETTE_HIT_OBJECT, TEXTURE);
//        TextureRegion textureRegion = new TextureRegion(texture, 250, 140);

        super.add(transformComponent);
        super.add(new PhysicsComponent(body, "HitObjectEntity"));
        super.add(hitObjectComponent);
        super.add(new TextureComponent(texture));
//        super.add(new TextureRegionComponent(textureRegion));
        super.add(new StateComponent());

        shape.dispose();
    }
}
