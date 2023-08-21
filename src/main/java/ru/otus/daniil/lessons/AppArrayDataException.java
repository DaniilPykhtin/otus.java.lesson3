package ru.otus.daniil.lessons;

public class AppArrayDataException extends NumberFormatException {
    private final int iIndex;
    private final  int jIndex;

    public AppArrayDataException(int iIndex, int jIndex) {
        this.iIndex = iIndex;
        this.jIndex = jIndex;
    }


    public String getErrInfo() {
        return "Ошибка преобразования в строке: " + iIndex + " столбец: " + jIndex;
    }
}
