/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.internal;

import java.lang.reflect.Method;
import net.minecraftforge.common.ForgeConfigSpec.ValueSpec;
import org.jetbrains.annotations.Nullable;

/**
 * Reads the range of a value spec through reflection: one loader keeps the range type package
 * private, so the shared screen cannot name it in a signature.
 */
public final class ValueRanges {
    private static final Method SPEC_GET_RANGE = findOnSpec("getRange");
    private static final Method RANGE_GET_MIN = findOnRange("getMin");
    private static final Method RANGE_GET_MAX = findOnRange("getMax");
    private static final Method RANGE_TEST = findOnRange("test", Object.class);

    private ValueRanges() {
    }

    private static Method findOnSpec(final String name) {
        try {
            final Method method = ValueSpec.class.getMethod(name);
            method.setAccessible(true);
            return method;
        } catch (final ReflectiveOperationException | RuntimeException e) {
            return null;
        }
    }

    private static Method findOnRange(final String name, final Class<?>... parameters) {
        try {
            final Class<?> range = Class.forName("net.minecraftforge.common.ForgeConfigSpec$Range");
            final Method method = range.getMethod(name, parameters);
            method.setAccessible(true);
            return method;
        } catch (final ReflectiveOperationException | RuntimeException e) {
            return null;
        }
    }

    @Nullable
    public static Object of(@Nullable final ValueSpec spec) {
        if (spec == null || SPEC_GET_RANGE == null) {
            return null;
        }
        return invoke(SPEC_GET_RANGE, spec);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> T min(@Nullable final Object range) {
        return range == null || RANGE_GET_MIN == null ? null : (T) invoke(RANGE_GET_MIN, range);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> T max(@Nullable final Object range) {
        return range == null || RANGE_GET_MAX == null ? null : (T) invoke(RANGE_GET_MAX, range);
    }

    public static boolean test(@Nullable final Object range, @Nullable final Object value) {
        return range == null || RANGE_TEST == null || Boolean.TRUE.equals(invoke(RANGE_TEST, range, value));
    }

    public static String text(@Nullable final Object range) {
        return range == null ? "" : String.valueOf(range);
    }

    @Nullable
    private static Object invoke(final Method method, final Object target, final Object... arguments) {
        try {
            return method.invoke(target, arguments);
        } catch (final ReflectiveOperationException | RuntimeException e) {
            return null;
        }
    }
}
