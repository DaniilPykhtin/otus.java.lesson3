package ru.otus.daniil.lessons.lesson13.transport;

import ru.otus.daniil.lessons.lesson13.Human;
import ru.otus.daniil.lessons.lesson13.Landscape;
import ru.otus.daniil.lessons.lesson13.Rideable;

import java.util.Arrays;

public class Horse implements Rideable {
    private static final String TYPE = "Horse";
    private String name;
    private int speed;
    private int endurance;
    private int enduranceConsumption;
    private Human human;
    private final Landscape[] forbiddenLandscapes = {Landscape.SWAMP};


    public Horse(String name, int speed, int endurance, int enduranceConsumption) {
        this.name = name;
        this.speed = speed;
        this.endurance = endurance;
        this.enduranceConsumption = enduranceConsumption;
    }

    public void getInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Скорость: " + speed + " км/ч");
        System.out.println("Выносливость, осталось: " + endurance + " у.е.");
        System.out.println("Потребление выносливости: " + enduranceConsumption + " у.е./км");
        System.out.println("Непроходимая местность: " + ((forbiddenLandscapes[0] == null) ? "Нет" : Arrays.toString(forbiddenLandscapes)));
        System.out.println("Наездник: " + ((human == null) ? "Нет" : human.getName()));
    }

    public void getHumanOn(Human human) {
        this.human = human;
    }

    @Override
    public void getHumanOff() {
        this.human = null;
    }

    @Override
    public boolean move(int distance, Landscape landscape) {
        int weightPenalty;

        if (human == null) {
            System.out.println(name + " не пойдёт один");
            return false;
        }

        if (human.getWeight() >= 100) {
            weightPenalty = 2;
        } else {
            weightPenalty = 1;
        }

        int enduranceCosts = enduranceConsumption * distance * landscape.movePenalty * weightPenalty;

        for (int i = 0; i < forbiddenLandscapes.length; i++) {
            if (landscape.equals(forbiddenLandscapes[i])) {
                System.out.println(name + " здесь не пройдёт");
                return false;
            }
        }

        if (enduranceCosts > endurance) {
            System.out.println(name + " не хватит сил");
            return false;
        }

        endurance -= enduranceCosts;
        System.out.println(name + " проехал " + distance + " км по местности " + landscape
                + " за " + (float) distance * landscape.movePenalty * weightPenalty * 60 / speed + " мин.");
        return true;

    }

    @Override
    public String toString() {
        return TYPE + " " + name;
    }
}
