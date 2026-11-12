/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.internal;

import net.minecraft.SharedConstants;
import net.minecraftforge.fml.ModList;

/** Loader lookups for mod names and environment checks. */
public final class ModLists {
    private ModLists() {
    }

    public static String displayName(String modId) {
        return ModList.get().getModContainerById(modId)
                .map(container -> container.getModInfo().getDisplayName())
                .orElse(modId);
    }

    public static boolean isDevelopmentEnvironment() {
        return SharedConstants.IS_RUNNING_IN_IDE;
    }
}
