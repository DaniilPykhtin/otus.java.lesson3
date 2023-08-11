package ru.otus.daniil.lessons.lesson13;
//
//Описание/Пошаговая инструкция выполнения домашнего задания:
//        Создайте класс Человек с полями name (имя) и currentTransport (текущий транспорт)
//        Реализуйте в вашем приложении классы Машина, Лошадь, Велосипед, Вездеход
//        Каждый из классов должен предоставлять возможность перемещаться на определенное расстояние с указанием типа местности
//        В приложении должны быть типы местности: густой лес, равнина, болото
//        Человек должен иметь возможность сесть на любой из этих видов транспорта, встать с него, или
//        переместиться на некоторое расстояние (при условии что он находится на каком-либо транспорте)
//        При попытке выполнить перемещение у человека, не использующего транспорт, считаем что он просто идет
//        указанное расстояние пешком
//        При перемещении Машина и Вездеход тратят бензин, который у них ограничен. Лошадь тратит силы. Велосипед
//        может использоваться без ограничений (можете для усложнения велосипедом тратить силы “водителя”).
//        При выполнении действия результат должен быть отпечатан в консоль
//        У каждого вида транспорта есть местности по которым он не может перемещаться: машина - густой лес и болото,
//        лошадь и велосипед - болото, вездеход - нет ограничений
//        При попытке переместиться должен быть возвращен результат true/false - удалось ли выполнить действие

public class Human {
    String name;
    Rideable currentTransport;
    int speed;
    int endurance;
    int enduranceConsumption;
    int weight;

    public Human(String name, int speed, int endurance, int enduranceConsumption, int weight) {
        this.name = name;
        this.speed = speed;
        this.endurance = endurance;
        this.enduranceConsumption = enduranceConsumption;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getEnduranceConsumption() {
        return enduranceConsumption;
    }

    public int getWeight() {
        return weight;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public void getInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Скорость: " + speed + " км/ч");
        System.out.println("Выносливость, осталось: " + endurance + " у.е.");
        System.out.println("Потребление выносливости: " + enduranceConsumption + " у.е./км");
    }

    public void getOn(Rideable transport) {
        if (currentTransport != null) {
            getOff();
        }
        System.out.println("Сажусь на " + transport);
        currentTransport = transport;
        transport.getHumanOn(this);

    }


    public void getOff() {
        if (currentTransport == null) {
            System.out.println("Я и так пеший");
            return;
        }
        System.out.println("Покидаю " + currentTransport);
        currentTransport.getHumanOff();
        currentTransport = null;
    }


    public boolean moveYourself(int distance, Landscape landscape) {
        if (currentTransport != null) {
            getOff();
        }
        if (enduranceConsumption * distance * landscape.movePenalty > endurance) {
            System.out.println(name + " не хватит сил ");
            return false;
        }
        endurance -= enduranceConsumption * distance * landscape.movePenalty;
        System.out.println(name + " прошел " + distance + " км по местности " + landscape + " за " + (float) distance * landscape.movePenalty * 60 / speed + " мин.");
        return true;
    }

    public boolean move(int distance, Landscape landscape) {

        if (currentTransport == null) {
            return moveYourself(distance, landscape);
        }

        return currentTransport.move(distance, landscape);
    }

}
