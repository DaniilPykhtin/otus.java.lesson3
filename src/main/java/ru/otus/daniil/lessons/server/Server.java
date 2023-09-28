package ru.otus.daniil.lessons.server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Server implements AutoCloseable {

    ServerSocket serverSocket;


    public Server(String host, int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public Socket getClient() throws IOException {
        return serverSocket.accept();
    }

    private String read(BufferedReader in) throws IOException {
        String res = "";
        while (true) {
            String str = in.readLine();
            if (str == null || str.trim().isEmpty() || str.equals("null")) {
                break;
            }
            res += str;
        }
        return res;
    }

    private void write(BufferedWriter out, String msg) throws IOException {
        out.write(msg + "\n");
        out.write("\n");
        out.flush();
    }

    public void handleClient(Socket socket) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        write(out, "Это сервер калькулятор");

        while (true) {
            String str = read(in);
            if (str.equals(":q")) {
                break;
            }
            write(out, Calculator.parseAndCalc(str));
        }

    }

    @Override
    public void close() throws Exception {
        serverSocket.close();
        System.out.println("Сервер завершил работу");
    }
}
