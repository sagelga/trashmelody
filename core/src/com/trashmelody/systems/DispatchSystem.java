package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.components.DispatchComponent.State;
import com.trashmelody.constants.Constants;
import com.trashmelody.entities.HitObjectEntity;
import com.trashmelody.managers.Assets;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;

import java.util.function.Predicate;

import static com.trashmelody.managers.Assets.CIGARETTE_HIT_OBJECT;
import static com.trashmelody.managers.Assets.TEXTURE;

public class DispatchSystem extends IteratingSystem {
    private World world;
    private Assets assets;
    private TimerListener fadeUpListener = new TimerListener() {
        @Override
        public void handle(Entity entity, float lifeTime, float remaining, float delta) {
            TransformComponent transform = Mapper.transform.get(entity);
            transform.scale = (float) Math.sqrt(1 - Math.pow(remaining / lifeTime, 2));
        }
    };

    @Inject
    public DispatchSystem(World world, Assets assets) {
        super(Family.all(DispatchComponent.class).get(), Systems.getIndex(DispatchSystem.class));

        this.world = world;
        this.assets = assets;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DispatchComponent dispatch = Mapper.dispatch.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);
        ScanLineComponent scanLine = getScanLineComponent();

        if (dispatch.state == State.Ready) {
            dispatch.state = State.Playing;
            physics.body.setLinearVelocity(new Vector2(dispatch.velocity, 0F));
        }

        Vector2 velocity = physics.body.getLinearVelocity();
        Vector2 position = physics.body.getPosition();

        if (!Constants.isInBound.test(position.x)) {
            physics.body.setLinearVelocity(-velocity.x, velocity.y);
        }

        if (!Constants.isInBound.test(position.x)) {
            if (Constants.isUnderBound.test(position.x)) {
                physics.body.setLinearVelocity(dispatch.velocity, velocity.y);
            } else {
                physics.body.setLinearVelocity(-dispatch.velocity, velocity.y);
            }
        }

        dispatch.hitObjects
                .filter(hitObject -> Constants.inDispatchArea.test(position.x))
                .takeWhile(ready(scanLine.elapsedTime))
                .map(hitObject -> new HitObjectEntity(
                        world,
                        new HitObjectComponent(hitObject),
                        new TypeComponent(TypeComponent.DISPATCHER),
                        new TextureComponent(assets.get(CIGARETTE_HIT_OBJECT, TEXTURE), new Color(1F, 1F, 1F, 1F)),
                        new TimerComponent(fadeUpListener, 400),
                        position.x
                ))
                .peek(getEngine()::addEntity)
                .peek(hitObjectEntity -> scanLine.activeHitObjects = scanLine.activeHitObjects.enqueue(hitObjectEntity))
                .forEach(hitObjectEntity -> dispatch.hitObjects = dispatch.hitObjects.tail());
    }

    private Predicate<HitObject> ready(float elapsedTime) {
        return hitObject -> hitObject.isAfterStartTime(elapsedTime + Constants.PRE_DISPATCH_TIME);
    }

    private ScanLineComponent getScanLineComponent() {
        return Mapper.scanLine.get(getEngine().getEntitiesFor(Family.all(ScanLineComponent.class).get()).first());
    }
}
