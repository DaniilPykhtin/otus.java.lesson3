package ru.otus.daniil.lessons.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements AutoCloseable {
    private BufferedReader in;
    private BufferedWriter out;

    public Client(Socket socket) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void write(String str) throws IOException {
        out.write(str + "\n");
        out.write("\n");
        out.flush();

    }

    public String read() throws IOException {
        String res = "";
        while (true) {
            String str = in.readLine();

            if (str == null || str.isEmpty() || str.equals("\n")) {
                break;
            }
            res += str;
        }
        return res;
    }

    public String prepareExpression() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Посчитаем? Yes/No");

        if (scanner.nextLine().equals("No")) {
            return ":q";
        }
        String result = "";
        System.out.println("Введите число");
        result += scanner.nextLine();
        System.out.println("Введите оператор");
        result += "|" + scanner.nextLine();
        System.out.println("Введите второе число");
        result += "|" + scanner.nextLine();
        //System.out.println(result);

        return result;
    }

    @Override
    public void close() throws Exception {
        in.close();
        out.close();
    }
}
