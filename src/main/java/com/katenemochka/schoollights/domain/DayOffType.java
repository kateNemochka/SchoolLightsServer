package com.katenemochka.schoollights.domain;

public enum DayOffType {
    SINGLE_DAY("Вихідний"),
    PERIOD("Декілька вихідних"),
    HOLIDAY("Канікули");

    private final String displayName;

    DayOffType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
