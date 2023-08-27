package ru.otus.daniil.lessons;


public class MainApp {
    public static void main(String[] args) {

        PhoneBook book = new PhoneBook();

        book.add("Daniil", "8903154");
        book.add("Katya", "890534534");
        book.add("Daniil", "8903423454");

        book.find("Daniil");
        System.out.println(book.containsPhoneNumber("8903423454"));
    }
}