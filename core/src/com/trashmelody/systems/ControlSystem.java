package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.handlers.KeyboardController;

public class ControlSystem extends IteratingSystem {
    private KeyboardController controller;

    @Inject
    ControlSystem(KeyboardController controller) {
        super(Family.all(ScanLineComponent.class).get());

        this.controller = controller;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScanLineComponent scanLine = Mapper.scanLine.get(entity);
        PlayerComponent player = getPlayerComponent();

        if (controller.keyMap.get(player.leftKey)) {
            controller.keyMap.put(player.leftKey, false);

            scanLine.activeHitObjects
                    .headOption()
                    .filter(hitObjectEntity -> isClickable(scanLine, Mapper.hitObject.get(hitObjectEntity)))
                    .forEach(hitObjectEntity -> {
                        HitObjectComponent hitObject = Mapper.hitObject.get(hitObjectEntity);
                        hitObjectEntity.remove(HitObjectComponent.class);
                        hitObjectEntity.add(new ScoringComponent(calculateDelta(scanLine, hitObject)));
                        scanLine.activeHitObjects = scanLine.activeHitObjects.tail();
                    });
            System.out.println("----------");
//            hitObjectEntities.forEach(hitObjectEntity -> {
//
//                float score = calculateScore(scanLine, hitObjectEntity);
//            });
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

    public static float calculateDelta(ScanLineComponent scanLine, HitObjectComponent hitObject) {
        return scanLine.elapsedTime - hitObject.hitObject.getStartTime();
    }

    private static boolean isClickable(ScanLineComponent scanLine, HitObjectComponent hitObject) {
        float delta = calculateDelta(scanLine, hitObject);
        return delta < 400 && delta > -400;
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
