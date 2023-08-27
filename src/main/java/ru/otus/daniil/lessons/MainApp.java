package ru.otus.daniil.lessons;

import java.util.ArrayList;

public class MainApp {

    public static ArrayList<Integer> createIntList(int min, int max) {
        ArrayList<Integer> intList = new ArrayList<>();
        //проверка на min < max и неотрицательность
        for (int i = 0; i < max - min + 1; i++) {
            intList.add(min + i);
        }

        return intList;
    }

    public static int sumIntList(ArrayList<Integer> intList) {
        int res = 0;
        for (int i = 0; i < intList.size(); i++) {
            int gotValue = intList.get(i);
            if (gotValue > 5) {
                res += gotValue;
            }
        }
        return res;
    }

    public static void reinitListByValue(ArrayList<Integer> intList, int value) {
        for (int i = 0; i < intList.size(); i++) {
            intList.set(i, value);
        }
    }

    public static void increaseListByValue(ArrayList<Integer> intList, int value) {
        for (int i = 0; i < intList.size(); i++) {
            intList.set(i, intList.get(i) + value);
        }
    }

    //        Списки
    public static ArrayList<String> getEmployeeNamesList(ArrayList<Employee> empList) {
        // проверка что список не пустой

        ArrayList<String> empNameList = new ArrayList<>();

        for (int i = 0; i < empList.size(); i++) {
            empNameList.add(empList.get(i).name);
        }
        return empNameList;
    }

    public static ArrayList<Employee> getEmployeeByAge(ArrayList<Employee> empList, int minAge) {
        // проверка что список не пустой
        ArrayList<Employee> someEmployees = new ArrayList<>();
        for (int i = 0; i < empList.size(); i++) {
            if (empList.get(i).age >= minAge) {
                someEmployees.add(empList.get(i));
            }
        }
        return someEmployees;
    }

    //        Реализуйте метод, принимающий в качестве аргумента список сотрудников и
//        минимальный средний возраст, и проверяющий что средний возраст сотрудников превышает указанный аргумент;
    public static boolean filterEmployeesByAvgAge(ArrayList<Employee> empList, int avgAge) {
        // проверка что список не пустой
        float resAvgAge = 0f;
        for (int i = 0; i < empList.size(); i++) {
            resAvgAge += empList.get(i).age;
        }
        resAvgAge /= empList.size();
        return (resAvgAge > avgAge);
    }

    //        Реализуйте метод, принимающий в качестве аргумента список сотрудников, и
//        возвращающий ссылку на самого молодого сотрудника.
    public static Employee getYoungestEmployee(ArrayList<Employee> empList) {
        // проверка что список не пустой
        Employee empLink = empList.get(0);
        int minAge = empLink.age;

        for (int i = 1; i < empList.size(); i++) {

            //System.out.println(empList.get(i).name+ " " + empList.get(i).age);
            if (minAge > empList.get(i).age) {
                empLink = empList.get(i);
                minAge = empLink.age;
            }
        }
        return empLink;
    }

    public static void main(String[] args) {

        System.out.println(createIntList(3, 9));


        ArrayList<Integer> intList = createIntList(3, 9);
        System.out.println(sumIntList(intList));

        reinitListByValue(intList, 15);
        System.out.println(intList);

        increaseListByValue(intList, -9);
        System.out.println(intList);


        ArrayList<Employee> emp = new ArrayList<>();

        emp.add(new Employee("Vasya", 43));
        emp.add(new Employee("Gena", 23));
        emp.add(new Employee("Katya", 33));
        emp.add(new Employee("Gennadiy Semyonovich", 63));


        System.out.println(getEmployeeNamesList(emp));

        ArrayList<Employee> empAge = getEmployeeByAge(emp, 35);
        for (int i = 0; i < empAge.size(); i++) {
            empAge.get(i).getInfo();
        }

        if (filterEmployeesByAvgAge(emp, 40)) {
            System.out.println("Список удовлетворяет условиям");
        } else {
            System.out.println("Список не удовлетворяет условиям");
        }

        System.out.println("Самый молодой сотрудник: ");
        getYoungestEmployee(emp).getInfo();
    }


}