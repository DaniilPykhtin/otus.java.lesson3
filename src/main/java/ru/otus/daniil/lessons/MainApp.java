package ru.otus.daniil.lessons;

public class MainApp {

    public static void oneThreadPerfExample(double[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }

    public static void fourThreadPerfExample(double[] arr) throws InterruptedException {

        Thread[] thread = new Thread[4];

        for (int i = 0; i < thread.length; i++) {
            int finalI = i;

            thread[i] = new Thread(() -> {
                for (int j = arr.length * finalI / 4; j < arr.length * (finalI + 1) / 4; j++) {
                    arr[j] = 1.14 * Math.cos(j) * Math.sin(j * 0.2) * Math.cos(j / 1.2);
                }
            });

            thread[i].start();
        }

        for (int i = 0; i < thread.length; i++) {
            thread[i].join();
        }
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
