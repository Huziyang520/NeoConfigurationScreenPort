/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.internal;

import java.util.Collections;
import java.util.Set;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.ModConfig;

/** Reads Forge's tracked configs without touching Forge internals beyond its public accessor. */
public final class ModConfigs {
    private ModConfigs() {
    }

    public static Set<ModConfig> getConfigSet(ModConfig.Type type) {
        return ConfigTracker.INSTANCE.configSets().getOrDefault(type, Collections.emptySet());
    }
}
