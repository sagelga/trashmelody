package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.utils.Functional;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;

import java.util.function.Predicate;

import static com.trashmelody.components.ScoringComponent.*;

public class AccuracySystem extends IteratingSystem {
    private static Predicate<Float> isPerfect = Functional.isBetween.apply(-20F, 20F);
    private static Predicate<Float> isGood = Functional.isBetween.apply(-50F, 50F);
    private static Predicate<Float> isCool = Functional.isBetween.apply(-100F, 100F);
    private static Predicate<Float> isBad = Functional.isBetween.apply(-200F, 200F);
    private static Predicate<Float> isMiss = Functional.isBetween.apply(-300F, 300F);
    private static Predicate<Float> isReacheable = Functional.isBetween.apply(-500F, 500F);

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
        if (isPerfect.test(timingError)) {
            return Accuracy.Perfect;
        } else if (isGood.test(timingError)) {
            return Accuracy.Good;
        } else if (isCool.test(timingError)) {
            return Accuracy.Cool;
        } else if (isBad.test(timingError)) {
            return Accuracy.Bad;
        } else if (isMiss.test(timingError)) {
            return Accuracy.Miss;
        } else {
            throw new RuntimeException("Unhandled accuracy");
        }
    }
}
