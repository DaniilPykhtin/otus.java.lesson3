package ru.otus.daniil.lessons;


import java.util.Arrays;

public class MainApp {

    //вспомогательные методы
    public static int genIntWithinRange(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    //  можно перегрузить и выбирать maxSize и minSize
    public static int[] genArray(int size, boolean isEmpty) {
        int arrSize = size;

        if (size == 0) {
            arrSize = genIntWithinRange(4, 15);
        }

        int[] arr = new int[arrSize];

        if (isEmpty) {
            return arr;
        }

        for (int i = 0; i < arrSize; i++) {
            arr[i] = genIntWithinRange(-100, 100);
        }
        return arr;
    }

    // Домашнее задание
    public static int[] sumArrays(int[]... arrays) { // получается на вход идет матрица

        //дебаг
        System.out.println("Входные массивы");
        for (int i = 0; i < arrays.length; i++) {
            System.out.println(Arrays.toString(arrays[i]));
        }
        //

        int maxSize = arrays[0].length;

        //нашли максимальный размер
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i].length > maxSize) {
                maxSize = arrays[i].length;
            }
        }

        int[] resArray = genArray(maxSize, true);

        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                resArray[j] += arrays[i][j];
            }
        }
        System.out.println("Выходной массив");
        System.out.println(Arrays.toString(resArray));
        return resArray;
    }

    public static boolean hasSpecialBound(int[] arr) {

        System.out.println("Входной массив");
        System.out.println(Arrays.toString(arr));

        int fullSum = 0;
        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            fullSum += arr[i];
        }

        if (fullSum % 2 == 1) {
            return false; //"Нет такой точки"
        }

        for (int i = 0; i < arr.length; i++) {
            leftSum += arr[i];

            if (leftSum == (fullSum / 2))
                return true; //"Точка находится между элементами"
        }
        return false; //"Нет такой точки"
    }

    public static boolean isSorted(int[] arr, String sortType) {

        System.out.println("Входной массив");
        System.out.println(Arrays.toString(arr));
        int prevValue = arr[0];
        if (sortType.equals("desc"))  //идея посоветовала сравнивать так
        {
            for (int i = 1; i < arr.length; i++) {
                if (prevValue < arr[i]) {

                    //"Массив не отсортирован в нужном порядке"
                    return false;
                }
                prevValue = arr[i];
            }
        } else if (sortType.equals("asc")) {
            for (int i = 1; i < arr.length; i++) {
                if (prevValue > arr[i]) {

                    //"Массив не отсортирован в нужном порядке"
                    return false;
                }
                prevValue = arr[i];
            }
        } else {
            System.out.println("Введен неправильный порядок сортировки. Выберите asc или desc");
            return false;
        }

        return true; //"Массив отсортирован в нужном порядке"

    }

    public static void reverseArray(int[] arr) {

        System.out.println("Входной массив");
        System.out.println(Arrays.toString(arr));
        int buffer;

        for (int i = 0; i < arr.length / 2; i++) {
            buffer = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = buffer;

        }
        System.out.println("Выходной массив");
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        //1
        System.out.println("Задача 1");
        sumArrays(genArray(0, false), genArray(0, false), genArray(0, false));
        System.out.println();

        //2
        //genArray(0, false)
        System.out.println("Задача 2");
        if (hasSpecialBound(genArray(0, false))) {  //new int[] {1,1,1,3,1,1,1,1,1,9}
            System.out.println("Точка находится между элементами");
        } else {
            System.out.println("Точка НЕ находится между элементами");
        }
        System.out.println();

        //3
        System.out.println("Задача 3");
        if (isSorted(genArray(0, false), "asc")) {
            System.out.println("Массив отсортирован в нужном порядке");
        } else {
            System.out.println("Массив НЕ отсортирован в нужном порядке");
        }
        System.out.println();
        //4
        System.out.println("Задача 4");
        reverseArray(genArray(7, false));
        System.out.println();
    }

}