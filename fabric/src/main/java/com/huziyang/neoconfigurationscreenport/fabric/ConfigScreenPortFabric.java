/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.fabric;

import com.huziyang.neoconfigurationscreenport.ConfigScreenPort;
import com.huziyang.neoconfigurationscreenport.ConfigScreenPortConfig;
import com.huziyang.neoconfigurationscreenport.internal.ModLists;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraftforge.fml.config.ModConfig;

public final class ConfigScreenPortFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModLists.install(new FabricModLists());
        ForgeConfigRegistry.INSTANCE.register(ConfigScreenPort.MOD_ID, ModConfig.Type.CLIENT, ConfigScreenPortConfig.CLIENT_SPEC);
    }
}
