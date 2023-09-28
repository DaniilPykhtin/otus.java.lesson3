package ru.otus.daniil.lessons.server;

import java.io.IOException;
import java.net.Socket;

public class MainApp {

    public static void main(String[] args) {

        try (Server server = new Server("localhost", 8088)) {

            //while (true) {
            Socket socket = server.getClient();
            server.handleClient(socket);
            //}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
