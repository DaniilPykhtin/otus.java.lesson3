package ru.otus.daniil.lessons;

public class MainApp {
    public static void main(String[] args) {

        Plate plate = new Plate(15);
        Cat[] cat = new Cat[5];

        cat[0] = new Cat("Barsik", 3);
        cat[1] = new Cat("Beliy", 4);
        cat[2] = new Cat("Vas'ka", 5);
        cat[3] = new Cat("Mursik", 6);
        cat[4] = new Cat("Timoxa", 7);

        for (int i = 0; i < cat.length; i++) {
            cat[i].eat(plate);
        }
        for (int i = 0; i < cat.length; i++) {
            System.out.println(cat[i]);
        }

        plate.getInfo();
        plate.addFood(10);
        plate.getInfo();

        for (int i = 0; i < cat.length; i++) {
            if (!cat[i].isFull()) {
                cat[i].eat(plate);
            }
        }
        for (int i = 0; i < cat.length; i++) {
            System.out.println(cat[i]);
        }
        plate.getInfo();
        plate.addFood(100);
        plate.getInfo();
    }
}
