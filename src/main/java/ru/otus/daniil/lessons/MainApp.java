package ru.otus.daniil.lessons;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MainApp {
    public static void getFileList(String path) {
        for (File file : new File(path).listFiles()) {
            System.out.println(file.getName());
        }
    }

    public static void getFileContent(String path) {
        if (!new File(path).isFile()) {
            return;
        }
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(path))) {
            int n = in.read();
            while (n != -1) {
                System.out.print((char) n);
                n = in.read();
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeContent(String path, String frase) {

        try (FileOutputStream out = new FileOutputStream(path, true)) {
            byte[] buffer = frase.getBytes(StandardCharsets.UTF_8);
            out.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\daniil.pykhtin\\IdeaProjects\\java_project\\src\\main\\java\\ru\\otus\\daniil\\lessons";

        Scanner scanner = new Scanner(System.in);
        getFileList(path);

        System.out.println("Выберите файл");

        path += "\\" + scanner.next();

        getFileContent(path);

        System.out.println("Введите текст для записи в файл или :q для выхода");
        writeContent(path, scanner.next());

    }
}

