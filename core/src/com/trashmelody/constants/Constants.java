package com.trashmelody.constants;

import java.util.function.Predicate;

import static com.trashmelody.constants.B2Dvars.PPM;
import static com.trashmelody.utils.Functional.isBetween;
import static com.trashmelody.utils.Functional.isLessThan;
import static com.trashmelody.utils.Functional.isMoreThan;

public class Constants {
    public static final int PRE_DISPATCH_TIME = 1200;
    public static final int HIT_OBJECT_LIFE_TIME = 200;
    public static final float xUpperBound = 512;
    public static final float yUpperBound = 384;
    public static final float LEFT_BORDER_X = 0;
    public static final float RIGHT_BORDER_X = 1920 / PPM;
    public static final float PADDING = 200 / PPM;
    public static final Predicate<Float> isUnderBound = isLessThan.apply(LEFT_BORDER_X);
    public static final Predicate<Float> isOverBound = isMoreThan.apply(RIGHT_BORDER_X);
    public static final Predicate<Float> isInBound = isBetween.apply(LEFT_BORDER_X, RIGHT_BORDER_X);
    public static final Predicate<Float> inDispatchArea = isBetween.apply(LEFT_BORDER_X + PADDING, RIGHT_BORDER_X - PADDING);
    public static Predicate<Float> isPerfect = isBetween.apply(-40F, 40F);
    public static Predicate<Float> isGood = isBetween.apply(-50F, 50F);
    public static Predicate<Float> isCool = isBetween.apply(-100F, 100F);
    public static Predicate<Float> isBad = isBetween.apply(-120F, 120F);
    public static Predicate<Float> isMiss = isBetween.apply(-300F, -300F);
    public static Predicate<Float> isReachable = isBetween.apply(-200F, 200F);
}
