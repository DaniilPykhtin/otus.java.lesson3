package ru.otus.daniil.lessons;


public class MainApp {

    public static void main(String[] args) {

        Cat cat = new Cat("Барсик", 12, 0, 10);
        Dog dog = new Dog("Бобик", 10, 2, 10);
        Horse horse = new Horse("Буря", 20, 1, 20);
        cat.info();
        dog.info();
        horse.info();
        cat.run(5);
        cat.swim(2);
        cat.run(15);
        dog.run(5);
        horse.swim(5);
        cat.info();
        dog.info();
        horse.info();

    }
}