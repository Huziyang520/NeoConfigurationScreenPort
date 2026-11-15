/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Element templates for list values, which 1.20.1 has no metadata for.
 * <p>
 * Unregistered lists fall back to the element type of their default value, so a list of strings needs nothing.
 * Register only what that guess cannot cover: another element type, an empty default, or a size limit.
 */
public final class ListElements {

    /** The default a new entry starts from, plus the size limits the list accepts. */
    public record Spec(Supplier<?> factory, int minSize, int maxSize) {
        public boolean allowsSize(int size) {
            return size >= minSize && size <= maxSize;
        }
    }

    private static final Map<String, Spec> SPECS = new HashMap<>();

    private ListElements() {
    }

    public static void register(String modId, String keyPath, Supplier<?> factory) {
        register(modId, keyPath, factory, 0, Integer.MAX_VALUE);
    }

    public static void register(String modId, String keyPath, Supplier<?> factory, int minSize, int maxSize) {
        SPECS.put(id(modId, keyPath), new Spec(factory, minSize, maxSize));
    }

    public static Optional<Spec> lookup(String modId, String keyPath) {
        return Optional.ofNullable(SPECS.get(id(modId, keyPath)));
    }

    private static String id(String modId, String keyPath) {
        return modId + "." + keyPath;
    }
}
