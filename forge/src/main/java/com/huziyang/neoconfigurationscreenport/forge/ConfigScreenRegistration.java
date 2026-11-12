/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.forge;

import com.huziyang.neoconfigurationscreenport.internal.ModLists;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

/** One-line registration of the configuration screen, mirroring how Forge Config API Port exposes it. */
public final class ConfigScreenRegistration {
    private ConfigScreenRegistration() {
    }

    public static void register(String modId) {
        register(modId, (container, lastScreen) -> new ConfigurationScreen(modId, lastScreen));
    }

    public static void register(String modId, IConfigScreenFactory factory) {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, lastScreen) -> factory
                        .createScreen(ModList.get().getModContainerById(modId).orElseThrow(), lastScreen)));
    }
}
