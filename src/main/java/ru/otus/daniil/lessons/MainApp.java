package ru.otus.daniil.lessons;

public class MainApp {

    //вспомогательные методы
    public static int castAndSum(String[][] stringArr) {

        int res = 0;

        if (stringArr.length != 4) {
            throw new AppArraySizeException();
        }
        for (int i = 0; i < stringArr.length; i++) {
            if (stringArr[i].length != 4) {
                throw new AppArraySizeException();
            }
        }

        for (int i = 0; i < stringArr.length; i++) {
            for (int j = 0; j < stringArr[i].length; j++) {
                try {
                    res += Integer.parseInt(stringArr[i][j]);
                } catch (NumberFormatException err) {
                    throw new AppArrayDataException(i + 1, j + 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        String[][] myStringArr = new String[][]{{"3", "13", "-4", "2"},
                {"3", "13", "-4", "2"},
                {"3", "13", "-4", "2"},
                {"3", "13", "-4", "2"}};
        try {
            System.out.println(castAndSum(myStringArr));
        } catch (AppArrayDataException errData) {
            System.out.println(errData.getErrInfo());
        } catch (AppArraySizeException errSize) {
            System.out.println("Массив неверного размера");
        }

    }


}