/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package net.neoforged.neoforge.client.gui;

import net.minecraft.client.Options;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Minimal options screen base for the 1.20.1 API, mirroring the parts of the newer
 * {@code OptionsSubScreen} contract that {@link ConfigurationScreen} relies on.
 */
public class OptionsSubScreen extends Screen {
    protected final Screen lastScreen;
    protected final Options options;
    protected final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
    protected ConfigOptionsList list;

    protected OptionsSubScreen(Screen lastScreen, Options options, Component title) {
        super(title);
        this.lastScreen = lastScreen;
        this.options = options;
    }

    @Override
    protected void init() {
        this.list = new ConfigOptionsList(this.minecraft, this.width, this.height, 32, 24);
        this.addRenderableWidget(this.list);
        this.addOptions();
        this.addFooter();
        this.layout.arrangeElements();
        this.layout.visitChildren(child -> child.visitWidgets(this::addRenderableWidget));
        this.added();
    }

    protected void addOptions() {
    }

    protected void addFooter() {
    }

    public void added() {
    }
}
