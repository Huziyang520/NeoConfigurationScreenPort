/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.forge;

import com.huziyang.neoconfigurationscreenport.internal.ModLists;
import net.minecraft.SharedConstants;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;

/** Forge lookups behind {@link ModLists}. */
final class ForgeModLists implements ModLists.Provider {

    @Override
    public String displayName(final String modId) {
        return ModList.get().getModContainerById(modId)
                .map(container -> container.getModInfo().getDisplayName())
                .orElse(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return SharedConstants.IS_RUNNING_IN_IDE;
    }

    @Override
    public String modIdOf(final Object modContainer) {
        return ((ModContainer) modContainer).getModId();
    }
}
