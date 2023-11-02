package ru.otus.daniil.lessons.lesson22.server;

public interface AuthenticationProvider {
    String[] authUser(String login, String password);

    boolean regUser(String login, String password,  String userRole);
}
