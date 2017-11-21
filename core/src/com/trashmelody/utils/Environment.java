package com.trashmelody.utils;

import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import io.vavr.control.Option;

public class Environment {

    private static Function1<String, Option<Class<?>>> getClassFromName = CheckedFunction1.lift(Class::forName);

    public static Option<Class<?>> getClassFromEnv(String classPath, String envKey) {
        return getClassFromName.apply(classPath + System.getenv(envKey));
    }

    public static Option<Boolean> getBooleanFromEnv(String envKey) {
        String value = System.getenv(envKey);
        if (value == null) {
            return Option.none();
        } else if (System.getenv(envKey).equalsIgnoreCase("true")) {
            return Option.some(true);
        } else if (System.getenv(envKey).equalsIgnoreCase("false")) {
            return Option.some(false);
        } else {
            return Option.none();
        }
    }

}
