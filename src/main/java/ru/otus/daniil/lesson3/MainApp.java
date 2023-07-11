package ru.otus.daniil.lesson3;

import java.util.Scanner;

public class MainApp {
    public static void greetengs() {
        System.out.println("Hello\nWorld\nfrom\nJava");
    }

    public static void checkSign(int a, int b, int c) {
        if (a + b + c >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
        //System.out.println((a+b+c>=0)? "Сумма положительная": "Сумма отрицательная");
    }

    public static void selectColor() {

        int data = (int) (Math.random() * 30); //0..30
        System.out.println("data = " + data);

        if (data <= 10) {
            System.out.println("Красный");
        } else if (data > 10 && data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = (int) (Math.random() * 1000);
        int b = (int) (Math.random() * 1000);

        if (a < b) {
            System.out.println(a + "<" + b);
        } else {
            System.out.println(a + ">=" + b);
        }
    }

    public static void addOrSubstractAndPrint(int initValue, int delta, boolean increment) {
        if (increment) {
            System.out.println(initValue + delta);
        } else {
            System.out.println(initValue - delta);
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите номер метода от 1 до 5. Введите 9 чтобы выйти из программы");
            Scanner scanner = new Scanner(System.in);
            int inValue = scanner.nextInt();
            //switch было бы хорошо
            if (inValue == 1) {
                greetengs();
            }
            if (inValue == 2) {
                checkSign(1, 2, -4);
            }
            if (inValue == 3) {
                selectColor();
            }
            if (inValue == 4) {
                compareNumbers();
            }
            if (inValue == 5) {
                addOrSubstractAndPrint(7, 5, false);
            }
            if (inValue == 9) {
                break;
            }

        }

    }

}
