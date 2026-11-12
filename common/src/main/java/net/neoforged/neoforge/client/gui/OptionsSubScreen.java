/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package net.neoforged.neoforge.client.gui;

import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

/**
 * Minimal options screen base for the 1.20.1 API, mirroring the parts of the newer
 * {@code OptionsSubScreen} contract that {@link ConfigurationScreen} relies on.
 */
public class OptionsSubScreen extends Screen {
    protected final Screen lastScreen;
    protected final Options options;
    protected final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
    protected final Button doneButton = Button.builder(CommonComponents.GUI_DONE, button -> onClose())
            .width(Button.SMALL_WIDTH).build();
    protected ConfigOptionsList list;

    protected OptionsSubScreen(Screen lastScreen, Options options, Component title) {
        super(title);
        this.lastScreen = lastScreen;
        this.options = options;
    }

    @Override
    protected void init() {
        this.list = new ConfigOptionsList(this.minecraft, this.width, this.height - 32 - 40, 32, 24);
        this.addRenderableWidget(this.list);
        this.layout.addToHeader(new StringWidget(this.title, this.font));
        this.addOptions();
        this.addFooter();
        this.layout.arrangeElements();
        this.layout.visitChildren(child -> child.visitWidgets(this::addRenderableWidget));
    }

    protected void addOptions() {
    }

    protected void addFooter() {
        this.layout.addToFooter(this.doneButton);
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(this.lastScreen);
        }
    }
}
