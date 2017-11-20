package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.entities.FallingTrash;
import com.trashmelody.screens.GameScreen;

import static com.trashmelody.components.ScoringComponent.Accuracy;
import static com.trashmelody.constants.Constants.*;

public class AccuracySystem extends IteratingSystem {

    private World world;

    @Inject
    public AccuracySystem(World world) {
        super(Family.all(ScoringComponent.class, HitObjectComponent.class).get(), Systems.getIndex(AccuracySystem.class));

        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScoringComponent scoring = Mapper.scoring.get(entity);
        HitObjectComponent hitObject = Mapper.hitObject.get(entity);
        TextureComponent texture = Mapper.texture.get(entity);
        ScanLineComponent scanLineComponent = getScanLineComponent();

        scoring.setTimingError(getErrorDiff(scanLineComponent, hitObject));

        Accuracy accuracy = getAccuracy(scoring.getTimingError());
        scoring.setAccuracy(accuracy);

        scoring.getClickedType().forEach(clickedType -> getEngine().addEntity(new FallingTrash(
            world,
            GameScreen.BIN_POSITION_MAPPER.get(clickedType).get(),
            new TextureComponent(texture.texture),
            new TypeComponent(TypeComponent.ITEM)
        )));

        entity.remove(HitObjectComponent.class);
    }

    private float getErrorDiff(ScanLineComponent scanLine, HitObjectComponent hitObject) {
        return Math.abs(scanLine.elapsedTime - hitObject.hitObject.getStartTime());
    }

    private ScanLineComponent getScanLineComponent() {
        return Mapper.scanLine.get(getEngine().getEntitiesFor(Family.all(ScanLineComponent.class).get()).first());
    }

    private Accuracy getAccuracy(float timingError) {
        if (isPerfect.test(timingError)) {
            return Accuracy.Perfect;
        } else if (isGood.test(timingError)) {
            return Accuracy.Good;
        } else if (isNice.test(timingError)) {
            return Accuracy.Nice;
        } else if (isBad.test(timingError)) {
            return Accuracy.Bad;
        } else {
            return Accuracy.Miss;
        }
    }
}
