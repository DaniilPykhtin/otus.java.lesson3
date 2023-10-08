package ru.otus.daniil.pykhtin.lesson26;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {

    ArrayList<T> content;
    public Box() {
        content = new ArrayList<>();
    }

    public void addFruit  (T[] fruit) {
        content.addAll(Arrays.asList(fruit));
    }

    public float getWeight () {
        float result = 0.0f;
        //сделал через суммирование, т.к. полагаю что вес фрукта когда-нибудь перестанет быть константой
        for (T entry : content) {
            result += entry.getWeight();
        }
        return result;
    }

    public boolean compare (Box<?> box) {
        if (box == null) {
            return false;
        }
        if (this == box) {
            return true;
        }
        return (Math.abs(this.getWeight() - box.getWeight()) < 0.0001f);
    }

    public void handContentOver(Box<? super T> box) {
        box.content.addAll(this.content);
        this.content.clear();
    }


}
