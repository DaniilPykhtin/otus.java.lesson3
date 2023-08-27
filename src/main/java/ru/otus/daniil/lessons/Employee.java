package ru.otus.daniil.lessons;

public class Employee {

    String name;
    int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void getInfo() {
        System.out.println("Имя: " + name + " Возраст: " + age);

    }
}
