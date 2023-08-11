package ru.otus.daniil.lessons.lesson13;

import ru.otus.daniil.lessons.lesson13.transport.Rover;
import ru.otus.daniil.lessons.lesson13.transport.Bicycle;
import ru.otus.daniil.lessons.lesson13.transport.Car;
import ru.otus.daniil.lessons.lesson13.transport.Horse;

public class MainApp {
    public static void main(String[] args) {

        Human human = new Human("Вася", 5, 100, 2, 110);
        Horse horse = new Horse("Буря", 60, 200, 1);
        Rover rover = new Rover("Range Rover", 120, 2, 240);
        Bicycle bicycle = new Bicycle("mountain", 15);
        Car car = new Car("Race Car", 200, 1, 120);


        human.getOn(horse);
        human.move(10, Landscape.PLAIN);
        human.getOn(bicycle);
        human.move(15, Landscape.DARK_FOREST);
        human.move(15, Landscape.PLAIN);
        human.getOff();
        human.move(4, Landscape.SWAMP);
        human.getOn(rover);
        human.move(20, Landscape.SWAMP);
        human.move(20, Landscape.DARK_FOREST);
        human.getOn(car);
        human.move(100, Landscape.SWAMP);
        human.move(100, Landscape.DARK_FOREST);
        human.move(100, Landscape.PLAIN);
        horse.getInfo();
        bicycle.getInfo();
        rover.getInfo();
        car.getInfo();

        human.moveYourself(1, Landscape.SWAMP);
        human.getInfo();
    }
}
