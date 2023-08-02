package ru.otus.daniil.lessons;


public class Box {
    private String color;
    private final float height;
    private final float length;
    private final float width;
    private boolean isFilled = false;
    private String filledBy;
    private boolean isOpen = false;

    public Box(String color, float length, float width, float height) {
        this.color = color;
        this.length = length;
        this.width = width;
        this.height = height;
    }


    public void getInfo() {
        System.out.println();
        System.out.println("Цвет: " + color);
        System.out.println("Размер: " + length + "x" + width + "x" + height);
        System.out.println("Открыта: " + ((isOpen) ? "Да" : "Нет"));
        System.out.println("Наполнена: " + ((isFilled) ? "Да" : "Нет"));
        if (isFilled) {
            System.out.println("Внутри: " + filledBy);
        }
        System.out.println();
    }

    public void setColor(String color) {
        this.color = color;
        System.out.println("Покрасили в " + color);
    }

    public void open() {
        if (isOpen) {
            System.out.println("Уже открыта");
        } else {
            isOpen = true;
            System.out.println("Открыли коробку");
        }
    }

    public void close() {
        if (!isOpen) {
            System.out.println("Уже закрыта");
        } else {
            isOpen = false;
            System.out.println("Закрыли коробку");
        }
    }

    public void putItem(String item) {
        if (!isOpen) {
            System.out.println("Не могу положить " + item + " - коробка закрыта");
            return;
        }

        if (isFilled) {
            System.out.println("Не могу положить " + item + " - коробка заполнена");
            return;
        }

        filledBy = item;
        isFilled = true;
        System.out.println("В коробку положили: " + item);

    }

    public void dropItem(String item) {

        if (!isOpen) {
            System.out.println("Не могу выкинуть " + item + " - коробка закрыта");
            return;
        }

        if (!isFilled) {
            System.out.println("Не могу выкинуть " + item + " - в коробке пусто");
            return;
        }

        if (!item.equalsIgnoreCase(filledBy)) {
            System.out.println("Не могу выкинуть " + item + " - его нет в коробке");
            return;
        }

        filledBy = null;
        isFilled = false;
        System.out.println("Из коробки выбросили: " + item);
    }
}


