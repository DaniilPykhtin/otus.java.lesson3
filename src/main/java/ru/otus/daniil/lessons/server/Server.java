package ru.otus.daniil.lessons.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private int port;
    private List<ClientHandler> clients;



    public Server(int port) {
        this.port = port;
        clients = new ArrayList<>();
    }

    public void start() {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                System.out.println("Сервер запущен на порту " + port);
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, this);

            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastMessage(null,"System: " + clientHandler.getUsername() + " вошел в чат");
    }

    public synchronized void broadcastMessage(ClientHandler sender, String message) { //+
        for (ClientHandler client : clients) {
            if (client == sender) {  //+
                continue;
            }
            client.sendMessage(message);
        }
    }

    public synchronized void sendSystemMessage (ClientHandler toClient, String message) {
        toClient.sendMessage("System: " + message);
    }

    public ClientHandler findClientByUsername (String username) {
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(username)) {
                return client;
            }
        }
        return null;
    }
    public synchronized void sendPersonalMessage(ClientHandler fromClient, ClientHandler toClient, String message) {
        toClient.sendMessage(fromClient.getUsername() + "(private): " + message);
    }



    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastMessage(null,"System: " + clientHandler.getUsername() + " вышел из чата");
    }
}
