package ru.otus.daniil.lesson3;

import java.util.Scanner;

public class MainApp {
    public static void greetings() {
        System.out.println("Hello\nWorld\nfrom\nJava");
    }

    public static void checkSign(int a, int b, int c) {
        System.out.println("a = " + a + " b = " + b + " c = " + c);
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
        int a = (int) (Math.random() * 2001 - 1000);
        int b = (int) (Math.random() * 2001 - 1000);

        if (a < b) {
            System.out.println(a + "<" + b);
        } else {
            System.out.println(a + ">=" + b);
        }
    }

    public static void addOrSubstractAndPrint(int initValue, int delta, boolean increment) {
        System.out.println("initValues = " + initValue + " delta = " + delta + " increment = " + increment);
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
                greetings();
            }
            if (inValue == 2) {
                int a = (int) (Math.random() * 2001 - 1000);
                int b = (int) (Math.random() * 2001 - 1000);
                int c = (int) (Math.random() * 2001 - 1000);
                // по хорошему бы сделать метод public static int getRandomInt (int leftBound, int rightBound)
                // но мы пока не умеем return
                checkSign(a, b, c);
            }
            if (inValue == 3) {
                selectColor();
            }
            if (inValue == 4) {
                compareNumbers();
            }
            if (inValue == 5) {
                int initValue = (int) (Math.random() * 2001 - 1000);
                int delta = (int) (Math.random() * 2001 - 1000);
                boolean increment = true;
                if ((int) (Math.random() * 2001 - 1000) < 0) {
                    increment = false;
                }
                addOrSubstractAndPrint(initValue, delta, increment);
            }
            if (inValue == 9) {

                break;
            }


        }

    }

}
