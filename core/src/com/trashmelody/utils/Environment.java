package com.trashmelody.utils;

import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import io.vavr.control.Option;

public class Environment {
    private static Function1<String, Option<Class<?>>> getClassFromName = CheckedFunction1.lift(Class::forName);

    public static Option<Class<?>> getClassFromEnv(String classPath, String envKey) {
        String entryScreen = System.getenv(envKey);
        return getClassFromName.apply(classPath + entryScreen);
    }
}
