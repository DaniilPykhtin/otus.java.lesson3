package ru.otus.daniil.lessons.server;

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
    private UserRole role;

    private static int userCount = 0;

    public String getUsername() {
        return username;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        role = UserRole.USER;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        username = "User" + userCount++;
        server.subscribe(this);
        new Thread(() -> {
            try {

                while (true) {
                    // /exit -> disconnect()
                    // /w user message -> user

                    String message = in.readUTF();
// наверное все проверки правильности написания коман надо переложить на клиент
// сейчас админку делаю костылем. Переделаю когда sql авторизация будет                    
                    if (message.startsWith("/")) {
                        if (message.equals("/admin 123qwerty")) {
                            server.sendSystemMessage(this, "System: Look at me! I am admin now");
                            role = UserRole.ADMIN;
                        }

                        if (message.matches("/\\S+\\s+\\S+\\s+\\S+")) {
                            String[] args = message.split("\\s+"); // тут по хорошему бы маппинг сделать интересный

                            switch (args[0]) {
                                case ("/w") -> sendPersonalMessageByUsername(args[1], args[2]);
                                case ("/kick") -> kickUserByUsername(args[1],args[2]);
                                default -> {
                                    continue;
                                }
                            }
                        }
                        if (message.equals("/exit")) {
                            break;
                        }

                    } else {
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
        if (role != UserRole.ADMIN) {
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
