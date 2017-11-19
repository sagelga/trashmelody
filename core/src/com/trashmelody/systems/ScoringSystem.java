package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.components.ScoringComponent.Accuracy;
import com.trashmelody.managers.Assets;

import static com.trashmelody.managers.Assets.*;

public class ScoringSystem extends IteratingSystem {
    public static final int HIT_OBJECT_FADING_INTERVAL = 1200;
    public static final int PERFECT_SCORE = 8000;
    public static final int GOOD_SCORE = 7000;
    public static final int COOL_SCORE = 5000;
    public static final int BAD_SCORE = 2000;
    public static final int MISS_SCORE = 0;
    public static final int BAD_HEALTH_REDUCTION = 300;
    public static final int MISS_HEALTH_REDUCTION = 800;
    public static final int PERFECT_HEALTH_INCRESION = 0;
    private Assets assets;

    @Inject
    public ScoringSystem(Assets assets) {
        super(Family.all(ScoringComponent.class).get(), Systems.getIndex(ScoringSystem.class));

        this.assets = assets;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScoringComponent scoring = Mapper.scoring.get(entity);
        TextureComponent texture = Mapper.texture.get(entity);
        HealthComponent health = getHealthComponent();
        ScanLineComponent scanLine = getScanLineComponent();
        Texture accuracyTexture = assets.get(MISS_ACCURACY);

        if (scoring.getAccuracy() == Accuracy.Perfect) {
            accuracyTexture = assets.get(PERFECT_ACCURACY);
            health.health -= PERFECT_HEALTH_INCRESION;
            scanLine.totalScore += PERFECT_SCORE;
        } else if (scoring.getAccuracy() == Accuracy.Good) {
            accuracyTexture = assets.get(GOOD_ACCURACY);
            health.health -= 0;
            scanLine.totalScore += GOOD_SCORE;
        } else if (scoring.getAccuracy() == Accuracy.Cool) {
            accuracyTexture = assets.get(COOL_ACCURACY);
            health.health -= 0;
            scanLine.totalScore += COOL_SCORE;
        } else if (scoring.getAccuracy() == Accuracy.Bad) {
            accuracyTexture = assets.get(BAD_ACCURACY);
            health.health -= BAD_HEALTH_REDUCTION;
            scanLine.totalScore += BAD_SCORE;
        } else if (scoring.getAccuracy() == Accuracy.Miss) {
            accuracyTexture = assets.get(MISS_ACCURACY);
            scanLine.totalScore += MISS_SCORE;
            health.health -= MISS_HEALTH_REDUCTION;
        }
        texture.texture = accuracyTexture;

        entity.remove(ScoringComponent.class);
        entity.add(fadeDown());
        entity.add(new CallbackComponent(entity1 -> entity1.add(new DestroyComponent()), HIT_OBJECT_FADING_INTERVAL));
    }

    private static TimerComponent fadeDown() {
        return new TimerComponent((entity, lifeTime, remaining, delta) -> {
            TransformComponent transform = Mapper.transform.get(entity);
            TextureComponent texture = Mapper.texture.get(entity);

            texture.setAlpha(remaining / lifeTime);
            transform.scale = 2F - remaining / lifeTime;
        }, HIT_OBJECT_FADING_INTERVAL);
    }


//    private static TimerComponent reduceHealth(float healthReduced) {
//        return new TimerComponent((entity, lifeTime, remaining, delta) -> {
//            HealthComponent health = Mapper.health.get(entity);
//            System.out.println("reducing health");
//            health.health -= (delta / lifeTime) * healthReduced;
//        }, 2000);
//    }

    private HealthComponent getHealthComponent() {
        return Mapper.health.get(getEngine().getEntitiesFor(Family.all(HealthComponent.class).get()).first());
    }

    private ScanLineComponent getScanLineComponent() {
        return Mapper.scanLine.get(getEngine().getEntitiesFor(Family.all(ScanLineComponent.class).get()).first());
    }
}
