package ru.otus.daniil.lessons;

public class User {
    private String name;
    private String surname;
    private String patronymic;
    private final int yearOfBirth;
    private String email;

    public User (String surname, String name, String patronymic, int yearOfBirth, String email) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
    }

    public void getInfo () {
        System.out.println("ФИО: " + surname + " " + name + " " + patronymic);
        System.out.println("Год рождения: " + yearOfBirth);
        System.out.println("e-mail: " + email);

    }

    public int getYearOfBirth() {
        return  yearOfBirth;
    }
}
