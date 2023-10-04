package ru.otus.daniil.pykhtin.lessons;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static String readFile(String path) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8))) {
            StringBuilder builder = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
                line = br.readLine();
            }
            return builder.toString();
        }
    }

    public static int substringCount(String string, String substring) {
        int entryCount = 0;
        char[] text = string.toCharArray();
        char[] textToFind = substring.toCharArray();

        int matched = 0;
        for (int i = 0; i < text.length; i++) {

            if (text[i] == textToFind[matched]) {
                matched++;
            } else {
                matched = 0;
            }

            if (matched == textToFind.length) {
                entryCount++;
                matched = 0;
            }
        }
        return entryCount;
    }

    public static void main(String[] args) {
        String text;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Выберите файл");
            File rootDir = new File(".");
            for (File file : rootDir.listFiles()) {
                System.out.println(file.getName());
            }
            text = readFile(scanner.next());
            System.out.println("Введите искомый текст");

            System.out.println(substringCount(text, scanner.next()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

