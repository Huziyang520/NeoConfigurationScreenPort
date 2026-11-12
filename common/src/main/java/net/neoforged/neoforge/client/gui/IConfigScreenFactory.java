/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.client.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.fml.ModContainer;

/**
 * Mirrors NeoForge's {@code IConfigScreenFactory} entry point so mods written against it compile unchanged.
 */
@FunctionalInterface
public interface IConfigScreenFactory {
    Screen createScreen(ModContainer container, Screen modScreen);
}
