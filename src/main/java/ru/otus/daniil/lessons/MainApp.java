package ru.otus.daniil.lessons;

public class MainApp {

    public static void oneThreadPerfExample(double[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }

    public static void fourThreadPerfExample(double[] arr) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < arr.length / 4; i++) {
                arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = arr.length / 4; i < arr.length / 2; i++) {
                arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = arr.length / 2; i < arr.length * 3 / 4; i++) {
                arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = arr.length * 3 / 4; i < arr.length; i++) {
                arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();


    }


    public static void main(String[] args) throws InterruptedException {
        double[] bigArr1 = new double[100_000_000];
        double[] bigArr2 = new double[100_000_000];

        long time = System.currentTimeMillis();
        oneThreadPerfExample(bigArr1);
        System.out.println("Один поток: " + (System.currentTimeMillis() - time) / 1000 + "сек");

        time = System.currentTimeMillis();
        fourThreadPerfExample(bigArr2);
        System.out.println("Четыре потока: " + (System.currentTimeMillis() - time) / 1000 + "сек");

        for (int i = 0; i < bigArr1.length; i++) {
            if (bigArr1[i] != bigArr2[i]) {
                System.out.println("Где-то косяк");
                break;
            }
        }


    }
}
