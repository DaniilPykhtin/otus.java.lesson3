package ru.otus.daniil.lessons.lesson13.transport;

import ru.otus.daniil.lessons.lesson13.Human;
import ru.otus.daniil.lessons.lesson13.Landscape;
import ru.otus.daniil.lessons.lesson13.Rideable;

import java.util.Arrays;

public class FueledTransport implements Rideable {

    private final String model;
    private int speed; //60km/h
    private int fuelConsumption; //1l/5 km
    private int fuelAmount; //10l
    private final Landscape[] forbiddenLandscapes;
    private Human human;
    private final String type;

    public FueledTransport(String model, int speed, int fuelConsumption, int fuelAmount, String type, Landscape... forbiddenLandscapes) {
        this.model = model;
        this.speed = speed;
        this.fuelConsumption = fuelConsumption;
        this.fuelAmount = fuelAmount;
        this.type = type;
        this.forbiddenLandscapes = forbiddenLandscapes;
    }

    public void getInfo() {
        System.out.println("Модель: " + model);
        System.out.println("Скорость: " + speed + " км/ч");
        System.out.println("Бак, осталось: " + fuelAmount + "л");
        System.out.println("Потребление: " + fuelConsumption + " л/км");
        System.out.println("Непроходимая местность: " + ((forbiddenLandscapes[0] == null) ? "Нет" : Arrays.toString(forbiddenLandscapes)));
        System.out.println("Водитель: " + ((human == null) ? "Нет" : human.getName()));
    }

    @Override
    public void getHumanOn(Human human) {
        if (this.human != null) {
            System.out.println(model + "занят");
            return;
        }
        this.human = human;
    }

    @Override
    public void getHumanOff() {
        this.human = null;
    }

    @Override
    public boolean move(int distance, Landscape landscape) {

        //distance * landscape.movePenalty = localDistance
        // считаю что проехать N км по болоту это как проехать N*3 км по равнине
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

        if (fuelConsumption * distance * landscape.movePenalty > fuelAmount) {
            System.out.println(model + " не хватит топлива");
            return false;
        }

        fuelAmount -= fuelConsumption * distance * landscape.movePenalty;
        System.out.println(model + " проехал " + distance + " км по местности " + landscape + " за " + (float) distance * landscape.movePenalty * 60 / speed + " мин.");
        return true;
    }

    @Override
    public String toString() {
        return type + " " + model;
    }
}
