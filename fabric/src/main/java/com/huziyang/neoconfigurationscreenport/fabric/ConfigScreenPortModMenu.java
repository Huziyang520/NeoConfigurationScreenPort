/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.fabric;

import com.huziyang.neoconfigurationscreenport.ConfigScreenPort;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import java.util.Map;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

/** Places the screen behind the config button that Mod Menu adds to the mod list. */
public final class ConfigScreenPortModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigurationScreen(ConfigScreenPort.MOD_ID, parent);
    }

    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        return ConfigScreenRegistration.screens();
    }
}
