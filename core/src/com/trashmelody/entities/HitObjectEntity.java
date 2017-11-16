package com.trashmelody.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.trashmelody.components.*;
import com.trashmelody.managers.Assets;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;

import static com.trashmelody.constants.B2Dvars.PPM;
import static com.trashmelody.constants.HitObjectConstant.yUpperBound;
import static com.trashmelody.managers.Assets.CIGARETTE_HIT_OBJECT;
import static com.trashmelody.managers.Assets.TEXTURE;

/* Beatmap Position
*  x (Integer) ranges from 0 to 512 (inclusive) and y (Integer) ranges from 0 to 384 (inclusive).
* */
public class HitObjectEntity extends Entity {
    public HitObjectEntity(World world,
                           HitObjectComponent hitObjectComponent,
                           TypeComponent type,
                           TextureComponent textureComponent,
                           float dispatcherX) {

        Vector2 hitObjectPosition = hitObjectComponent.hitObject.getPosition().toGdxVector();
        float hitObjectX = dispatcherX;
        float hitObjectY = (hitObjectPosition.y / yUpperBound * 300 + 640) / PPM;
//        float hitObjectY = (hitObjectPosition.y % 100 * 3 + 640) / PPM;
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

//        TextureRegion textureRegion = new TextureRegion(texture, 250, 140);

        super.add(transformComponent);
        super.add(new PhysicsComponent(body, "HitObjectEntity"));
        super.add(hitObjectComponent);
        super.add(textureComponent);
        super.add(new StateComponent());

        shape.dispose();
    }
}
