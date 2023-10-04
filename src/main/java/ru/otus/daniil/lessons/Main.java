package ru.otus.daniil.pykhtin.lessons;


public class Main {

    public static void main(String[] args) {
        Box<Orange> orangeBox = new Box();
        Box<Apple> appleBox = new Box();
        Box<Fruit> fruitBox = new Box();
        Box fruitBox2 = new Box();  // по идее это тоже Box<Fruit>


        orangeBox.addFruit(new Orange[]{new Orange(), new Orange(), new Orange()}); //0.9f

        appleBox.addFruit(new Apple[]{new Apple(), new Apple(), new Apple()}); // 0.6f

        fruitBox.addFruit(new Fruit[]{new Orange(), new Apple(), new Apple(), new Apple()}); //0.9f

        System.out.println(orangeBox.compare(appleBox));
        System.out.println(appleBox.compare(orangeBox));
        System.out.println(orangeBox.compare(fruitBox));
        System.out.println(fruitBox.compare(appleBox));

        orangeBox.handContentOver(fruitBox);
        //appleBox.handContentOver(orangeBox);  // так компилятор не дает нам сделать
        //fruitBox.handContentOver(appleBox); // так компилятор не дает нам сделать
        fruitBox2.handContentOver(appleBox); // а вот так компилятор ДАЁТ сделать. Почему? Ведь это тоже FruitBox :(


    }
}
