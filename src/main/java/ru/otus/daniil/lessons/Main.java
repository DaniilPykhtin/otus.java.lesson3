package ru.otus.daniil.lessons;

import java.util.Arrays;

public class Main {

    public static int[] copyArrayFromIndex(int[] arr, int index) {

        if (index == arr.length - 1) {
            return new int[]{};
        }

        int[] newArray = new int[arr.length - index - 1];

        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = arr[index + i + 1];
        }
        return newArray;
    }

    public static int[] copyArrayAfterLastNumber(int[] arr, int number) {
        int index = -1;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == number) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new RuntimeException();
        }

        return copyArrayFromIndex(arr, index);

    }

    public static boolean arrConsistOnlyOfOneAndTwo(int[] arr) {

        int countOne = 0;
        int countTwo = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                countOne++;
                continue;
            }
            if (arr[i] == 2) {
                countTwo++;
                continue;
            }
            return false;
        }

        if (countOne == 0 || countTwo == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(copyArrayAfterLastNumber(new int[]{1, 2}, 1)));

        System.out.println(arrConsistOnlyOfOneAndTwo(new int[]{1, 1, 1, 1, 1, 1, 3}));
        System.out.println(arrConsistOnlyOfOneAndTwo(new int[]{1, 1, 1, 1, 1, 1, 2}));
        System.out.println(arrConsistOnlyOfOneAndTwo(new int[]{1, 3, 1, 1, 1, 1, 2}));


    }
}
