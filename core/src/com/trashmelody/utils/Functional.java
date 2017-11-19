package com.trashmelody.utils;

import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import io.vavr.collection.Stream;
import io.vavr.control.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Functional {

    public static Function<Float, Predicate<Float>> isMoreThan = (lower) -> value -> value > lower;

    public static Function<Float, Predicate<Float>> isLessThan = (upper) -> value -> value < upper;

    public static BiFunction<Float, Float, Predicate<Float>> isBetween = (lower, upper) -> isMoreThan.apply(lower).and(isLessThan.apply(upper));

    public static Stream<Path> listFiles(Path path) {
        try {
            return Stream.ofAll(Files.list(path));
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    public static <T> Stream<T> flatten(Stream<Option<T>> stream) {
        if (stream.isEmpty()) {
            return Stream.empty();
        } else if (stream.head().isEmpty()) {
            return flatten(stream.tail());
        } else {
            return flatten(stream.tail()).prepend(stream.head().get());
        }
    }

}
