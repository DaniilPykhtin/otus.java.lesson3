package ru.otus.daniil.lessons;
import java.util.List;

public interface ItemsStorage {

    void put(List<Item> items);

    List<Item> get();

}
