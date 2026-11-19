/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.internal;

import java.util.Objects;

/** Loader lookups for mod names, environment checks and mod id resolution. */
public final class ModLists {
    /** Installed by the loader entry point before any screen is built. */
    public interface Provider {
        String displayName(String modId);

        boolean isDevelopmentEnvironment();

        String modIdOf(Object modContainer);
    }

    private static final Provider UNAVAILABLE = new Provider() {
        @Override
        public String displayName(final String modId) {
            return modId;
        }

        @Override
        public boolean isDevelopmentEnvironment() {
            return false;
        }

        @Override
        public String modIdOf(final Object modContainer) {
            throw new IllegalStateException("No loader provider installed");
        }
    };

    private static Provider provider = UNAVAILABLE;

    private ModLists() {
    }

    public static void install(final Provider installed) {
        provider = Objects.requireNonNull(installed, "provider");
    }

    public static String displayName(final String modId) {
        return provider.displayName(modId);
    }

    public static boolean isDevelopmentEnvironment() {
        return provider.isDevelopmentEnvironment();
    }

    public static String modIdOf(final Object modContainer) {
        return provider.modIdOf(modContainer);
    }
}
