/*
 * Copyright (c) 2026 Huziyang520
 * SPDX-License-Identifier: MPL-2.0
 */

package com.huziyang.neoconfigurationscreenport.api;

public enum RestartType {
    NONE,
    WORLD,
    GAME;

    public RestartType with(RestartType other) {
        return other.ordinal() > this.ordinal() ? other : this;
    }
}
