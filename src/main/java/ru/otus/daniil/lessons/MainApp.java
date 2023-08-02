package ru.otus.daniil.lessons;

import java.time.Year;

public class MainApp {

    //вспомогательные методы
    public static void main(String[] args) {
        Box box = new Box("Красный", 2.0f, 1f, 1f);
        box.getInfo();
        box.putItem("Веник");
        box.open();
        box.putItem("Веник");
        box.putItem("Мыло");
        box.close();
        box.putItem("Мыло");
        box.dropItem("Мыло");
        box.open();
        box.dropItem("Мыло");
        box.dropItem("Веник");
        box.putItem("Мыло");
        box.setColor("Синий");
        box.getInfo();
        System.out.println("\n\n");
        User[] users = new User[5];
        users[0] = new User("Иванов", "Иван", "Иванович", 1997, "ivanovii@mail.com");
        users[1] = new User("Сергеев", "Сергей", "Сергеевич", 1987, "sergeevss@mail.com");
        users[2] = new User("Петров", "Петр", "Петрович", 1977, "petrovpp@mail.com");
        users[3] = new User("Васильев", "Василий", "Васильевич", 1967, "vasilievvv@mail.com");
        users[4] = new User("Никитин", "Никита", "Никитьевич", 1987, "nikitinnn@mail.com");
        int year = Year.now().getValue();
        for (int i = 0; i < users.length; i++)
            if (year - users[i].getYearOfBirth() >= 40)
                users[i].getInfo();
    }
}



