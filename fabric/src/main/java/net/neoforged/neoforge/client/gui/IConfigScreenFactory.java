/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.client.gui;

import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.gui.screens.Screen;

/**
 * Mirrors NeoForge's {@code IConfigScreenFactory} entry point, with the loader's own container type.
 */
@FunctionalInterface
public interface IConfigScreenFactory {
    Screen createScreen(ModContainer container, Screen modScreen);
}
