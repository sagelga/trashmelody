package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.components.ScoringComponent.Accuracy;
import com.trashmelody.managers.Assets;

import static com.trashmelody.constants.Constants.healthUpdateMap;
import static com.trashmelody.constants.Constants.scoreMap;
import static com.trashmelody.managers.Assets.*;

public class ScoringSystem extends IteratingSystem {
    public static final int HIT_OBJECT_FADING_INTERVAL = 1200;
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

        texture.texture = updateStatistic(scoring, scanLine);
        health.health = updateHealth(health, scoring.getAccuracy());
        scanLine.score.totalScore = updateScore(scanLine, scoring.getAccuracy());

        entity.remove(ScoringComponent.class);
        entity.add(fadeDown());
        entity.add(new CallbackComponent(entity1 -> entity1.add(new DestroyComponent()), HIT_OBJECT_FADING_INTERVAL));
    }

    private float updateHealth(HealthComponent health, Accuracy accuracy) {
        return health.health + healthUpdateMap.get(accuracy).get();
    }

    private int updateScore(ScanLineComponent scanLine, Accuracy accuracy) {
        return scanLine.score.totalScore + scoreMap.get(accuracy).get();
    }

    private Texture updateStatistic(ScoringComponent scoring,
                                    ScanLineComponent scanLine) {
        if (scoring.getAccuracy() == Accuracy.Perfect) {
            scanLine.score.perfect++;
            return assets.get(PERFECT_ACCURACY);
        } else if (scoring.getAccuracy() == Accuracy.Good) {
            scanLine.score.good++;
            return assets.get(GOOD_ACCURACY);
        } else if (scoring.getAccuracy() == Accuracy.Cool) {
            scanLine.score.cool++;
            return assets.get(COOL_ACCURACY);
        } else if (scoring.getAccuracy() == Accuracy.Bad) {
            scanLine.score.bad++;
            return  assets.get(BAD_ACCURACY);
        } else {
            scanLine.score.miss++;
            return assets.get(MISS_ACCURACY);
        }
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
