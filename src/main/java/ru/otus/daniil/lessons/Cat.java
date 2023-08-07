package ru.otus.daniil.lessons;

public class Cat extends Animal {

    public Cat(String name, int runSpeed, int swimSpeed, int endurance) {
        super(name, "Кот", runSpeed, swimSpeed, endurance, 0, 1);

        // захардкодил swimCostMulti runCostMulti чтобы не создавать здесь бесполезные переменные и
        // и максимально переиспользовать родительский метод
        // как сделать "правильно" пока не понятно :(
    }

    @Override
    public float swim(int distance) {
        System.out.println("Котики не плавают, у них лапки");
        return -1f;
    }
}