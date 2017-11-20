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
import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;
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

        if (controller.keyJustPressed(Input.Keys.ESCAPE)) {
            scanLine.music.pause();
            scanLine.state = State.Pause;
            game.setLazyScreen(screens.get(PauseScreen.class));
        }

        processClick(player, scanLine);
    }

    private void processClick(PlayerComponent player, ScanLineComponent scanLine) {
        if (controller.keyJustPressed(player.dangerous)) {
            processHitObject(scanLine, TrashType.Dangerous);
        } else if (controller.keyJustPressed(player.recycle)) {
            processHitObject(scanLine, TrashType.Recycle);
        } else if (controller.keyJustPressed(player.wet)) {
            processHitObject(scanLine, TrashType.Wet);
        } else if (controller.keyJustPressed(player.general)) {
            processHitObject(scanLine, TrashType.General);
        }
    }

    private void processHitObject(ScanLineComponent scanLine, TrashType trashType) {
        scanLine.activeHitObjects
            .filter(entity -> Mapper.hitObject.get(entity).trash.getType() == trashType)
            .headOption()
            .filter(entity -> isClickable(scanLine, Mapper.hitObject.get(entity)))
            .forEach(entity -> {
                HitObjectComponent hitObject = Mapper.hitObject.get(entity);
                entity.add(new ScoringComponent(calculateDelta(scanLine, hitObject)));
                scanLine.activeHitObjects = scanLine.activeHitObjects.remove(entity);
            });
    }

    static float calculateDelta(ScanLineComponent scanLine, HitObjectComponent hitObject) {
        return scanLine.elapsedTime - hitObject.hitObject.getStartTime();
    }

    private PlayerComponent getPlayerComponent() {
        return Mapper.player.get(getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get()).first());
    }

    private static boolean isClickable(ScanLineComponent scanLine, HitObjectComponent hitObject) {
        return Constants.isReachable.test(calculateDelta(scanLine, hitObject));
    }
}
