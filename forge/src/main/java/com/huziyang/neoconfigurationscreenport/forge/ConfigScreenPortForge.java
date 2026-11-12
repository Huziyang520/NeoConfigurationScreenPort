/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.forge;

import com.huziyang.neoconfigurationscreenport.ConfigScreenPort;
import com.huziyang.neoconfigurationscreenport.ConfigScreenPortConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(ConfigScreenPort.MOD_ID)
public final class ConfigScreenPortForge {

    public ConfigScreenPortForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigScreenPortConfig.CLIENT_SPEC);
        ConfigScreenRegistration.register(ConfigScreenPort.MOD_ID);
    }
}
