package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;

import static com.trashmelody.components.ScoringComponent.*;

public class AccuracySystem extends IteratingSystem {
    @Inject
    public AccuracySystem() {
        super(Family.all(ScoringComponent.class, HitObjectComponent.class).get(), 10);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScoringComponent scoring = Mapper.scoring.get(entity);
        HitObjectComponent hitObject = Mapper.hitObject.get(entity);
        ScanLineComponent scanLineComponent = getScanLineComponent();

        scoring.setTimingError(getErrorDiff(scanLineComponent, hitObject));

        Accuracy accuracy = getAccuracy(scoring.getTimingError());
        scoring.setAccuracy(accuracy);

    }

    private float getErrorDiff(ScanLineComponent scanLine, HitObjectComponent hitObject) {
        return Math.abs(scanLine.elapsedTime - hitObject.hitObject.getStartTime());
    }

    private ScanLineComponent getScanLineComponent() {
        return Mapper.scanLine.get(getEngine().getEntitiesFor(Family.all(ScanLineComponent.class).get()).first());
    }

    private Accuracy getAccuracy(float timingError) {
        if (timingError < 10) {
            return Accuracy.Perfect;
        } else if (timingError < 50) {
            return Accuracy.Good;
        } else if (timingError < 100) {
            return Accuracy.Cool;
        } else if (timingError < 200) {
            return Accuracy.Bad;
        } else {
            return Accuracy.Miss;
        }
    }
}
