package ru.otus.daniil.lessons.lesson13.transport;

import ru.otus.daniil.lessons.lesson13.Landscape;

public class Rover extends FueledTransport {
    private static final String TYPE = "Rover";
    public Rover(String model, int speed, int fuelConsumption, int fuelAmount) {
        super(model, speed, fuelConsumption, fuelAmount, TYPE, (Landscape) null);
    }
}
