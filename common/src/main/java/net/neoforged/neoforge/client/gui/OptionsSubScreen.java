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
    /** Same header/footer split as 1.21's default {@code HeaderAndFooterLayout}. */
    private static final int HEADER_HEIGHT = 33;
    private static final int FOOTER_HEIGHT = 36;
    private static final int BUTTON_SPACING = 8;
    /** 1.20.1 has no {@code Button.BIG_WIDTH}; 1.21's footer uses 200 for the lone done button. */
    private static final int WIDE_BUTTON_WIDTH = 200;
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
        // 1.20.1 keeps renderables across init() calls, so a resize would stack a second list on top.
        this.clearWidgets();
        this.doneButton = null;
        this.undoButton = null;
        this.resetButton = null;
        this.list = new ConfigOptionsList(this.minecraft, this.width, this.height - HEADER_HEIGHT - FOOTER_HEIGHT,
                HEADER_HEIGHT, 24);
        this.addRenderableWidget(this.list);
        this.addOptions();
        this.createFooter();
        LOGGER.info("[ConfigScreenPort] init {} screen={}x{} list={}x{} rows={}", this.title.getString(),
                this.width, this.height, this.list.getWidth(), this.list.getHeight(), this.list.children().size());
    }

    /**
     * Footer mirroring 1.21: a single wide "done" when there is nothing to undo, otherwise
     * undo/reset/done as one centred row of equally sized buttons.
     */
    protected void createFooter() {
        final boolean grouped = this.undoButton != null || this.resetButton != null;
        final int doneWidth = grouped ? Button.SMALL_WIDTH : WIDE_BUTTON_WIDTH;
        if (this.doneButton == null || this.doneButton.getWidth() != doneWidth) {
            this.doneButton = Button.builder(CommonComponents.GUI_DONE, button -> this.onClose())
                    .width(doneWidth).build();
        }
        int total = this.doneButton.getWidth();
        if (this.undoButton != null) {
            total += this.undoButton.getWidth() + BUTTON_SPACING;
        }
        if (this.resetButton != null) {
            total += this.resetButton.getWidth() + BUTTON_SPACING;
        }
        final int y = this.height - FOOTER_HEIGHT + (FOOTER_HEIGHT - Button.DEFAULT_HEIGHT) / 2;
        int x = (this.width - total) / 2;
        if (this.undoButton != null) {
            this.place(this.undoButton, x, y);
            x += this.undoButton.getWidth() + BUTTON_SPACING;
        }
        if (this.resetButton != null) {
            this.place(this.resetButton, x, y);
            x += this.resetButton.getWidth() + BUTTON_SPACING;
        }
        this.place(this.doneButton, x, y);
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
        // 1.20.1 screens draw their own backdrop; without it the parent screen shows through.
        this.renderBackground(graphics);
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
