package com.trashmelody.utils;

import io.vavr.collection.Stream;
import io.vavr.collection.Traversable;
import io.vavr.control.Option;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Functional {

    public static Function<Float, Predicate<Float>> isMoreThan = (lower) -> value -> value > lower;

    public static Function<Float, Predicate<Float>> isLessThan = (upper) -> value -> value < upper;

    public static BiFunction<Float, Float, Predicate<Float>> isBetween = (lower, upper) -> isMoreThan.apply(lower).and(isLessThan.apply(upper));

    public static <T> Stream<T> flatten(Traversable<Option<T>> traversable) {
        if (traversable.isEmpty()) {
            return Stream.empty();
        } else if (traversable.head().isEmpty()) {
            return flatten(traversable.tail());
        } else {
            return flatten(traversable.tail()).prepend(traversable.head().get());
        }
    }

}
