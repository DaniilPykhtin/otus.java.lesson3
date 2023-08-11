package ru.otus.daniil.lessons.lesson13;

public interface Rideable {

    boolean move(int distance, Landscape landscape);

    void getHumanOn(Human human);

    void getHumanOff();
}
