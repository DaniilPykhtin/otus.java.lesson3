package ru.otus.daniil.lessons;


import java.util.*;

//
//Реализуйте класс PhoneBook, который хранит в себе список имен (фио) и телефонных номеров;
//        Метод add() должен позволять добавлять запись имя-телефон;
//        Метод find() выполнять поиск номер(-а, -ов) телефона по имени;
//        Под одним именем может быть несколько телефонов (в случае однофамильцев, или наличии у
//        одного человека нескольких номеров), тогда при запросе такой фамилии
//        должны выводиться все телефоны;
//        Метод containsPhoneNumber должен проверять наличие телефона в справочнике.
public class PhoneBook {

    HashMap<String, HashSet<String>> namePhoneMap= new HashMap<>();

    public void find(String name) {
        //namePhoneMap.get(name);
        System.out.println(namePhoneMap.get(name));

    }

    public void add(String name, String phone) {

        if (namePhoneMap.containsKey(name)) {
            namePhoneMap.get(name).add(phone);
        } else {
            HashSet<String> newSet = new HashSet<>();
            newSet.add(phone);
            namePhoneMap.put(name, newSet);
        }

    }

    public boolean containsPhoneNumber (String phone) {
        for (HashSet value : namePhoneMap.values()) {
            if (value.contains(phone)) {
                return true;
            }
        }
        return false;
    }
}
