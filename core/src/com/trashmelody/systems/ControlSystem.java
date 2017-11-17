package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.handlers.KeyboardController;

public class ControlSystem extends IteratingSystem {
    private KeyboardController controller;

    @Inject
    ControlSystem(KeyboardController controller) {
        super(Family.all(ScanLineComponent.class).get(), Systems.getIndex(ControlSystem.class));

        this.controller = controller;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScanLineComponent scanLine = Mapper.scanLine.get(entity);
        PlayerComponent player = getPlayerComponent();

        if (controller.keyMap.get(player.dangerous)) {
            controller.keyMap.put(player.dangerous, false);

            scanLine.activeHitObjects
                    .headOption()
                    .filter(hitObjectEntity -> isClickable(scanLine, Mapper.hitObject.get(hitObjectEntity)))
                    .forEach(hitObjectEntity -> {
                        HitObjectComponent hitObject = Mapper.hitObject.get(hitObjectEntity);
                        hitObjectEntity.add(new ScoringComponent(calculateDelta(scanLine, hitObject)));
                        scanLine.activeHitObjects = scanLine.activeHitObjects.tail();
                    });
        }
    }

    private PlayerComponent getPlayerComponent() {
        return Mapper.player.get(getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get()).first());
    }


    public static float calculateDelta(ScanLineComponent scanLine, HitObjectComponent hitObject) {
        return scanLine.elapsedTime - hitObject.hitObject.getStartTime();
    }

    private static boolean isClickable(ScanLineComponent scanLine, HitObjectComponent hitObject) {
        return AccuracySystem.isReachable.test(calculateDelta(scanLine, hitObject));
    }
}
