/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package net.neoforged.neoforge.client.gui;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

/**
 * 1.20.1 stand-in for the newer {@code OptionsSubScreen} contract: a scrollable row list above
 * a footer of buttons. Kept deliberately close to vanilla positioning so third party UI mods
 * that rewrite selection lists keep working.
 */
public class OptionsSubScreen extends Screen {
    private static final org.slf4j.Logger LOGGER = LogUtils.getLogger();
    private boolean loggedOnce;
    protected final Screen lastScreen;
    protected final Options options;
    protected ConfigOptionsList list;
    protected Button doneButton;
    protected Button undoButton;
    protected Button resetButton;

    protected OptionsSubScreen(Screen lastScreen, Options options, Component title) {
        super(title);
        this.lastScreen = lastScreen;
        this.options = options;
    }

    @Override
    protected void init() {
        this.list = new ConfigOptionsList(this.minecraft, this.width, this.height - 32 - 36, 32, 24);
        ConfigOptionsList.listY = 32;
        ConfigOptionsList.listH = this.height - 32 - 36;
        this.list.setRenderBackground(false);
        this.addRenderableWidget(this.list);
        this.addOptions();
        this.createFooter();
        LOGGER.info("[ConfigScreenPort] init {} screen={}x{} list={}x{} rows={}", this.title.getString(),
                this.width, this.height, this.list.getWidth(), this.list.getHeight(), this.list.children().size());
    }

    protected void createFooter() {
        if (this.doneButton == null) {
            this.doneButton = Button.builder(CommonComponents.GUI_DONE, button -> this.onClose())
                    .width(Button.SMALL_WIDTH).build();
        }
        final int y = this.height - 28;
        this.place(this.doneButton, this.width / 2 + 4, y);
        if (this.undoButton != null) {
            this.place(this.undoButton, this.width / 2 - 4 - this.undoButton.getWidth() - 4 - this.undoButton.getWidth(), y);
        }
        if (this.resetButton != null) {
            this.place(this.resetButton, this.width / 2 - 4 - this.resetButton.getWidth(), y);
        }
    }

    private void place(final AbstractWidget widget, final int x, final int y) {
        widget.setX(x);
        widget.setY(y);
        if (!this.renderables.contains(widget)) {
            this.addRenderableWidget(widget);
        }
    }

    protected void addOptions() {
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (!this.loggedOnce) {
            this.loggedOnce = true;
            LOGGER.info("[ConfigScreenPort] first render {}, rows={} rowWidth={}", this.title.getString(),
                    this.list == null ? -1 : this.list.children().size(),
                    this.list == null ? -1 : this.list.getRowWidth());
        }
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 12, 0xFFFFFF);
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(this.lastScreen);
        }
    }
}
