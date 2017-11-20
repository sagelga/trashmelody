package com.trashmelody.constants;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

import java.util.function.Predicate;

import static com.trashmelody.components.ScoringComponent.*;
import static com.trashmelody.constants.B2Dvars.PPM;
import static com.trashmelody.utils.Functional.isBetween;
import static com.trashmelody.utils.Functional.isLessThan;
import static com.trashmelody.utils.Functional.isMoreThan;

public class Constants {

//    public static final int MAX_HEALTH = 10000;
    public static final int MAX_HEALTH = 1000000000;
    public static final int PRE_DISPATCH_TIME = 1200;
    public static final int HIT_OBJECT_LIFE_TIME = 200;
    public static final int END_OF_GAME_DELAY = 4000;
    public static final float xUpperBound = 512;
    public static final float yUpperBound = 384;
    public static final float START_POSITION = 10F / PPM;
    public static final float LEFT_BORDER_X = 0 / PPM;
    public static final float RIGHT_BORDER_X = 1920 / PPM;
    public static final float PADDING = 150 / PPM;

    public static final Predicate<Float> isUnderBound = isLessThan.apply(LEFT_BORDER_X);
    public static final Predicate<Float> isOverBound = isMoreThan.apply(RIGHT_BORDER_X);
    public static final Predicate<Float> isInBound = isBetween.apply(LEFT_BORDER_X, RIGHT_BORDER_X);
    public static final Predicate<Float> inDispatchArea = isBetween.apply(LEFT_BORDER_X + PADDING, RIGHT_BORDER_X - PADDING);

    public static final Predicate<Float> isPerfect = isBetween.apply(-40F, 40F);
    public static final Predicate<Float> isGood = isBetween.apply(-50F, 50F);
    public static final Predicate<Float> isNice = isBetween.apply(-100F, 100F);
    public static final Predicate<Float> isBad = isBetween.apply(-120F, 120F);
    public static final Predicate<Float> isMiss = isBetween.apply(-300F, 300F);
    public static final Predicate<Float> isReachable = isBetween.apply(-200F, 200F);

    public static final Map<Accuracy, Integer> scoreMap = HashMap.of(
        Accuracy.Perfect, 8000,
        Accuracy.Good, 7000,
        Accuracy.Nice, 5000,
        Accuracy.Bad, 2000,
        Accuracy.Miss, 0
    );

    public static final Map<Accuracy, Float> healthUpdateMap = HashMap.of(
        Accuracy.Perfect, 0F,
        Accuracy.Good, 0F,
        Accuracy.Nice, 0F,
        Accuracy.Bad, -300F,
        Accuracy.Miss, -800F
    );

}
