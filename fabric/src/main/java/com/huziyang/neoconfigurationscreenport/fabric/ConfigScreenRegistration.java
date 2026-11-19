/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import java.util.LinkedHashMap;
import java.util.Map;
import net.fabricmc.loader.api.FabricLoader;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

/** One-line registration of the configuration screen, mirroring the Forge side through Mod Menu. */
public final class ConfigScreenRegistration {
    private static final Map<String, ConfigScreenFactory<?>> SCREENS = new LinkedHashMap<>();

    private ConfigScreenRegistration() {
    }

    public static void register(String modId) {
        register(modId, (container, lastScreen) -> new ConfigurationScreen(modId, lastScreen));
    }

    public static void register(String modId, IConfigScreenFactory factory) {
        SCREENS.put(modId, parent -> factory.createScreen(
                FabricLoader.getInstance().getModContainer(modId).orElse(null), parent));
    }

    static Map<String, ConfigScreenFactory<?>> screens() {
        return Map.copyOf(SCREENS);
    }
}
