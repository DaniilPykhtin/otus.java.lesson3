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
    public static int sumOfPositiveElements(int[][] matrix) {
        int res = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0) {
                    res += matrix[i][j];
                }
            }
        }
        return res;
    }

    public static void printSquareBySize(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || j == 0 || i == size - 1 || j == size - 1) {
                    System.out.print("  *");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
        }
    }

    public static void setDiagonalValuesToZero(int[][] matrix, String method) {

        for (int i = 0; i < matrix.length; i++) {
            if (matrix.length != matrix[i].length) {  //если зубатую матрицу подсунут
                System.out.println("Метод только для квадратных матриц");
                return;
            }
        }
        if (method.equalsIgnoreCase("anti")) { // я enum ом пока не умею пользоваться :(

            for (int i = 0; i < matrix.length; i++) {
                matrix[matrix.length - 1 - i][i] = 0;
            }
        } else if (method.equalsIgnoreCase("both")) {
            for (int i = 0; i < matrix.length; i++) {  //дважды центральный элемент занулит (если есть)
                matrix[i][i] = 0;
                matrix[matrix.length - 1 - i][i] = 0;
            }
        } else {  // по умолчанию - main
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][i] = 0;
            }
        }
        // debug
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static int findMax(int[][] array) {
        int max = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {

                if (max < array[i][j]) {
                    max = array[i][j];
                }
            }
        }
        return max;
    }

    public static int sumSecondRow(int[][] matrix) {
        int res = 0;
        if (matrix.length == 1) {
            return -1;
        }
        for (int i = 0; i < matrix[1].length; i++) {
            res += matrix[1][i];
        }
        return res;
    }

    public static void main(String[] args) {

        //создадим случайную матрицу
        int[][] matrix = new int[genIntWithinRange(1, 10)][genIntWithinRange(1, 10)];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = genArray(matrix[i].length, false);
        }

        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();

        //1
        System.out.println(sumOfPositiveElements(matrix));
        System.out.println();
        //2
        printSquareBySize(genIntWithinRange(1, 10));
        System.out.println();
        //4
        System.out.println(findMax(matrix));
        System.out.println();
        //5
        System.out.println(sumSecondRow(matrix));
        System.out.println();
        //3
        setDiagonalValuesToZero(matrix, "anti");
        System.out.println();
        //new int[][]{{1,1,1,1,1}, {1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}}
    }


}