package ru.otus.daniil.lessons;

public class Cat {
    private String name;
    private int appetite;
    private boolean fullness; //заполненость кота :D

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.fullness = false;
    }

    public boolean isFull() {
        return fullness;
    }
    @Override
    public String toString() {
        return name + " " + ((fullness) ? "сыт" : "голоден");
    }

    public void eat(Plate plate) {
        if (plate.getFood(appetite)) {
            System.out.println(name + " поел и теперь сыт");
            fullness = true;
        } else {
            System.out.println(name + " не хватило еды");
        }
    }
}
