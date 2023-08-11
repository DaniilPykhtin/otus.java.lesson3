package ru.otus.daniil.lessons.lesson13;

public enum Landscape {
    SWAMP(3),
    DARK_FOREST(2),
    PLAIN(1);

    public final int movePenalty;

    Landscape(int movePenalty) {
        this.movePenalty = movePenalty;
    }
}
