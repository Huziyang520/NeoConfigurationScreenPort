/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport;

import java.util.List;
import net.minecraftforge.common.ForgeConfigSpec;

/** Config of the port itself, doubles as the default content of the screen. */
public final class ConfigScreenPortConfig {
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec.BooleanValue SHOW_TOOLTIPS;
    public static final ForgeConfigSpec.IntValue ROW_HEIGHT;
    public static final ForgeConfigSpec.DoubleValue SCROLL_SPEED;
    public static final ForgeConfigSpec.ConfigValue<String> TITLE_SUFFIX;
    public static final ForgeConfigSpec.EnumValue<Theme> THEME;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HIDDEN_MODS;

    public enum Theme {
        VANILLA,
        DARK,
        HIGH_CONTRAST
    }

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("screen");
        SHOW_TOOLTIPS = builder.comment("Show the configuration comments as tooltips.").define("showTooltips", true);
        ROW_HEIGHT = builder.comment("Height of a single row.").defineInRange("rowHeight", 24, 16, 48);
        SCROLL_SPEED = builder.comment("Multiplier for mouse wheel scrolling.").defineInRange("scrollSpeed", 1.0, 0.25, 4.0);
        TITLE_SUFFIX = builder.comment("Text appended to the screen title.").define("titleSuffix", "");
        THEME = builder.comment("Colour theme of the screen.").defineEnum("theme", Theme.VANILLA);
        HIDDEN_MODS = builder.comment("Mod ids whose configs are not listed.").defineList("hiddenMods", List.of(), entry -> entry instanceof String);
        builder.pop();
        CLIENT_SPEC = builder.build();
    }

    private ConfigScreenPortConfig() {
    }
}
