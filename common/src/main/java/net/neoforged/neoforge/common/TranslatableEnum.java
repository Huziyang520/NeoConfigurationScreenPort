/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.common;

import net.minecraft.network.chat.Component;

/**
 * Implemented by an enum that wants the configuration screen to show something other than its constant name.
 */
public interface TranslatableEnum {
    default Component getTranslatedName() {
        return Component.literal(((Enum<?>) this).name());
    }
}
