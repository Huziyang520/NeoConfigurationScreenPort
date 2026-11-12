/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package net.neoforged.neoforge.client.gui;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;

/**
 * Row list for configuration widgets. Rows hold one or two widgets, matching the newer
 * {@code OptionsList} contract instead of the option-instance based 1.20.1 one.
 */
public class ConfigOptionsList extends ContainerObjectSelectionList<ConfigOptionsList.Entry> {
    private static boolean LOGGED;
    public static int listY = -1;
    public static int listH = -1;
    public ConfigOptionsList(Minecraft minecraft, int width, int height, int y, int itemHeight) {
        super(minecraft, width, height, y, itemHeight, 0);
    }

    public void addSmall(@Nullable AbstractWidget first, @Nullable AbstractWidget second) {
        this.addEntry(new Entry(first, second));
    }

    public void addBig(AbstractWidget widget) {
        this.addEntry(new Entry(widget, null));
    }

    /** {@code clearEntries()} is protected in the vanilla list, the screen needs it publicly. */
    public void clearRows() {
        this.clearEntries();
    }

    @Override
    public int getRowWidth() {
        return Math.min(310, this.width - 40);
    }

    @Override
    protected int getScrollbarPosition() {
        return this.width / 2 + 155;
    }

    public static class Entry extends ContainerObjectSelectionList.Entry<Entry> {
        private final List<AbstractWidget> widgets = new ArrayList<>();
        private final List<NarratableEntry> narratables = new ArrayList<>();

        public Entry(@Nullable AbstractWidget first, @Nullable AbstractWidget second) {
            if (first != null) {
                this.widgets.add(first);
            }
            if (second != null) {
                this.widgets.add(second);
            }
            this.narratables.addAll(this.widgets);
        }

        @Override
        public void render(GuiGraphics graphics, int index, int top, int left, int width, int height,
                int mouseX, int mouseY, boolean hovering, float partialTick) {
            if (index == 0 && !LOGGED) {
                LOGGED = true;
                org.slf4j.LoggerFactory.getLogger("ConfigScreenPort").info(
                        "[ConfigScreenPort] row0 render top={} left={} width={} height={} widgets={} listY={} listH={}",
                        top, left, width, height, this.widgets.size(), listY, listH);
            }
            int x = left;
            for (final AbstractWidget widget : this.widgets) {
                widget.setX(x);
                widget.setY(top);
                widget.render(graphics, mouseX, mouseY, partialTick);
                x += widget.getWidth() + 8;
            }
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return this.widgets;
        }

        @Override
        public List<? extends NarratableEntry> narratables() {
            return this.narratables;
        }

    }
}
