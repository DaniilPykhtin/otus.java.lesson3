package ru.otus.daniil.lessons;

public class Plate {
    private final int capacityMax;
    private int capacityUsed;

    public Plate(int capacityMax) {
        this.capacityMax = capacityMax;
        this.capacityUsed = capacityMax;
    }

    public void getInfo() {
        System.out.println("Заполненость тарелки " + capacityUsed + "/" + capacityMax);
    }

    public void addFood(int amount) {

        if (capacityUsed + amount > capacityMax) {
            capacityUsed = capacityMax;
            System.out.println("Полностью заполнили тарелку");

        } else {
            System.out.println("Добавили " + amount + " еды в тарелку");
            capacityUsed += amount;
        }

    }

    public boolean getFood(int amount) {
        if (amount > capacityUsed) {
            return false;
        }

        capacityUsed -= amount;
        return true;
    }
}
