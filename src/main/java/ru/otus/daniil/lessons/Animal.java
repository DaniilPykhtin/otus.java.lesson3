package ru.otus.daniil.lessons;

public class Animal {
    String name;
    String type;
    int runSpeed;
    int swimSpeed;
    int endurance;
    int swimCostMulti;
    int runCostMulti;
    boolean isTired = false;

    public Animal(String name, String type, int runSpeed, int swimSpeed, int endurance, int swimCostMulti, int runCostMulti) {
        this.name = name;
        this.type = type;
        this.runSpeed = runSpeed;
        this.swimSpeed = swimSpeed;
        this.endurance = endurance;
        this.swimCostMulti = swimCostMulti;
        this.runCostMulti = runCostMulti;
    }

    public float run(int distance) {

        if (isTired) {
            System.out.println(name + " не будет бежать, он устал");
            return -1f;
        }

        if (endurance < distance * runCostMulti) {
            System.out.println(name + " устал");
            endurance = 0;
            isTired = true;
            return -1f;
        }
        endurance -= distance * runCostMulti;
        System.out.println(name + " успешно пробежал " + distance + " метров за " + (float) distance / runSpeed + " секунд");
        return (float) distance / runSpeed;
    }

    public float swim(int distance) {

        if (isTired) {
            System.out.println(name + " не будет плыть, он устал");
            return -1f;
        }
        if (endurance < distance * swimCostMulti) {
            System.out.println(name + " устал");
            endurance = 0;
            isTired = true;
            return -1f;
        }
        endurance -= distance * swimCostMulti;
        System.out.println(name + " успешно проплыл " + distance + " метров");
        return (float) distance / runSpeed;
    }

    public void info() {
        System.out.println("Имя: " + name);
        System.out.println("Тип: " + type);
        System.out.println("Скорость бега: " + runSpeed + ", м/с");
        System.out.println("Скорость плавания: " + swimSpeed + ", м/с");
        System.out.println("Выносливость: " + endurance + ", у.е.");
        System.out.println("Состояние: " + ((isTired) ? "Устал" : "Бодрый"));
    }

}