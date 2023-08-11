package ru.otus.daniil.lessons.lesson13.transport;

import ru.otus.daniil.lessons.lesson13.Landscape;

public class Car extends FueledTransport {
    private static final String TYPE = "Car";

    public Car(String model, int speed, int fuelConsumption, int fuelAmount) {
        super(model, speed, fuelConsumption, fuelAmount, TYPE, Landscape.SWAMP, Landscape.DARK_FOREST);
    }
}
