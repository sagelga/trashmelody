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
import static com.trashmelody.utils.Functional.*;

public class AccuracySystem extends IteratingSystem {
    public static Predicate<Float> isPerfect = isBetween.apply(-30F, 30F);
    public static Predicate<Float> isGood = isBetween.apply(-50F, 50F);
    public static Predicate<Float> isCool = isBetween.apply(-100F, 100F);
    public static Predicate<Float> isBad = isBetween.apply(-150F, 200F);
    public static Predicate<Float> isMiss = isBetween.apply(-300F, -300F);
    public static Predicate<Float> isReachable = isBetween.apply(-500F, 500F);

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
        System.out.println(scoring.getTimingError());

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
