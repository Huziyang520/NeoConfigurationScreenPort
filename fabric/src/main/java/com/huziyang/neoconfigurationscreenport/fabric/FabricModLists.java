/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.fabric;

import com.huziyang.neoconfigurationscreenport.internal.ModLists;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

/** Fabric lookups behind {@link ModLists}. */
final class FabricModLists implements ModLists.Provider {

    @Override
    public String displayName(final String modId) {
        return FabricLoader.getInstance().getModContainer(modId)
                .map(container -> container.getMetadata().getName())
                .orElse(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public String modIdOf(final Object modContainer) {
        return ((ModContainer) modContainer).getMetadata().getId();
    }
}
