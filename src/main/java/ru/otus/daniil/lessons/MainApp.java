package ru.otus.daniil.lessons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApp {

    private static final Object mon = new Object();
    private static char lastPrintedChar = 'C';

    public static void printChar(char chr, char awaitChar) {
        synchronized (mon) {
            try {
                while (lastPrintedChar != awaitChar) {
                    mon.wait();
                }
                System.out.println(chr);
                lastPrintedChar = chr;
                mon.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        
        executorService.execute(() -> {
            for (int i = 0; i < 5; i++) {
                printChar('A', 'C');
            }
        });

        executorService.execute(() -> {
            for (int i = 0; i < 5; i++) {
                printChar('B', 'A');
            }
        });

        executorService.execute(() -> {
            for (int i = 0; i < 5; i++) {
                printChar('C', 'B');
            }
        });
        executorService.shutdown();

    }
}
