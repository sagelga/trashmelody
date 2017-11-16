package com.trashmelody.utils;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Functional {
    public static BiFunction<Float, Float, Predicate<Float>> isBetween = (lower, upper) -> value -> value > lower && value < upper;
    public static Function<Float, Predicate<Float>> isMoreThan = (lower) -> value -> value > lower;
    public static Function<Float, Predicate<Float>> isLessThan = (upper) -> value -> value < upper;
}
