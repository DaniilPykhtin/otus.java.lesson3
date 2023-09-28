package ru.otus.daniil.lessons.server;

import java.util.Arrays;

public class Calculator {

    public static String parseAndCalc(String str) {

        String[] instr = str.split("\\|");
        double result;
        System.out.println(Arrays.toString(instr));
        try {
            switch (instr[1].trim()) {
                case ("+") -> result = Double.parseDouble(instr[0]) + Double.parseDouble(instr[2]);
                case ("-") -> result = Double.parseDouble(instr[0]) - Double.parseDouble(instr[2]);
                case ("*") -> result = Double.parseDouble(instr[0]) * Double.parseDouble(instr[2]);
                case ("/") -> result = Double.parseDouble(instr[0]) / Double.parseDouble(instr[2]);
                default -> {
                    return "Оператор введен неправильно";
                }
            }
        } catch (NumberFormatException e) {
            return "Выражение введено неправильно";
        }

        return Double.toString(result);
    }


}
