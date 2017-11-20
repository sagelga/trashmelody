package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;
import com.trashmelody.components.HitObjectComponent;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.ScanLineComponent;
import com.trashmelody.components.ScoringComponent;

import static com.trashmelody.components.ScoringComponent.Accuracy;
import static com.trashmelody.constants.Constants.*;

public class AccuracySystem extends IteratingSystem {
    @Inject
    public AccuracySystem() {
        super(Family.all(ScoringComponent.class, HitObjectComponent.class).get(), Systems.getIndex(AccuracySystem.class));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScoringComponent scoring = Mapper.scoring.get(entity);
        HitObjectComponent hitObject = Mapper.hitObject.get(entity);
        ScanLineComponent scanLineComponent = getScanLineComponent();

        scoring.setTimingError(getErrorDiff(scanLineComponent, hitObject));

        Accuracy accuracy = getAccuracy(scoring.getTimingError());
        scoring.setAccuracy(accuracy);

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
        } else if (isCool.test(timingError)) {
            return Accuracy.Cool;
        } else if (isBad.test(timingError)) {
            return Accuracy.Bad;
        } else {
            return Accuracy.Miss;
        }
    }
}
