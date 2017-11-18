package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.google.inject.Inject;
import com.trashmelody.TrashMelody;
import com.trashmelody.components.*;
import com.trashmelody.components.ScanLineComponent.State;
import com.trashmelody.constants.Constants;
import com.trashmelody.handlers.KeyboardController;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.screens.PauseScreen;

public class ControlSystem extends IteratingSystem {
    private TrashMelody game;
    private KeyboardController controller;
    private ScreenProvider screens;

    @Inject
    ControlSystem(TrashMelody game, KeyboardController controller, ScreenProvider screens) {
        super(Family.all(ScanLineComponent.class).get(), Systems.getIndex(ControlSystem.class));

        this.game = game;
        this.controller = controller;
        this.screens = screens;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScanLineComponent scanLine = Mapper.scanLine.get(entity);
        PlayerComponent player = getPlayerComponent();

        if (controller.keyJustPressed(Input.Keys.Z)) {
            scanLine.music.pause();
            scanLine.state = State.Pause;
            game.setLazyScreen(screens.get(PauseScreen.class));
        }

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

    public static float calculateDelta(ScanLineComponent scanLine, HitObjectComponent hitObject) {
        return scanLine.elapsedTime - hitObject.hitObject.getStartTime();
    }

    private PlayerComponent getPlayerComponent() {
        return Mapper.player.get(getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get()).first());
    }

    private static boolean isClickable(ScanLineComponent scanLine, HitObjectComponent hitObject) {
        return Constants.isReachable.test(calculateDelta(scanLine, hitObject));
    }
}
