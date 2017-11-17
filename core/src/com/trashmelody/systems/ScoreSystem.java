package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.entities.HitObjectEntity;
import com.trashmelody.handlers.KeyboardController;

public class ScoreSystem extends IteratingSystem {
    private KeyboardController controller;

    @Inject
    ScoreSystem(KeyboardController controller) {
        super(Family.all(DispatchComponent.class).get());

        this.controller = controller;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScanLineComponent scanLine = getScanLineComponent();
        PlayerComponent player = getPlayerComponent();
        ImmutableArray<Entity> hitObjectEntities = getHitObjectEntities();

        if (controller.keyMap.get(player.leftKey)) {
            controller.keyMap.put(player.leftKey, false);

            System.out.println("----------");
            hitObjectEntities.forEach(hitObjectEntity -> {

                float score = calculateScore(scanLine, hitObjectEntity);
            });
        }
    }

    private DispatchComponent getDispatchComponent() {
        return Mapper.dispatch.get(getEngine().getEntitiesFor(Family.all(DispatchComponent.class).get()).first());
    }

    private ScanLineComponent getScanLineComponent() {
        return Mapper.scanLine.get(getEngine().getEntitiesFor(Family.all(ScanLineComponent.class).get()).first());
    }

    private PlayerComponent getPlayerComponent() {
        return Mapper.player.get(getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get()).first());
    }

    private ImmutableArray<Entity> getHitObjectEntities() {
        return getEngine().getEntitiesFor(Family.all(HitObjectComponent.class).get());
    }

    private int calculateScore(ScanLineComponent scanLine, Entity hitObjectEntity) {
        HitObjectComponent hitObject = Mapper.hitObject.get(hitObjectEntity);
        float delta = Math.abs(scanLine.elapsedTime - hitObject.hitObject.getStartTime());

        if (delta < 100) {
            System.out.println(scanLine.elapsedTime);
            System.out.println(hitObject.hitObject.getStartTime());
            hitObject.status = HitObjectComponent.Status.Died;
            return 100;
        }

        return 0;
    }
}
