package com.trashmelody.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.managers.Assets;

import static com.trashmelody.managers.Assets.CIGARETTE_HIT_OBJECT;
import static com.trashmelody.managers.Assets.TEXTURE;

public class HitObjectEntity extends Entity {
    public HitObjectEntity(World world,
                           HitObjectComponent hitObjectComponent,
                           TypeComponent type,
                           TransformComponent transformComponent,
                           Assets assets) {
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

        Texture texture = assets.get(CIGARETTE_HIT_OBJECT, TEXTURE);
        TextureRegion textureRegion = new TextureRegion(texture, 250, 140);

        super.add(transformComponent);
        super.add(new PhysicsComponent(body, "HitObjectEntity"));
        super.add(hitObjectComponent);
        super.add(new TextureComponent(texture));
//        super.add(new TextureRegionComponent(textureRegion));
        super.add(new StateComponent());
    }
}
