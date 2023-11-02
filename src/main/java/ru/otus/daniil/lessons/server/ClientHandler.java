package ru.otus.daniil.lessons.server;
package ru.otus.daniil.lessons.lesson22.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private final Socket socket;

    private final Server server;
    private final DataInputStream in;
    private final DataOutputStream out;

    private String username;
    private String role;
    private boolean isAuth = false;

    private static int userCount = 0;

    public String getUsername() {
        return username;
    }

    public ClientHandler(Socket socket, Server server) throws IOException {
        userCount++;
        this.socket = socket;
        this.server = server;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

    
        new Thread(() -> {
            try {
                while (true) {


                    String message = in.readUTF();

                    if (message.equals("/exit")) {
                        break;
                    }
                    if (message.startsWith("/")) {

                        handleCommand(message);
                        continue;
                    }
                    if (isAuth) {
                        server.broadcastMessage(this, username + ": " + message); //+
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                disconnect();
            }
        }).start();
    }

    private void handleCommand(String message){
        if (message.matches("/\\S+\\s+\\S+\\s+\\S+")) {
            String[] args = message.split("\\s+"); // тут по хорошему бы маппинг сделать интересный
            switch (args[0]) {
                case ("/w") -> sendPersonalMessageByUsername(args[1], args[2]);
                case ("/kick") -> kickUserByUsername(args[1],args[2]);
                case ("/auth") -> authUser(args[1],args[2]);
                case ("/reg") -> regUser(args[1],args[2]);
                default -> {
                }
            }
        }
    }
    private void authUser (String login, String password)  {
        String[] str = server.provider.authUser(login, password);
        server.sendSystemMessage(this, "System: Вход произведён");
        username = str[0];
        role = str[1];
        isAuth = true;
        server.subscribe(this);
    }
    private void regUser (String login, String password)  {
        if (server.provider.regUser(login,password,"User")) {
            server.sendSystemMessage(this, "System: Регистрация успешна");
            username = login;
            role = "User";
            isAuth = true;
            server.subscribe(this);
        }
        else {
            server.sendSystemMessage(this, "System: Что-то пошло не так");
        }

    }

    public void disconnect() {
        server.unsubscribe(this);
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
        }
    }
    public void sendPersonalMessageByUsername(String username, String message) {
        ClientHandler toClient = server.findClientByUsername(username);
        if (toClient == null) {
            server.sendSystemMessage(this, "System: Пользователя c ником " + username + "не существует");
            return;
        }
        server.sendPersonalMessage(this, toClient, message);
    }
    public void kickUserByUsername(String username, String message) {
        if (role != "Admin") {
            server.sendSystemMessage(this, "System: Не хватает прав");
            return;
        }
        ClientHandler client = server.findClientByUsername(username);
        if (client == null) {
            server.sendSystemMessage(this, "System: Пользователя c ником " + username + "не существует");
            return;
        }
        server.sendSystemMessage(client, "System: Вас кикнули, причина: " + message);
        client.disconnect();
    }
}

