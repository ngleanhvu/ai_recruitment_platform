package com.ngleanhvu.common.util;

import java.util.Collection;
import java.util.Map;

public final class ValidationUtil {

    private ValidationUtil() {
        // prevent instantiation
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }


    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }


    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }


    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Object obj) {

        switch (obj) {
            case null -> {
                return true;
            }
            case String str -> {
                return str.isEmpty();
            }
            case Collection<?> collection -> {
                return collection.isEmpty();
            }
            case Map<?, ?> map -> {
                return map.isEmpty();
            }
            default -> {
            }
        }

        if (obj.getClass().isArray()) {
            return ((Object[]) obj).length == 0;
        }

        return false;
    }

    public static <T> T defaultIfNull(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    @SafeVarargs
    public static <T> T firstNonNull(T... values) {

        for (T value : values) {
            if (value != null) {
                return value;
            }
        }
        return null;
    }
}
