package ru.otus.daniil.lessons.lesson13.transport;

import ru.otus.daniil.lessons.lesson13.Human;
import ru.otus.daniil.lessons.lesson13.Landscape;
import ru.otus.daniil.lessons.lesson13.Rideable;

import java.util.Arrays;

public class Bicycle implements Rideable {
    private static final String TYPE = "Bicycle";
    private final String model;
    private int speed;
    private Human human;
    private final Landscape[] forbiddenLandscapes = {Landscape.SWAMP};


    public Bicycle(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }

    public void getInfo() {
        System.out.println("Модель: " + model);
        System.out.println("Скорость: " + speed + " км/ч");
        System.out.println("Непроходимая местность: " + ((forbiddenLandscapes[0] == null) ? "Нет" : Arrays.toString(forbiddenLandscapes)));
        System.out.println("Водитель: " + ((human == null) ? "Нет" : human.getName()));
    }

    public void getHumanOn(Human human) {
        if (this.human != null) {
            System.out.println(model + "занят");
            return;
        }
        this.human = human;
    }

    @Override
    public void getHumanOff() {
        if (this.human == null) {
            System.out.println("некого высаживать");
            return;
        }
        this.human = null;
    }

    @Override
    public boolean move(int distance, Landscape landscape) {

        if (human == null) {
            System.out.println(model + " не самоходный");
            return false;
        }
        for (int i = 0; i < forbiddenLandscapes.length; i++) {
            if (landscape.equals(forbiddenLandscapes[i])) {
                System.out.println(model + " здесь не проедет");
                return false;
            }
        }

        if (human.getEnduranceConsumption() * distance * landscape.movePenalty > human.getEndurance()) {
            System.out.println(human.getName() + " не хватит сил");
            return false;
        }
        int newEndurance = human.getEndurance() - human.getEnduranceConsumption() * distance * landscape.movePenalty;
        human.setEndurance(newEndurance);
        System.out.println(human.getName() + " проехал " + distance + " км на " + model + " по местности " + landscape + " за " + (float) distance * landscape.movePenalty * 60 / speed + " мин.");
        return true;
    }

    @Override
    public String toString() {
        return TYPE + " " + model;
    }
}
